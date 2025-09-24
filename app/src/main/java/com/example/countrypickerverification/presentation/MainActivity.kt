package com.example.countrypickerverification.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.countrypickerverification.presentation.screen.sign_up.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
        )

        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            splashScreenView.remove()
        }

        WindowCompat.setDecorFitsSystemWindows(
            window, false
        )

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