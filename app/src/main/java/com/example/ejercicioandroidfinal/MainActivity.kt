package com.example.ejercicioandroidfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ejercicioandroidfinal.ui.theme.EjercicioAndroidFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioAndroidFinalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    EmployeesApp()
                }
            }
        }
    }
}