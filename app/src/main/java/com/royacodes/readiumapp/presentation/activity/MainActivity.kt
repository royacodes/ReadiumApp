package com.royacodes.readiumapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.royacodes.readiumapp.presentation.screens.openbook.OpenBookScreen
import com.royacodes.readiumapp.ui.theme.ReadiumAppTheme
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadiumAppTheme {
               OpenBookScreen()
            }
        }
    }
}


