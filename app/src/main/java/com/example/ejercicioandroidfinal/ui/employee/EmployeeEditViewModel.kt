package com.example.ejercicioandroidfinal.ui.employee

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicioandroidfinal.data.EmployeesRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel para recuperar y actualizar un empleado desde la base de datos del [EmployeesRepository].
 */
class EmployeeEditViewModel (
    savedStateHandle: SavedStateHandle,
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    /**
     * Mantiene el estado actual del empleado ui
     */
    var employeeUiState by mutableStateOf(EmployeeUiState())
        private set

    private val employeeId: Int = checkNotNull(savedStateHandle[EmployeeEditDestination.employeeIdArg])

    init {
        viewModelScope.launch {
            employeeUiState = employeesRepository.getEmployeeStream(employeeId)
                .filterNotNull()
                .first()
                .toEmployeeUiState()
        }
    }

    /**
     * Actualizar el elemento en la base de datos de [EmployeesRepository]
     */
    suspend fun updateEmployee(){
        if (validateInput(employeeUiState.employeeDetails)) {
            employeesRepository.updateEmployee(employeeUiState.employeeDetails.toEmployee())
        }
    }

    /**
     * Actualiza el [employeeUiState] con el valor proporcionado en el argumento. Este método también activa
     * una validación para los valores de entrada.
     */
    fun updateUiState(employeeDetails: EmployeeDetails) {
        employeeUiState =
            EmployeeUiState(employeeDetails = employeeDetails, isEntryValid = validateInput(employeeDetails))
    }

    private fun validateInput(uiState: EmployeeDetails = employeeUiState.employeeDetails): Boolean {
        return with(uiState){
            firstname.isNotBlank() && lastname.isNotBlank() && position.isNotBlank() && salary.isNotBlank() && yearsofexperience.isNotBlank()
        }
    }
}