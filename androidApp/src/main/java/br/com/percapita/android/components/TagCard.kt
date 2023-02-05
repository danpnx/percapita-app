package br.com.percapita.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    MyApplicationTheme {
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
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    val iconOptions = Icons.Filled.Edit
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { }) {
                        Icon(imageVector = iconOptions, contentDescription = "Editar")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun TagCard_Preview() {
    TagCard(tag = Tag("1", "ifood"))
}