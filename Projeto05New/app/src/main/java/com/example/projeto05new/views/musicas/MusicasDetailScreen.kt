package com.example.projeto05new.views.musicas

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
import com.example.projeto05new.data.models.Musica

@Composable
fun MusicasDetailScreen(navController : NavController, db: AppDatabase) {
    val musicaDao = db.musicaDao()
    var musicas: List<Musica> = musicaDao.getAll()

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
                onClick = { navController.navigate("musicas")}
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go"
                )
                Text(text = "Musicas")
            }

            Button(
                onClick = {
                    navController.navigate("musicas_remover")
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
                items(musicas){ musica->
                    Row(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(text = "Nome: ${musica.nome} Lançamento: ${musica.lancamento}")
                    }
                }

            }
        }

    }

}