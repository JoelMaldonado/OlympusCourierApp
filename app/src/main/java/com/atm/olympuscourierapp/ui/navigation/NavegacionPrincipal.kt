package com.atm.olympuscourierapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.atm.olympuscourierapp.ui.features.DarConformidad.DarConformidadScreen
import com.atm.olympuscourierapp.ui.features.DetalleReparto.DetailRepartoScreen
import com.atm.olympuscourierapp.ui.features.Login.LoginScreen
import com.atm.olympuscourierapp.ui.features.Menu.MenuScreen
import com.atm.olympuscourierapp.ui.features.Perfil.PerfilScreen
import com.atm.olympuscourierapp.ui.features.Settings.SettingsScreen
import com.atm.olympuscourierapp.ui.features.VerCodigo.VerCodigoScreen
import com.atm.olympuscourierapp.ui.features.VerRepartos.VerRepartosScreen

@Composable
fun NavegacionPrincipal(
    modifier: Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Rutas.Login.url
    ) {
        composable(Rutas.Login.url) {
            LoginScreen(
                toMenu = {
                    navController.navigate(Rutas.Menu.url)
                }
            )
        }
        composable(Rutas.Menu.url) {
            MenuScreen(
                logout = {
                    navController.popBackStack(Rutas.Login.url, false)
                },
                toVerRepartos = {
                    navController.navigate(Rutas.VerRepartos.url)
                },
                toVerCodigo = {
                    navController.navigate(Rutas.VerCodigo.url)
                },
                toSettings = {
                    navController.navigate(Rutas.Settings.url)
                }
            )
        }

        composable(Rutas.VerRepartos.url) {
            VerRepartosScreen(
                toPerfil = {
                    navController.navigate(Rutas.Perfil.url)
                },
                toDetalle = {
                    navController.navigate(Rutas.DetailReparto.sendId(it))
                },
                toDarConformidad = {
                    navController.navigate(Rutas.DarConformidad.sendId(it))
                }
            )
        }

        composable(Rutas.VerCodigo.url) {
            VerCodigoScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.Perfil.url) {
            PerfilScreen(
                back = {
                    navController.popBackStack()
                },
                logout = {
                    navController.popBackStack(Rutas.Login.url, false)
                }
            )
        }
        composable(
            route = Rutas.DarConformidad.url,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt("id")?.let { id ->
                DarConformidadScreen(
                    idReparto = id,
                    back = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            route = Rutas.DetailReparto.url,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt("id")?.let { id ->
                DetailRepartoScreen(
                    idReparto = id,
                    back = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            route = Rutas.Settings.url
        ) {
            SettingsScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }
    }
}