package com.atm.olympuscourierapp.ui.features.Menu

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.atm.olympuscourierapp.R
import com.atm.olympuscourierapp.ui.theme.ColorP1
import com.atm.olympuscourierapp.ui.theme.ColorS1
import com.atm.olympuscourierapp.ui.theme.ColorSec
import com.jjmf.android.olympuscourierapp.ui.features.Menu.MenuViewModel

@Composable
fun MenuScreen(
    logout: () -> Unit,
    toVerRepartos: () -> Unit,
    toVerCodigo: ()->Unit,
    viewModel: MenuViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.fondo_blanco),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_large_slogan),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
            contentScale = ContentScale.FillWidth
        )

        Column {
            Text(
                text = "Hola,",
                fontSize = 18.sp,
                color = ColorSec,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = "Qué haremos hoy!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ColorP1,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = "Tus opciones:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 5.dp)
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CardMenu(
                modifier = Modifier.weight(1f),
                icon = R.drawable.ic_caja_2,
                title = "Ver Repartos",
                click = toVerRepartos
            )
            CardMenu(
                modifier = Modifier.weight(1f),
                icon = R.drawable.ic_qr,
                title = "Codigo QR",
                click = toVerCodigo
            )
        }

    }

    BackHandler {
        alertaCerrarSesion(
            context = context,
            click = logout
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMenu(
    modifier: Modifier,
    @DrawableRes icon: Int,
    title: String,
    click: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = ColorP1
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = click
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .border(2.dp, ColorSec, CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = ColorP1
                )
            }

            Text(
                text = title,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
}

fun alertaCerrarSesion(context: Context, click: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).apply {
        setCustomImage(R.drawable.ic_logout)
        titleText = "Cerrar Sesión"
        contentText = "¿Esta seguro de querer terminar la sesión?"
        setConfirmButton("Confirmar") {
            click()
            dismissWithAnimation()
        }
        setCancelButton("Cancelar") {
            dismissWithAnimation()
        }
        confirmButtonBackgroundColor = ColorP1.hashCode()
        cancelButtonBackgroundColor = ColorS1.hashCode()
        show()
    }
}