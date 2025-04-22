package com.example.sospets.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sospets.presentation.NoteState
import com.example.sospets.presentation.NotesEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(85.dp)
                    .background(Color.Cyan)
                    .padding(top = 20.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SOS Pets",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { onEvent(NotesEvent.SortNotes) }) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "SortButton",
                        modifier = Modifier
                            .size(30.dp)
                            .background(color = Color.White),
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.title.value = ""
                state.description.value = ""
                navController.navigate("AddNoteScreen")
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Button")
            }
        }
    ) { paddingValues ->

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.notes.size) { index ->

                NoteItem(
                    state = state,
                    index = index,
                    onEvent = onEvent)
            }
        }
    }
}

@Composable
fun NoteItem(
    state: NoteState,
    index: Int,
    onEvent: (NotesEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = state.notes[index].title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.notes[index].description,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        IconButton(
            onClick =
                { onEvent(NotesEvent.DeleteNote(state.notes[index])) }
        ) {

            Icon(Icons.Filled.Delete, contentDescription = "Delete Button Icon",
                modifier = Modifier.size(35.dp),
                tint = Color.Black)
        }
    }
}


@Preview
@Composable
private fun NotesScreenPreview() {
    NotesScreen(
        state = NoteState(),
        navController = NavController(LocalContext.current),
        onEvent = {}
    )
}