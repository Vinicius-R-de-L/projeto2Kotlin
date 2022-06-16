package com.example.projeto05new.views.musicas

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projeto05new.data.AppDatabase
import com.example.projeto05new.data.models.Musica


@Composable
fun MusicasAdd(navController : NavController, db: AppDatabase){

    var nome by remember { mutableStateOf("") }
    var lancamento by remember { mutableStateOf("") }
    val musicaDao = db.musicaDao()

    Column(
        modifier = Modifier.fillMaxWidth().padding(30.dp).fillMaxHeight().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") }
        )

        OutlinedTextField(
            value = lancamento,
            onValueChange = { lancamento = it },
            label = { Text(text = "Lançamento") }
        )

        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                musicaDao.insert(Musica(nome = nome, lancamento = lancamento))
                navController.navigate("musicas")
            }
        ) {
            Text(text = "Adicionar")
        }
    }

}