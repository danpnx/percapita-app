package br.com.percapita.android.screens.tag

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.TagCard
import br.com.percapita.android.components.TopBar
import br.com.percapita.android.util.Lists.tagList

@Composable
fun TagScreen(isSystemDarkTheme: Boolean, onBack: () -> Unit, onCreateTag: () -> Unit) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            topBar = { TopBar(title = "Tags", onBack = onBack) },
            floatingActionButton = { AddTag(onCreateTag) }
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .height(445.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(tagList) { tag ->
                        TagCard(tag = tag)
                    }
                }
            }
        }
    }
}

@Composable
fun AddTag(onCreateTag: () -> Unit) {
    FloatingActionButton(
        onClick = onCreateTag,
        shape = CircleShape,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = FloatingActionButtonDefaults.elevation(7.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Cadastrar Tag"
        )
    }
}

@Composable
@Preview
fun TagScreen_Preview() {
    TagScreen(isSystemDarkTheme = false, onBack = {}, onCreateTag = {})
}

@Composable
@Preview
fun TagScreenDark_Preview() {
    TagScreen(isSystemDarkTheme = true, onBack = {}, onCreateTag = {})
}