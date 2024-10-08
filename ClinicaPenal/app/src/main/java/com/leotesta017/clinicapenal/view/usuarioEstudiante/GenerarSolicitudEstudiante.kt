package com.leotesta017.clinicapenal.view.usuarioEstudiante


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leotesta017.clinicapenal.model.modelUsuario.Appointment
import com.leotesta017.clinicapenal.model.modelUsuario.Case
import com.leotesta017.clinicapenal.model.modelUsuario.UserIdData
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.CaseUserAdminItem
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.EstudiantesBarraNav
import com.leotesta017.clinicapenal.view.theme.ClinicaPenalTheme
import com.leotesta017.clinicapenal.view.templatesPantallas.GenerarSolicitudPantallaTemplatenavController
import com.leotesta017.clinicapenal.viewmodel.viewmodelUsuario.CaseViewModel
import com.leotesta017.clinicapenal.viewmodel.viewmodelUsuario.UsuarioViewModel


@Composable
fun GenerarSolicitudEstudiante(navController: NavController?) {
    val caseViewModel: CaseViewModel = viewModel()
    val userId = UserIdData.userId
    val usuarioViewModel: UsuarioViewModel = viewModel()

    val casesList by usuarioViewModel.userCasesWithAppointments.collectAsState()

    LaunchedEffect(userId) {
        if (userId != null) {
            // Obtener casos completos con sus detalles
            usuarioViewModel.fetchUserCasesWithLastAppointmentDetails(userId)
        }
    }

    val citasList by caseViewModel.unrepresentedCasesWithLastAppointment.collectAsState()

    LaunchedEffect(citasList){
        caseViewModel.fetchUnrepresentedCasesWithLastAppointment()
    }

    val representacionList = remember { mutableStateOf<List<Triple<Case,String,Boolean>>>(emptyList()) }

    val tempRepresentacionList = mutableListOf<Triple<Case,String,Boolean>>()

    casesList.forEach { caseDetails ->
        if ((caseDetails.first.studentAssigned == userId ||
            caseDetails.first.lawyerAssigned == userId) &&
            caseDetails.first.represented)
        {
            tempRepresentacionList.add(caseDetails)
        }
    }

    representacionList.value = tempRepresentacionList


    // Llamamos a la función con las listas filtradas y los Composables adecuados
    GenerarSolicitudPantallaTemplatenavController(
        navController = navController,
        titulo1 = "Citas",
        items1 = citasList,  // Pasamos la lista de citas
        itemComposable1 = { cita ->
            CaseUserAdminItem(
                case = cita, // Pasamos el item de tipo Case
                onDelete = { },
                confirmDeleteText = "¿Estás seguro de que deseas eliminar esta cita?",
                navController = navController,
                route = "detallecasoestudiante"
            )
        },
        titulo2 = "Casos Representación",
        items2 = representacionList.value,  // Pasamos la lista de casos de representación
        itemComposable2 = { representacion ->
            CaseUserAdminItem(
                case = representacion, // Pasamos el item de tipo Case
                onDelete = { },
                confirmDeleteText = "¿Estás seguro de que deseas eliminar este caso de representación?",
                navController = navController,
                route = "detallecasoestudiante"
            )
        },
        barraNavComposable = {
            Box(modifier = Modifier.fillMaxSize()) {
                EstudiantesBarraNav(
                    navController = navController,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                )
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewGenerarSolicitudEstudiante() {
    ClinicaPenalTheme {
        GenerarSolicitudEstudiante(navController = rememberNavController())
    }
}
