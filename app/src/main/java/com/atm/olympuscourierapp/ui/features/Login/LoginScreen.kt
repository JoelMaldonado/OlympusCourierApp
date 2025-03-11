package com.atm.olympuscourierapp.ui.features.Login

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.atm.olympuscourierapp.R
import com.atm.olympuscourierapp.ui.components.CajaTextoLogin
import com.atm.olympuscourierapp.ui.theme.ColorFondo
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.ui.theme.ColorTextoLabel
import com.jjmf.android.olympuscourierapp.ui.features.Login.LoginViewModel
import com.atm.olympuscourierapp.util.show


@Composable
fun LoginScreen(
    toMenu: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val focus = LocalFocusManager.current

    if (viewModel.toMenu) {
        LaunchedEffect(key1 = Unit) {
            viewModel.toMenu = false
            toMenu()
        }
    }

    viewModel.error?.let {
        LaunchedEffect(key1 = Unit) {
            context.show(it)
            viewModel.error = null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_login),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column {
                Text(
                    text = "Bienvenido",
                    color = ColorP1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_large),
                    contentDescription = null,
                    modifier = Modifier.width(250.dp)
                )
            }
            CajaTextoLogin(
                valor = viewModel.documento,
                newValor = {
                    viewModel.documento = it
                },
                ic = Icons.Default.Person,
                label = "Ingrese su documento",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focus.moveFocus(FocusDirection.Down)
                    }
                ),
                modifier = Modifier.padding(top = 12.dp)
            )
            Column {
                CajaTextoLogin(
                    valor = viewModel.clave,
                    newValor = {
                        if (it.length < 9) {
                            viewModel.clave = it
                        }
                    },
                    label = "Ingrese su contraseña",
                    keyboardType = KeyboardType.Number,
                    ic = Icons.Default.Lock,
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focus.clearFocus()
                        }
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                RecordarUsuario()
            }
            Spacer(modifier = Modifier.height(5.dp))

            if (viewModel.loader) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                Button(
                    onClick = viewModel::login,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorP1,
                        contentColor = Color.White,
                        disabledContainerColor = ColorP1
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(15.dp)
                ) {
                    Text(text = "Ingresar", fontWeight = FontWeight.Medium)
                }
            }

            Spacer(modifier = Modifier.weight(0.5f))

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Version ${obtenerVersionName(context)}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}

private fun obtenerVersionName(context: Context): String {
    try {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName ?: ""
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return ""
}

@Composable
fun RecordarUsuario(viewModel: LoginViewModel = hiltViewModel()) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Recordar usuario")
        Spacer(modifier = Modifier.width(10.dp))
        Switch(
            checked = viewModel.check,
            onCheckedChange = { viewModel.check = it },
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = ColorTextoLabel,
                uncheckedTrackColor = Color.White
            )
        )
    }
}