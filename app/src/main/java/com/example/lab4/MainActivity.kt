package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(Selector())
                }
            }
        }
    }
}

@Composable
fun Greeting(selector: Selector) {
    val questText = remember {
        mutableStateOf(selector.getQuest())
    }
    val state = remember {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        Arrangement.SpaceAround,
        Alignment.CenterHorizontally,

        ) {
        state.value = selector.isFinish()
        if (state.value) {
            Text(
                text = "Конец, вы набрали " + selector.getPoints() + " баллов",
                Modifier.padding(10.dp),
            )
            Button(
                onClick = { selector.restart(); questText.value = selector.getQuest(); state.value = false },
                Modifier.fillMaxWidth(1f)
            )
            {
                Text(text = "Начать заново!")
            }
        } else {
            Text(
                text = questText.value,
                Modifier.padding(10.dp),
            )
            Column() {
                Row(
                    Modifier.fillMaxWidth(1f),
                ) {
                    Button(
                        onClick = {
                            selector.doAnswer(true); questText.value = selector.getQuest()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green, contentColor = Color.White),
                        modifier = Modifier.fillMaxWidth(0.5f),
                    ) {
                        Text(text = "Да")
                    }
                    Button(
                        onClick = {
                            selector.doAnswer(false); questText.value = selector.getQuest()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White),
                        modifier = Modifier.fillMaxWidth(2f),
                    )
                    {
                        Text(text = "Нет")
                    }
                }
                Button(
                    onClick = { selector.skip(); questText.value = selector.getQuest() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.fillMaxWidth(1f)
                ) {
                    Text(text = "Пропустить")
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab4Theme {
        Greeting(Selector())
    }
}