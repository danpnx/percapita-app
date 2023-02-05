package br.com.percapita.android.screens.tag

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.percapita.android.MyApplicationTheme
import br.com.percapita.android.components.BottomBar
import br.com.percapita.android.components.TagCard
import br.com.percapita.android.components.TopBar
import br.com.percapita.android.screens.history.AddTransactionFAB
import br.com.percapita.android.screens.history.HistoryTopBar
import br.com.percapita.android.util.Lists.tagList

@Composable
fun TagScreen(isSystemDarkTheme: Boolean) {
    MyApplicationTheme(darkTheme = isSystemDarkTheme) {
        Scaffold(
            bottomBar = { BottomBar() },
            topBar = { TopBar(title = "Tags") }
        ) {
            LazyColumn(
                modifier = Modifier.padding(it),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                items(tagList) { tag ->
                    TagCard(tag = tag)
                }
            }
        }
    }
}
@Composable
@Preview
fun TagScreen_Preview() {
    TagScreen(isSystemDarkTheme = false)
}