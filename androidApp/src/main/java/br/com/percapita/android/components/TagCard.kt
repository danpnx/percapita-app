package br.com.percapita.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.DropdownMenuItem
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.model.Tag

@Composable
fun TagCard(
    tag: Tag
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp)
            .height(50.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colors.surface),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable { }
            ) {
            Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Column() {
                        Text(
                            text = tag.tagName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    DropdownMenuTag()
                }
            }
        }
}

@Composable
fun DropdownMenuTag() {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "Localized description",
                tint = Color.Gray
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = { }) {
                Text("Editar")
            }
            DropdownMenuItem(onClick = { }) {
                Text("Deletar")
            }
        }
    }
}

@Composable
@Preview
fun TagCard_Preview() {
    MyApplicationTheme(darkTheme = false) {
        TagCard(tag = Tag("1", "iFood"))
    }
}

@Composable
@Preview
fun TagCardDark_Preview() {
    MyApplicationTheme(darkTheme = true) {
        TagCard(tag = Tag("1", "iFood"))
    }
}