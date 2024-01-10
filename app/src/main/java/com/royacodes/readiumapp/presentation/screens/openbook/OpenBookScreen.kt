package com.royacodes.readiumapp.presentation.screens.openbook

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.royacodes.readiumapp.ui.theme.ReadiumAppTheme

@Composable
fun OpenBookScreen() {

    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val url = "https://www.gutenberg.org/ebooks/11.epub.images"
    val uri = Uri.parse(url)
    loading = true
//    bookshelfViewModel.addPublicationFromUri(uri)

    LinearDeterminateIndicator(loading)


}

@Composable
fun LinearDeterminateIndicator(loading : Boolean) {


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (loading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReadiumAppTheme {
        LinearDeterminateIndicator(true)
    }
}