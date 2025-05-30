package com.pandawork.quoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun QuoteScreen(viewModel: QuoteViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = { TopAppBar() }) { innerPadding ->
        Column(
            Modifier
                .padding(vertical = 24.dp, horizontal = 16.dp)
                .fillMaxSize()
        ) {
            Card(
                Modifier
                    .fillMaxWidth()
                    .height(700.dp)
                    .padding(innerPadding),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,

                    ) {
                    Image(
                        painter = painterResource(uiState.image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(280.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                    Text(
                        uiState.quote,
                        modifier = Modifier
                            .padding(vertical = 24.dp, horizontal = 12.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
            Spacer(Modifier.height(4.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {
                    viewModel.updateQuoteAndImage()
                }
                ) {
                    Text(text = "Previous Quote")
                }
                Button(onClick = {
                    viewModel.updateQuoteAndImage()
                }
                ) {
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
                "Quotes✨",
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