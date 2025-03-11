package com.atm.olympuscourierapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atm.olympuscourierapp.ui.theme.ColorP1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CajaTexto(
    valor: String,
    newValor: (String) -> Unit,
    titulo: String,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    readOnly : Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    color: Color = ColorP1,
    mensajeError:String? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = titulo, color = color, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = valor,
            onValueChange = newValor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                unfocusedBorderColor = color,
                focusedBorderColor = color,
                cursorColor = color,
                focusedPlaceholderColor = Color.Gray.copy(0.5f)
            ),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(text = label)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            keyboardActions = keyboardActions,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            readOnly = readOnly,
            singleLine = true,
            maxLines = 1,
            visualTransformation = visualTransformation
        )
        mensajeError?.let {
            Text(text = it, fontSize = 12.sp, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(start = 10.dp))
        }
    }
}