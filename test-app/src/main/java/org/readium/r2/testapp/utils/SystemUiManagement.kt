/*
 * Copyright 2021 Readium Foundation. All rights reserved.
 * Use of this source code is governed by the BSD-style license
 * available in the top-level LICENSE file of the project.
 */

package org.readium.r2.testapp.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat

// Using ViewCompat and WindowInsetsCompat does not work properly in all versions of Android

/** Returns `true` if fullscreen or immersive mode is not set. */
private fun Activity.isSystemUiVisible(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val insets = window.decorView.rootWindowInsets
        return insets.isVisible(WindowInsets.Type.statusBars())
    } else {
        @Suppress("DEPRECATION")
        return this.window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0

    }

}

// Using ViewCompat and WindowInsetsCompat does not work properly in all versions of Android
/** Enable fullscreen or immersive mode. */
fun Activity.hideSystemUi() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        this.window.setDecorFitsSystemWindows(false)
        this.window.insetsController?.let { controller ->
            controller.hide(WindowInsets.Type.statusBars())
            controller.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        @Suppress("DEPRECATION")
        this.window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
            )
    }
}

// Using ViewCompat and WindowInsetsCompat does not work properly in all versions of Android
/** Disable fullscreen or immersive mode. */
fun Activity.showSystemUi() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        this.window.setDecorFitsSystemWindows(true)
        this.window.insetsController?.let { controller ->
            controller.show(WindowInsets.Type.statusBars())
            controller.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        @Suppress("DEPRECATION")
        this.window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            )
    }

}

/** Toggle fullscreen or immersive mode. */
fun Activity.toggleSystemUi() {
    if (this.isSystemUiVisible()) {
        this.hideSystemUi()
    } else {
        this.showSystemUi()
    }
}

/** Set padding around view so that content doesn't overlap system UI */
fun View.padSystemUi(insets: WindowInsets, activity: AppCompatActivity) =
    WindowInsetsCompat.toWindowInsetsCompat(insets, this)
        // Actual insets are inexplicably wrong when a SearchView is expanded.
        .getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars()).apply {
            setPadding(
                left,
                top + activity.supportActionBar!!.height,
                right,
                bottom
            )
        }

/** Clear padding around view */
fun View.clearPadding() =
    setPadding(0, 0, 0, 0)
