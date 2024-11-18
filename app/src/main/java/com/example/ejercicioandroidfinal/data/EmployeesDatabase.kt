package com.example.ejercicioandroidfinal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class EmployeesDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    companion object{
        @Volatile
        private var Instance: EmployeesDatabase? = null

        fun getDatabase(context: Context): EmployeesDatabase {
            // si la Instancia no es nula, la devuelve, en caso contrario crea una nueva instancia de base de datos.
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, EmployeesDatabase::class.java, "employees_database")
                    .build().also { Instance = it }
            }
        }
    }
}