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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projeto05new.data.AppDatabase
import com.example.projeto05new.data.models.Cantor
import com.example.projeto05new.data.models.Musica

@Composable
fun MusicasScreen(navController : NavController, db: AppDatabase) {
    val musicaDao = db.musicaDao()
    var musicas: List<Musica> = musicaDao.getAll()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.Top
        ){
            Text(text = "Musicas", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }
        
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            Button(
                onClick = { navController.navigate("musicas_detalhes")}
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go"
                )
                Text(text = "Detalhes")
            }

            Button(
                onClick = {
                    navController.navigate("musicas_add")
                }
            ) {
                Text(text = "Add")
            }

            Button(
                onClick = {
                    musicaDao.insert(Musica(nome = "How you Like That", lancamento = "2020"))
                    musicaDao.insert(Musica(nome = "The Trooper", lancamento = "1983"))
                    musicaDao.insert(Musica(nome = "Alexander The Great", lancamento = "1986"))
                    musicaDao.insert(Musica(nome = "Playing With Fire", lancamento = "2016"))
                    musicaDao.insert(Musica(nome = "Orion", lancamento = "1986"))
                    musicaDao.insert(Musica(nome = "Don't Talk To Strangers", lancamento = "1983"))
                    musicas = musicaDao.getAll();
                    navController.navigate("musicas")
                }
            ) {
                Text(text = "inserção inicial")
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
                        Text(text = "Nome: ${musica.nome}")
                    }
                }

            }
        }

    }

}
