package com.royacodes.readiumapp

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import com.royacodes.readiumapp.ui.theme.ReadiumAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val bookshelfViewModel: BookshelfViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        bookshelfViewModel.addPublicationFromUri(uri)
        setContent {
            ReadiumAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    openBook()
                }
            }
        }
    }
}

@Composable
fun openBook() {
    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val url = "https://www.gutenberg.org/ebooks/11.epub.images"
    val uri = Uri.parse(url)
    loading = true
    bookshelfViewModel.addPublicationFromUri(uri)

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

/** Iterate the progress value */
suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReadiumAppTheme {
        LinearDeterminateIndicator(true)
    }
}