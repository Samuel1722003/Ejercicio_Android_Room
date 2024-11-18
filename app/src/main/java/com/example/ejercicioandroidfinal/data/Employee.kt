package com.example.ejercicioandroidfinal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * La clase de datos Entidad representa una única fila en la base de datos.
 */

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstname: String,
    val lastname: String,
    val position: String,
    val salary: Double,
    val yearsofexperience: Int,
)