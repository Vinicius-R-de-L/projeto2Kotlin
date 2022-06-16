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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projeto05new.data.AppDatabase
import com.example.projeto05new.data.models.Cantor
import com.example.projeto05new.data.models.Genero

@Composable
fun GeneroScreen(navController : NavController, db: AppDatabase) {
    val generoDao = db.generoDao()
    var generos: List<Genero>
    generos = generoDao.getAll()

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
            Text(text = "Gêneros Musicais", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            Button(
                onClick = { navController.navigate("genero_detalhes")}
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go"
                )
                Text(text = "Detalhes")
            }

            Button(
                onClick = {
                    navController.navigate("genero_add")
                }
            ) {
                Text(text = "Add")
            }

            Button(
                onClick = {
                    generoDao.insert(Genero(genero_musical = "Rock"))
                    generoDao.insert(Genero(genero_musical = "Kpop"))
                    generoDao.insert(Genero(genero_musical = "Pagode"))
                    generoDao.insert(Genero(genero_musical = "Sertanejo"))
                    generoDao.insert(Genero(genero_musical = "Classica"))
                    generoDao.insert(Genero(genero_musical = "Eletronica"))
                    generos = generoDao.getAll();
                    navController.navigate("genero")
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