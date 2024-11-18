package com.example.ejercicioandroidfinal

import android.app.Application
import com.example.ejercicioandroidfinal.data.AppContainer
import com.example.ejercicioandroidfinal.data.AppDataContainer

class EmployeesApplication : Application() {

    /**
     * Instancia de AppContainer utilizada por el resto de clases para obtener dependencias.
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}