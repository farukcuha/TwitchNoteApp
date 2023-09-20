package com.farukcuha.twitchnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.farukcuha.twitchnoteapp.presentation.screens.create_note.CreateNoteScreen
import com.farukcuha.twitchnoteapp.presentation.screens.notes.NotesScreen
import com.farukcuha.twitchnoteapp.presentation.theme.TwitchNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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