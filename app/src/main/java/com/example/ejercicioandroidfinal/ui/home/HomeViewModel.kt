package com.example.ejercicioandroidfinal.ui.home

import androidx.lifecycle.ViewModel
import com.example.ejercicioandroidfinal.data.Employee
import kotlinx.coroutines.flow.StateFlow
import com.example.ejercicioandroidfinal.data.EmployeesRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel para recuperar todos los empleados de la base de datos Room.
 */

class HomeViewModel (employeesRepository: EmployeesRepository): ViewModel() {
    val homeUiState: StateFlow<HomeUiState> =
        employeesRepository.getAllEmployeesStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State para HomeScreen
 */
data class HomeUiState(val employeesList: List<Employee> = listOf())