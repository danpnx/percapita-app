package br.com.percapita.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.Greeting

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            background =  Color(0xFF302D2D),
            primary = Color(0xFF04C457),
            onPrimary = Color(0xFFFFFFFF),
            primaryVariant = Color(0xFF74F7AC),
            secondary = Color(0xFF518A6B),
            onSecondary = Color(0xFFDBF7E8),
            secondaryVariant = Color(0xFFA4C7B4),
            surface = Color(0xFFCACACA),
            onSurface = Color(0xFF88AC98),
            error = Color(0xFFDF6262),
            onError = Color(0xFFF8F8F8)
        )
    } else {
        lightColors(
            background =  Color(0xFFFFFFFF),
            primary = Color(0xFF04C457),
            onPrimary = Color(0xFFFFFFFF),
            primaryVariant = Color(0xFF74F7AC),
            secondary = Color(0xFF426F57),
            onSecondary = Color(0xFFDBF7E8),
            secondaryVariant = Color(0xFF9FB9AB),
            surface = Color(0xFFCACACA),
            onSurface = Color(0xFF6B8577),
            error = Color(0xFFDF6262),
            onError = Color(0xFFF8F8F8)
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        Greeting(Greeting().greeting())
                        TextField(value = "Hello", onValueChange = {})
                        Button(onClick = {  }) {
                            Text(text = "Aperte")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Hello, Android!")
    }
}