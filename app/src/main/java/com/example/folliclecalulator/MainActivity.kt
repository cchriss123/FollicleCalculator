package com.example.folliclecalulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.folliclecalulator.ui.theme.FollicleCalulatorTheme
import com.example.folliclecalulator.poko.Zone

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
    var zones by remember { mutableStateOf(listOf<Zone>()) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { it ->
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
            AddZoneRow { zoneName ->
                val newZone = Zone(name = zoneName)
                zones = zones + newZone
            }
            Zones(zones = zones, onZoneUpdate = { updatedZone ->
                zones = zones.map { if (it.id == updatedZone.id) updatedZone else it }
            })
        }
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

@Composable
fun AddZoneRow(onAddZone: (String) -> Unit) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Zone Name") },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
//            shape = RoundedCornerShape(20.dp), // Apply rounded corners
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White,
//                focusedIndicatorColor = Color.Transparent, // Hide the focused indicator
//                unfocusedIndicatorColor = Color.Transparent // Hide the unfocused indicator
//            ),
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .padding(horizontal = 8.dp)
//                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp)) // Apply border
        )
        CustomStyledButton(onClick = {
            val zoneName = textState.value.text
            if (zoneName.isNotBlank()) {
                onAddZone(zoneName)
                textState.value = TextFieldValue("")
            }
        }, text = "Add Zone")
    }
}




@Composable
fun CustomStyledButton(text: String, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(56.dp)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
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
fun Zones(zones: List<Zone>, onZoneUpdate: (Zone) -> Unit) {
    Column {
        zones.forEach { zone ->
            ZoneItem(zone = zone, onZoneUpdate = onZoneUpdate)
        }
    }
}

@Composable
fun ZoneItem(zone: Zone, onZoneUpdate: (Zone) -> Unit) {
    var areaInCm2 by remember { mutableStateOf(TextFieldValue(zone.areaInCm2.toString())) }
    var fuPerCm2 by remember { mutableStateOf(TextFieldValue(zone.fuPerCm2.toString())) }

    val fuPerZone by remember {
        derivedStateOf {
            val area = areaInCm2.text.toDoubleOrNull() ?: 0.0
            val fu = fuPerCm2.text.toDoubleOrNull() ?: 0.0
            (area * fu).toInt()
        }
    }

    Column (

        // add a border to the column
        modifier = Modifier
            .fillMaxWidth()
            .padding(46.dp)


    )
    {







        TextField(
            value = areaInCm2,
            onValueChange = { newTextFieldValue ->
                areaInCm2 = newTextFieldValue
                val updatedZone = zone.copy(
                    areaInCm2 = areaInCm2.text.toDoubleOrNull() ?: 0.0
                )
                onZoneUpdate(updatedZone)
            },
            label = { Text("Area (cm²)") }
        )

        Spacer(modifier = Modifier.height(16.dp))


        TextField(

            value = fuPerCm2,
            onValueChange = { newTextFieldValue ->
                fuPerCm2 = newTextFieldValue
                val updatedZone = zone.copy(
                    fuPerCm2 = fuPerCm2.text.toDoubleOrNull() ?: 0.0
                )
                onZoneUpdate(updatedZone)
            },
            label = { Text("FU per cm²") }
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(text = "FU per Zone: $fuPerZone")
    }
}
