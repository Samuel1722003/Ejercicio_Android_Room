package com.example.ejercicioandroidfinal.data

import kotlinx.coroutines.flow.Flow

/**
 * Repositorio que proporciona inserción, actualización, borrado y recuperación de [Employee] desde una fuente de datos dada.
 */
interface EmployeesRepository {
    /**
     * Recupera todos los elementos de la base de datos dada.
     */
    fun getAllEmployeesStream(): Flow<List<Employee>>

    /**
     * Recuperar un empleado de la base de datos dada que coincida con el [id].
     */
    fun getEmployeeStream(id: Int): Flow<Employee?>

    /**
     * Insertar elemento en la base de datos
     */
    suspend fun insertEmployee(employee: Employee)

    /**
     * Borrar elemento de la base de datos
     */
    suspend fun deleteEmployee(employee: Employee)

    /**
     * Actualizar elemento en la base de datos
     */
    suspend fun updateEmployee(employee: Employee)
}