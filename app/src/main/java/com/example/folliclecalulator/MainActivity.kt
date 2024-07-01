package com.example.folliclecalulator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.folliclecalulator.ui.theme.FollicleCalulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FollicleCalulatorTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color(0xFFF0F0F0))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                contentAlignment = Alignment.Center
            ) {
                ImageDisplay()
            }
            Spacer(modifier = Modifier.height(16.dp))
            AddZoneRow()
        }
    }
}



@Composable
fun AddZoneRow() {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .padding(horizontal = 8.dp)
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
        ) {
            BasicTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 18.sp,
                ),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        if (textState.value.text.isEmpty()) {
                            Text(
                                text = "Zone Name",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                                fontSize = 18.sp,
                            )
                        }
                        innerTextField()
                    }
                }
                ,

                modifier = Modifier.fillMaxSize()
            )
        }
        CustomStyledButton(onClick = { /* Add zone logic */ }, text = "Add Zone")
    }
}

@Composable
fun CustomStyledButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        modifier = Modifier.height(56.dp)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(20.dp),

        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF24a1d5),
            contentColor = Color.White
    ),
    ) {
        Text(text)
    }
}


@Composable
fun ImageDisplay(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.g_logo),
        contentDescription = "G Logo",
        modifier = modifier.size(60.dp)
    )
}

