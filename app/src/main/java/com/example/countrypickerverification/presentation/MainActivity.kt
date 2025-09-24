package com.example.countrypickerverification.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.countrypickerverification.presentation.screen.sign_up.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            splashScreenView.remove()
        }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SignUpScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}