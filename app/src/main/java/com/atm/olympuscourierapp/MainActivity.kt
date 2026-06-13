package com.atm.olympuscourierapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cn.pedant.SweetAlert.SweetAlertDialog
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.ui.theme.OlympusCourierAppTheme
import com.atm.olympuscourierapp.core.CheckNetwork
import com.atm.olympuscourierapp.ui.navigation.NavegacionPrincipal
import com.atm.olympuscourierapp.ui.theme.ColorP3
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.startUpdateFlowForResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(this) }

    private val updateLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) {
        // Si el usuario cancela una actualización obligatoria, se vuelve a solicitar
        checkForUpdate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OlympusCourierAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavegacionPrincipal(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(ColorP1)
                            .padding(innerPadding)
                            .background(Color.White)
                    )
                }
            }
            call()
        }
        checkForUpdate()
    }

    override fun onResume() {
        super.onResume()
        // Si la app se quedó a medio actualizar (el usuario salió de la pantalla
        // de actualización), se reanuda el flujo obligatorio.
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            if (info.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                appUpdateManager.startUpdateFlowForResult(
                    info,
                    updateLauncher,
                    AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
                )
            }
        }
    }

    private fun checkForUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                info.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    info,
                    updateLauncher,
                    AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
                )
            }
        }
    }

    private fun call() {
        CheckNetwork(this).observe(this) {
            if (it == false) {
                SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).apply {
                    titleText = "Sin Conexion a internet"
                    contentText = "Asegurate de estar conectado a una red Wi-Fi o Red de datos"
                    setConfirmButton("Confirmar") {
                        dismissWithAnimation()
                    }
                    confirmButtonBackgroundColor = ColorP1.hashCode()
                    show()
                }
            }
        }
    }
}