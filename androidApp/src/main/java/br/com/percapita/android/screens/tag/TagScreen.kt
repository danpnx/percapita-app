package br.com.percapita.android.screens.tag

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.TagCard
import br.com.percapita.android.components.TopBar
import br.com.percapita.utils.DataResult
import br.com.percapita.model.Tag
import br.com.percapita.payload.TagList

@Composable
fun TagScreen(isSystemDarkTheme: Boolean, onBack: () -> Unit, onCreateTag: () -> Unit) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            topBar = { TopBar(title = "Tags", onBack = onBack) },
            floatingActionButton = { AddTag(onCreateTag) }
        ) { _ ->
            val viewModel = viewModel<TagScreenViewModel>()
            val tag = viewModel.tag.collectAsState()

            when(tag) {
                is DataResult.Success<*> -> ContentTagScreen(tag as DataResult.Success<TagList>)
                else -> Unit
            }
        }
    }
}

@Composable
fun ContentTagScreen(result: DataResult.Success<TagList>) {
    val tags = result.data
    LazyColumn(
        modifier = Modifier
            .padding()
            .height(445.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(tags.list.size) {
            TagCard(tagName = tags.list[it].tagName)
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