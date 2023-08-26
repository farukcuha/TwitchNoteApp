package com.farukcuha.twitchnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.farukcuha.twitchnoteapp.ui.theme.TwitchNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwitchNoteAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "notes") {
                    composable("notes") { NotesScreen(navController = navController) }
                    composable(route = "create_a_note?note_id={note_id}", arguments = listOf(
                        navArgument("note_id") {
                            type = NavType.StringType
                            nullable = true
                        }
                    )) {
                        val arguments = requireNotNull(it.arguments)
                        val noteId = arguments.getString("note_id")
                        CreateNoteScreen(noteId, navController = navController)
                    }
                }
            }
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TwitchNoteAppTheme {
        Greeting("Android")
    }
}*/