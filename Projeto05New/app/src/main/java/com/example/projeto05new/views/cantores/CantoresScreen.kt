package com.example.projeto05new.views.cantores

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

@Composable
fun CantoresScreen(navController : NavController, db: AppDatabase) {
    val cantorDao = db.cantorDao()
    var cantores: List<Cantor>
    cantores = cantorDao.getAll()
        
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
            Text(text = "Cantores", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            Button(
                onClick = { navController.navigate("cantores_detalhes")}
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go"
                )
                Text(text = "Detalhes")
            }
            
            Button(
                onClick = { 
                    navController.navigate("cantores_add")
                }
            ) {
                Text(text = "Add")
            }
            
            Button(
                onClick = {
                    cantorDao.insert(Cantor(primeiroNome = "Kim", ultimoNome = "Jennie", idade = "26"))
                    cantorDao.insert(Cantor(primeiroNome = "Paul", ultimoNome = "Bruce Dickinson", idade = "63"))
                    cantorDao.insert(Cantor(primeiroNome = "Pranpriya", ultimoNome = "Manobal", idade = "25"))
                    cantorDao.insert(Cantor(primeiroNome = "James", ultimoNome = "Alan Hetfield", idade = "58"))
                    cantorDao.insert(Cantor(primeiroNome = "Kim", ultimoNome = "Jisoo", idade = "27"))
                    cantorDao.insert(Cantor(primeiroNome = "Ronnie", ultimoNome = "James Dio", idade = "67"))
                    cantores = cantorDao.getAll();
                    navController.navigate("cantores")
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
                items(cantores){ cantor->
                    Row(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(text = "Nome: ${cantor.primeiroNome} ${cantor.ultimoNome} ")
                        Text(text = "Idade: ${cantor.idade}")
                    }
                }

            }
        }

    }

}