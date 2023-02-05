package br.com.percapita.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.percapita.model.Tag

@Composable
fun TagCard(
    tag: Tag
) {
    Card(
        modifier = Modifier.padding(bottom = 0.3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable { }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Column() {
                    Spacer(modifier = Modifier.width(25.dp))
                    Text(text = tag.tagName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                val iconOptions = Icons.Filled.Edit
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = iconOptions, contentDescription = "Editar")
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
