package com.leotesta017.clinicapenal.view.usuarioEstudiante

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//TEMPLATE DE LA VISTA
import com.leotesta017.clinicapenal.view.templatesPantallas.PantallaInfoGenerica

//FUNCIONES GENERALES
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.CarruselDeNoticias
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.CategoriesSection
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.EstudiantesBarraNav
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.LabelCategoria
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.LabelCategoriaConBoton
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.MyTextNoticias
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.PantallasExtra
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.SearchBarPantallaInfo
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.ServicesSection
import com.leotesta017.clinicapenal.view.funcionesDeUsoGeneral.SpacedItem

//VIEW MODEL
import com.leotesta017.clinicapenal.viewmodel.VideoViewModel

@Composable
fun PantallaInfoEstudiante(navController: NavController?) {
    PantallaInfoGenerica(
        navController = navController,
        searchBar = { categorias, servicios, errorCategoria, errorServicio, onSearchStarted ->
            SearchBarPantallaInfo(
                searchText = "",
                onSearchTextChange = {},
                categorias = categorias,
                servicios = servicios,
                errorCategoria = errorCategoria,
                errorServicio = errorServicio,
                navController = navController,
                routeCategoria = "detalle_info",
                routeServicio = "servicios_info",
                onSearchStarted = onSearchStarted
            )
        },

        //SECCION DE NOTICIAS
        noticias = {
            SpacedItem(spacing = 16) {
                CarruselDeNoticias(
                    viewModel = VideoViewModel(),
                    contentText = {
                        MyTextNoticias(text = "Noticias")
                    }
                )
            }
        },

        //SECCION DE INFORMACION LEGAL
        informacionLegal = { categorias, error ->
            LabelCategoria(
                label = "Información Legal",
                modifier = Modifier.padding(0.dp)
            )

            CategoriesSection(
                navController = navController,
                route = "detalle_info",
                categories = categorias,
                error = error
            )
        },

        //SECCION DE SERVICIOS
        servicios = { servicios,error ->
            LabelCategoria( //Cambiar aqui
                label = "Servicios",
                modifier = Modifier.padding(0.dp)
            )

            ServicesSection(
                navController = navController,
                route = "servicios_info",
                servicios = servicios,
                error = error
            )

        },

        //SECCION DE JURIBOT Y GENERARSOLICITUD
        pantallasExtra = {
            PantallasExtra(
                navController = navController,
                routeJuribot = "JuriBotAdmin",
                routeCrearSolicitud = "generasolicitudestudiante"
            )
        },

        //SECCION INFERIOR DE LA BARRA DE NAVEGACION
        barraNav = {
            Box(modifier =  Modifier.fillMaxSize())
            {
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


