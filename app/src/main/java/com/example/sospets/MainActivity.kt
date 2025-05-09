package com.example.sospets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.sospets.data.NotesDatabase
import com.example.sospets.presentation.NotesViewModel
import com.example.sospets.presentation.screens.AddNoteScreen
import com.example.sospets.presentation.screens.NotesScreen
import com.example.sospets.ui.theme.SOSPetsTheme

@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel by viewModels<NotesViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun<T: ViewModel> create(modelClass: Class<T>): T {
                    return NotesViewModel(database.noteDao()) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SOSPetsTheme {
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "NotesScreen") {
                    composable("NotesScreen") {
                        NotesScreen(
                            state = state,
                            navController = navController,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable("AddNoteScreen") {
                        AddNoteScreen(
                            state = state,
                            navController = navController,
                            onEvent = viewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}
