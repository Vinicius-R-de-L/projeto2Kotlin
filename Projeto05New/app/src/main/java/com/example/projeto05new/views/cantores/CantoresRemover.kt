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
fun CantoresRemover(navController : NavController, db: AppDatabase) {
    val daoCantor = db.cantorDao()
    var primeiroNome by remember { mutableStateOf("") }
    var ultimoNome by remember { mutableStateOf("") }

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

        val cantor = daoCantor.findByName(first = primeiroNome, last = ultimoNome)

        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                daoCantor.delete(cantor)
                navController.navigate("cantores")
            }
        ) {
            Text(text = "Remover")
        }
    }

}