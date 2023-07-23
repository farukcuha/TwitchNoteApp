package com.farukcuha.twitchnoteapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesScreenViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    title = { Text(text = "Notes App") },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate("create_a_note")
                        }) {
                            Icon(Icons.Filled.Add, null)
                        }
                    }
                )
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(viewModel.notes.size) { position ->
                NoteView(viewModel.notes[position])
            }
        }
    }
}

@Composable
fun NoteView(note: NoteEntity) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = note.title ?: "", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = note.body ?: "",
                maxLines = 2 , fontSize = 16.sp)
            Text(text = note.time?.toToDate() ?: "", fontStyle = FontStyle.Italic)
        }
    }
}

fun Long.toToDate(): String {
    val simpleDateFormat = SimpleDateFormat("dd.mm.yyyy - HH:MM")
    return simpleDateFormat.format(Date(this))
}

@Preview
@Composable
fun CreateNotesScreen() {
    NotesScreen(navController = rememberNavController())
}