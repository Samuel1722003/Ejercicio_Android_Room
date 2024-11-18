package com.example.ejercicioandroidfinal.data

import android.content.Context

/**
 * Contenedor de aplicaciones para inyección de dependencias.
 */
interface AppContainer{
    val employeesRepository: EmployeesRepository
}

/**
 * [AppContainer] implementación que proporciona instancia de [OfflineEmployeesRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementación para [EmployeesRepository]
     */
    override val employeesRepository: EmployeesRepository by lazy {
        OfflineEmployeesRepository(EmployeesDatabase.getDatabase(context).employeeDao())
    }
}