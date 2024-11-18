package com.example.ejercicioandroidfinal.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ejercicioandroidfinal.EmployeesApplication
import com.example.ejercicioandroidfinal.ui.home.HomeViewModel
import com.example.ejercicioandroidfinal.ui.employee.EmployeeDetailsViewModel
import com.example.ejercicioandroidfinal.ui.employee.EmployeeEditViewModel
import com.example.ejercicioandroidfinal.ui.employee.EmployeeEntryViewModel

/**
 * Proporciona Factory para crear una instancia de ViewModel para toda la aplicación Empleados
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Inicializador para EmployeeEditViewModel
        initializer {
            EmployeeEditViewModel(
                this.createSavedStateHandle(),
                employeesApplication().container.employeesRepository
            )
        }

        // Inicializador para ItemEntryViewModel
        initializer {
            EmployeeEntryViewModel(employeesApplication().container.employeesRepository)
        }

        // Inicializador para ItemDetailsViewModel
        initializer {
            EmployeeDetailsViewModel(
                this.createSavedStateHandle(),
                employeesApplication().container.employeesRepository
            )
        }

        // Inicializador para HomeViewModel
        initializer {
            HomeViewModel(employeesApplication().container.employeesRepository)
        }
    }
}

/**
 * Función de extensión para consultas para el objeto [Application] y devuelve una instancia de
 * [EmployeesApplication].
 */
fun CreationExtras.employeesApplication(): EmployeesApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as EmployeesApplication)