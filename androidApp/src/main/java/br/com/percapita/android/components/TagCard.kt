package br.com.percapita.android.components

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.screens.tag.DeleteTagViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagCard(
    id: String,
    tagName: String,
    onEditTag: (String) -> Unit,
    onDeleteTag: (String) -> Unit,
    onRegisterTransaction: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp)
            .height(50.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colors.surface),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = { onRegisterTransaction.invoke(tagName) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Column {
                    Text(
                        text = tagName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                DropdownMenuTag(id, onEditTag, onDeleteTag)
            }
        }
    }
}

@Composable
fun DropdownMenuTag(
    id: String,
    onEditTag: (String) -> Unit,
    onDeleteTag: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    val viewModel = viewModel<DeleteTagViewModel>()
    val tagState by viewModel.deleteTag.collectAsState()
    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopEnd)) {
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
            DropdownMenuItem(onClick = { onEditTag.invoke(id) }) {
                Text("Editar")
            }
            DropdownMenuItem(onClick = {
                viewModel.deleteTag("", id = id)
                showDialog.value = true }) {
                Text("Deletar")
            }
        }
    }

    if(showDialog.value) {
        AlertDialog(
            modifier = Modifier.background(
                color = MaterialTheme.colors.surface),
            title = { Text(text = "")},
            text = { Text(text = "Você deseja deletar essa tag?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )},
            onDismissRequest = { showDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    onDeleteTag(id)
                    Toast.makeText(context, "Tag deletada com sucesso!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Sim",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onBackground)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text(text = "Não",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onBackground)
                }
            }
        )
    }
}

@Composable
@Preview
fun TagCard_Preview() {
    MyApplicationTheme(darkTheme = false) {
        TagCard("1", "TesteA", {}, {}, {})
    }
}

@Composable
@Preview
fun TagCardDark_Preview() {
    MyApplicationTheme(darkTheme = true) {
        TagCard("1", "TesteB", {}, {}, {})
    }
}