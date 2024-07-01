package com.example.folliclecalulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.folliclecalulator.ui.theme.FollicleCalulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FollicleCalulatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxWidth()
                        .padding(it).height(100.dp)
//                        .background(Color.LightGray)
                        ,
                        contentAlignment = Alignment.Center) {
                        ImageDisplay()
                    }
                }
            }
        }
    }
}

@Composable
fun ImageDisplay(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.g_logo),
        contentDescription = "G Logo",
        modifier = modifier.size(60.dp)  // Adjust the size as needed
    )
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        fontSize = 30.sp
//    )
//}

//@Composable
//fun GreetingWithImage(name: String, modifier: Modifier = Modifier) {
//    ImageDisplay(modifier = modifier)
//    Greeting(name = name, modifier = modifier)
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FollicleCalulatorTheme {
//        GreetingWithImage("Android")
//    }
//}
