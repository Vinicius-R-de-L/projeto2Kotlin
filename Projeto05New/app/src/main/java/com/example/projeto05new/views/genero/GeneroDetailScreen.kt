package com.example.projeto05new.views.genero

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projeto05new.data.AppDatabase
import com.example.projeto05new.data.models.Cantor
import com.example.projeto05new.data.models.Genero

@Composable
fun GeneroDetailScreen(navController : NavController, db: AppDatabase) {
    val generoDao = db.generoDao()
    var generos: List<Genero> = generoDao.getAll()

    Spacer(modifier = Modifier.height(30.dp))

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            Button(
                onClick = { navController.navigate("genero")}
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go"
                )
                Text(text = "Generos")
            }

            Button(
                onClick = {
                    navController.navigate("genero_remover")
                }
            ) {
                Text(text = "Remover")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            LazyColumn() {
                items(generos){ genero->
                    Row(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(text = "Nome: ${genero.genero_musical}")
                    }
                }

            }
        }

    }

}