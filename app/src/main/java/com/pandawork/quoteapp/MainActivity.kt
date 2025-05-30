package com.pandawork.quoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandawork.quoteapp.ui.theme.QuoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuoteAppTheme {
                QuoteScreen()
            }
        }
    }
}

@Composable
fun QuoteScreen() {
    Scaffold(
        topBar = { TopAppBar() }) { innerPadding ->
        Column(
            Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxSize()
        ) {
            Card(
                Modifier
                    .height(750.dp)
                    .padding(innerPadding)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

            ) {
                Text(
                    text = "Daily Feed✨",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            16.dp
                        )
                )
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {}) {
                    Text(text = "Previous Quote")
                }
                Button(onClick = {}) {
                    Text(text = "Next Quote")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                "Quotes",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold
            )
        }
    )
}

@Preview
@Composable
fun QuoteScreenPreview() {
    QuoteAppTheme(dynamicColor = true) {
        QuoteScreen()
    }
}