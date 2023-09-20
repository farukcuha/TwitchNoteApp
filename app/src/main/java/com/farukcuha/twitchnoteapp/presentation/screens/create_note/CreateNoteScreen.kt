package com.farukcuha.twitchnoteapp.presentation.screens.create_note

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

enum class Mode {
    UPDATE, CREATE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteScreen(
    noteId: String? = null,
    navController: NavController,
    viewModel: CreateNoteScreenViewModel = hiltViewModel()
) {
    var mode = remember {
        mutableStateOf(Mode.CREATE)
    }
    var title = remember {
        mutableStateOf("")
    }
    val body = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    DisposableEffect(Unit) {
        noteId?.let {
            mode.value = Mode.UPDATE
            val note = viewModel.getNoteById(it.toInt())
            title.value = note?.title ?: ""
            body.value = note?.body ?: ""
        }
        onDispose {

        }
    }

    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    title = {
                        if (mode.value == Mode.UPDATE) Text(text = "Update the Note")
                        else Text(text = "Create a Note")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            if (title.value.isEmpty()) {
                                Toast.makeText(context, "Please fill the title!", Toast.LENGTH_SHORT).show()
                                return@IconButton
                            }
                            if (body.value.isEmpty()) {
                                Toast.makeText(context, "Please fill the body!", Toast.LENGTH_SHORT).show()
                                return@IconButton
                            }
                            if (mode.value == Mode.CREATE) viewModel.createNote(title = title.value, body = body.value)
                            else viewModel.updateNoteByNoteId(noteId?.toInt()!!, title.value, body.value)
                            navController.navigateUp()
                        }) {
                            Icon(Icons.Filled.Save, null)
                        }
                    }
                )
            }
        }
    ) {
        Column(modifier = Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(value = title.value, onValueChange = { titleNewValue ->
                title.value = titleNewValue
            }, placeholder = {
                Text(text = "Title")
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), shape = TextFieldDefaults.outlinedShape)
            OutlinedTextField(value = body.value, onValueChange = { bodyValue ->
                body.value = bodyValue
            }, placeholder = {
                Text(text = "Body")
            }, modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(8.dp), singleLine = false)
        }
    }
}

@Preview
@Composable
fun CreateNoteScreenPreview() {
    CreateNoteScreen(navController = rememberNavController())
}