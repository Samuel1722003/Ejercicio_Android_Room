package com.example.ejercicioandroidfinal.ui.employee

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicioandroidfinal.data.EmployeesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel para recuperar, actualizar y eliminar un elemento de la base de datos de [EmployeeRepository].
 */
class EmployeeDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val employeesRepository: EmployeesRepository,
): ViewModel() {

    private val employeeId: Int = checkNotNull(savedStateHandle[EmployeeDetailsDestination.employeeIdArg])

    /**
     * Contiene el estado de la interfaz de usuario de los detalles del artículo. Los datos se recuperan de [EmployeesRepository] y se asignan a
     * el estado UI.
     */
    val uiState: StateFlow<EmployeeDetailsUiState> =
        employeesRepository.getEmployeeStream(employeeId)
            .filterNotNull()
            .map {
                EmployeeDetailsUiState(outOfStock = it.salary <= 0, employeeDetails = it.toEmployeeDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = EmployeeDetailsUiState()
            )

    fun reduceQuantityByOne(){

    }

    /**
     * Elimina el elemento de la fuente de datos de [EmployeesRepository].
     */
    suspend fun deleteEmployee() {
        employeesRepository.deleteEmployee(uiState.value.employeeDetails.toEmployee())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Estado de la interfaz de usuario para EmployeeDetailsScreen
 */
data class EmployeeDetailsUiState(
    val outOfStock: Boolean = true,
    val employeeDetails: EmployeeDetails = EmployeeDetails()
)