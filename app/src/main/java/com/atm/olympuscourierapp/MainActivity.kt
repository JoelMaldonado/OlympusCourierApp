package com.atm.olympuscourierapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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