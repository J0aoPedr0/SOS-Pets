package com.example.sospets.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.sospets.presentation.NoteState
import com.example.sospets.presentation.NotesEvent

@Composable
fun NotesScreen(
    state:NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit
){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Deu certo")

    }
}