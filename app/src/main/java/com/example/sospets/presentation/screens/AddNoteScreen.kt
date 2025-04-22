package com.example.sospets.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sospets.presentation.NoteState
import com.example.sospets.presentation.NotesEvent

@Composable
fun AddNoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit
) {
    Scaffold(
        floatingActionButton =  {
            FloatingActionButton(onClick = {
                onEvent(NotesEvent.SaveNote(
                    title = state.title.value,
                    description = state.description.value
                ))
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "Add Button")
            }
        }
    ) {
        paddingValues ->

        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                    .background(Color.LightGray),
                value = state.title.value,
                onValueChange = {
                state.title.value = it
            },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                placeholder = {
                    Text(text = "Animal:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold)
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, start = 16.dp, end = 16.dp)
                    .background(Color.LightGray),
                value = state.description.value,
                onValueChange = {
                    state.description.value = it
                },
                placeholder = {
                    Text(text = "Descrição do Animal:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AddNoteScreenPreview() {
    AddNoteScreen( state = NoteState(),
        navController = NavController(LocalContext.current),
        onEvent = {})
}