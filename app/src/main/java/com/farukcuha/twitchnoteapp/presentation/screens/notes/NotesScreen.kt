package com.farukcuha.twitchnoteapp.presentation.screens.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.farukcuha.twitchnoteapp.data.model.entity.NoteEntity
import com.farukcuha.twitchnoteapp.domain.model.Note
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesScreenViewModel = hiltViewModel()
) {
    val notes = viewModel.notes.collectAsState(emptyList())
    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    title = { Text(text = "Notes App") },
                    actions = {
                        IconButton(onClick = {
                            viewModel.clear()
                        }) {
                            Icon(Icons.Filled.ClearAll, null)
                        }
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
        Column(
            modifier = Modifier.padding(it)
        ) {

        }
        notes.let { list ->
            LazyColumn(modifier = Modifier.padding(it)) {
                items(list.value.size) { position ->
                    NoteView(list.value[position], onClickDelete = { noteId ->
                        viewModel.deleteNote(noteId)
                    }, onClickEdit = { noteId ->
                        navController.navigate("create_a_note?note_id=$noteId")
                    })
                }
            }
        }
    }
}

@Composable
fun NoteView(note: Note, onClickDelete: (noteId: Int?) -> Unit, onClickEdit: (Int) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = note.title ?: "", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = note.body ?: "",
                maxLines = 2 , fontSize = 16.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = note.time?.toToDate() ?: "",
                    fontStyle = FontStyle.Italic)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    IconButton(onClick = {
                        note.id?.let{ onClickEdit(it) }
                    }, modifier = Modifier.size(20.dp)) {
                        Icon(Icons.Filled.Edit, null)
                    }
                    IconButton(onClick = {
                        onClickDelete(note.id)
                    }, modifier = Modifier.size(20.dp)) {
                        Icon(Icons.Filled.Delete, null)
                    }
                }
            }
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