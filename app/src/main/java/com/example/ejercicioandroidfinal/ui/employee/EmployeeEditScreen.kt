package com.example.ejercicioandroidfinal.ui.employee

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejercicioandroidfinal.EmployeesTopAppBar
import com.example.ejercicioandroidfinal.R
import com.example.ejercicioandroidfinal.ui.AppViewModelProvider
import com.example.ejercicioandroidfinal.ui.navigation.NavigationDestination
import com.example.ejercicioandroidfinal.ui.theme.EjercicioAndroidFinalTheme
import kotlinx.coroutines.launch

object EmployeeEditDestination : NavigationDestination {
    override val route = "employee_edit"
    override val titleRes = R.string.edit_item_title
    const val employeeIdArg = "employeeId"
    val routeWithArgs = "$route/{$employeeIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EmployeeEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            EmployeesTopAppBar(
                title = stringResource(EmployeeEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EmployeeEntryBody(
            employeeUiState = viewModel.employeeUiState,
            onEmployeeValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateEmployee()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeEditScreenPreview() {
    EjercicioAndroidFinalTheme {
        EmployeeEditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
    }
}