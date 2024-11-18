package com.example.ejercicioandroidfinal.ui.employee

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ejercicioandroidfinal.data.Employee
import com.example.ejercicioandroidfinal.data.EmployeesRepository
import java.text.NumberFormat

/**
 * ViewModel para validar e insertar empleados en la base de datos de Room.
 */
class EmployeeEntryViewModel(private val employeesRepository: EmployeesRepository) : ViewModel() {

    /**
     * Mantiene el estado actual del empleado ui
     */
    var employeeUiState by mutableStateOf(EmployeeUiState())
        private set

    /**
     * Actualiza el [employeeUiState] con el valor proporcionado en el argumento. Este método también activa
     * una validación para los valores de entrada.
     */
    fun updateUiState(employeeDetails: EmployeeDetails){
        employeeUiState =
            EmployeeUiState(employeeDetails = employeeDetails, isEntryValid = validateInput(employeeDetails))
    }

    /**
     * Inserta un [Employee] en la base de datos de Room
     */
    suspend fun saveEmployee(){
        if(validateInput()){
            employeesRepository.insertEmployee(employeeUiState.employeeDetails.toEmployee())
        }
    }

    private fun validateInput(uiState: EmployeeDetails = employeeUiState.employeeDetails): Boolean {
        return with(uiState) {
            firstname.isNotBlank() && lastname.isNotBlank() && position.isNotBlank() && salary.isNotBlank() && yearsofexperience.isNotBlank()
        }
    }
}

/**
 * Representa el estado Ui de un empleado.
 */
data class EmployeeUiState(
    val employeeDetails: EmployeeDetails = EmployeeDetails(),
    val isEntryValid: Boolean = false
)

data class EmployeeDetails(
    val id: Int = 0,
    val firstname: String = "",
    val lastname: String = "",
    val position: String = "",
    val salary: String = "",
    val yearsofexperience: String = "",
)

/**
 * Función de extensión para convertir [EmployeeUiState] en [Employee]. Si el valor de [EmployeeDetails.salary] es
 * no es un [Double] válido, entonces el precio se establecerá en 0.0. Del mismo modo, si el valor de
 * [EmployeeUiState] no es un [Int] válido, entonces la cantidad se establecerá en 0
 */

fun EmployeeDetails.toEmployee(): Employee = Employee(
    id = id,
    firstname = firstname,
    lastname = lastname,
    position = position,
    salary = salary.toDoubleOrNull() ?: 0.0,
    yearsofexperience = yearsofexperience.toIntOrNull() ?: 0
)

fun Employee.formatedSalary(): String {
    return NumberFormat.getCurrencyInstance().format(salary)
}

/**
 * Función de extensión para convertir [Employee] a [EmployeeUiState].
 */
fun Employee.toEmployeeUiState(isEntryValid: Boolean = false): EmployeeUiState = EmployeeUiState (
    employeeDetails = this.toEmployeeDetails(),
    isEntryValid = isEntryValid
)

/**
 * Función de extensión para convertir [Employee] a [EmployeeDetails]
 */
fun Employee.toEmployeeDetails(): EmployeeDetails = EmployeeDetails(
    id = id,
    firstname = firstname,
    lastname = lastname,
    position = position,
    salary = salary.toString(),
    yearsofexperience = yearsofexperience.toString()
)