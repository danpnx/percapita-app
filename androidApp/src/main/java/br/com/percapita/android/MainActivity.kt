package br.com.percapita.android

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.navigation.Navigator

@SuppressLint("ConflictingOnColor")
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFF41e270),
            onPrimary = Color(0xFF003914),
            primaryVariant = Color(0xFF005320),
            secondary = Color(0xFF78da9f),
            onSecondary = Color(0xFF00391f),
            secondaryVariant = Color(0xFF00522f),
            error = Color(0xFFffb4ab),
            onError = Color(0xFF690005),
            background = Color(0xFF1a1c19),
            onBackground = Color(0xFFe2e3dd),
            surface = Color(0xFF1a1c19),
            onSurface = Color(0xFFC8C9C3)
        )
    } else {
        lightColors(
            primary = Color(0xFF006e2d),
            onPrimary = Color(0xFFFFFFFF),
            primaryVariant = Color(0xFF67ff8b),
            secondary = Color(0xFF006d40),
            onSecondary = Color(0xFFFFFFFF),
            secondaryVariant = Color(0xFF94f7ba),
            error = Color(0xFFba1a1a),
            onError = Color(0xFFFFFFFF),
            background = Color(0xFFfcfdf7),
            onBackground = Color(0xFF1a1c19),
            surface = Color(0xFFfcfdf7),
            onSurface = Color(0xFF1a1c19)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigator(isSystemDarkTheme = false)
        }
    }
}