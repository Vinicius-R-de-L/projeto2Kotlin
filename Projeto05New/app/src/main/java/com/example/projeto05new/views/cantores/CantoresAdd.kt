package com.example.projeto05new.views.cantores

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
import com.example.projeto05new.data.models.Cantor


@Composable
fun CantoresAdd(navController : NavController, db: AppDatabase){

    var primeiroNome by remember { mutableStateOf("") }
    var ultimoNome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    val cantorDao = db.cantorDao()

    Column(
        modifier = Modifier.fillMaxWidth().padding(30.dp).fillMaxHeight().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = primeiroNome,
            onValueChange = { primeiroNome = it },
            label = { Text(text = "Primeiro Nome") }
        )

        OutlinedTextField(
            value = ultimoNome,
            onValueChange = { ultimoNome = it },
            label = { Text(text = "Sobrenome") }
        )

        OutlinedTextField(
            value = idade,
            onValueChange = { idade = it },
            label = { Text(text = "Idade") }
        )

        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                cantorDao.insert(Cantor(primeiroNome = primeiroNome, ultimoNome = ultimoNome, idade = idade))
                navController.navigate("cantores")
            }
        ) {
            Text(text = "Adicionar")
        }
    }
    
}