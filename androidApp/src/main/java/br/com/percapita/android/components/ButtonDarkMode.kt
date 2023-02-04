package br.com.percapita.android.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.percapita.android.MyApplicationTheme

@Composable
fun ButtonDarkMode(darkTheme: Boolean){
    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 25.dp)){
        Button(onClick = {  /**/   },
            shape = CircleShape,
            modifier = Modifier.padding(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onPrimary)) {
            Icon(
                Icons.Default.Brightness4,
                contentDescription = "Dark Mode",
                modifier = Modifier.height(16.dp),
                tint = MaterialTheme.colors.onBackground)
        }
    }
}


@Preview
@Composable
fun ButtonDarkModePreview() {
    MyApplicationTheme {
        ButtonDarkMode(false)
    }
}

@Preview
@Composable
fun ButtonDarkModePreviewDark() {
    MyApplicationTheme(darkTheme = true) {
        ButtonDarkMode(true)
    }
}