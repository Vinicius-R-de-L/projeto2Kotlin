package com.example.projeto05new

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.projeto05new.data.AppDatabase
import com.example.projeto05new.ui.theme.Projeto05NewTheme
import com.example.projeto05new.views.cantores.CantoresAdd
import com.example.projeto05new.views.cantores.CantoresDetailScreen
import com.example.projeto05new.views.cantores.CantoresRemover
import com.example.projeto05new.views.cantores.CantoresScreen
import com.example.projeto05new.views.genero.GeneroAdd
import com.example.projeto05new.views.genero.GeneroDetailScreen
import com.example.projeto05new.views.genero.GeneroRemover
import com.example.projeto05new.views.genero.GeneroScreen
import com.example.projeto05new.views.musicas.MusicasAdd
import com.example.projeto05new.views.musicas.MusicasScreen
import com.example.projeto05new.views.musicas.MusicasDetailScreen
import com.example.projeto05new.views.musicas.MusicasRemover

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        setContent {
            Projeto05NewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MusicApp(
                        db
                    )
                }
            }
        }
    }
}

@Composable
fun MusicApp(db: AppDatabase) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar (
                modifier = Modifier.height(80.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavScreens.forEach { botNavScreen ->
                    BottomNavigationItem(
                        icon = { Icon(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(6.dp),
                            contentDescription = stringResource(id = botNavScreen.name),
                            painter = painterResource(id = botNavScreen.icon)
                        )},
                        label = { Text(text = stringResource(id = botNavScreen.name))},
                        selected = currentDestination?.hierarchy?.any{
                                it.route == botNavScreen.route
                             } == true,
                        onClick = {
                            navController.navigate(botNavScreen.route){
                                popUpTo(navController.graph.startDestinationId){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.CantoresScreen.route
        ){
            composable(Screen.CantoresScreen.route){
                CantoresScreen(navController, db)
            }
            composable(Screen.CantoresDetails.route){
                CantoresDetailScreen(navController, db)
            }
            composable(Screen.CantoresAdd.route){
                CantoresAdd(navController, db)
            }
            composable(Screen.CantoresRemover.route){
                CantoresRemover(navController, db)
            }



            composable(Screen.MusicasScreen.route){
                MusicasScreen(navController, db)
            }
            composable(Screen.MusicasDetails.route){
                MusicasDetailScreen(navController, db)
            }
            composable(Screen.MusicasAdd.route){
                MusicasAdd(navController, db)
            }
            composable(Screen.MusicasRemover.route){
                MusicasRemover(navController, db)
            }



            composable(Screen.GeneroScreen.route){
                GeneroScreen(navController, db)
            }
            composable(Screen.GeneroDetails.route){
                GeneroDetailScreen(navController, db)
            }
            composable(Screen.GeneroAdd.route){
                GeneroAdd(navController, db)
            }
            composable(Screen.GeneroRemover.route){
                GeneroRemover(navController, db)
            }
        }
    }
}


private val bottomNavScreens = listOf(
    Screen.CantoresScreen,
    Screen.GeneroScreen,
    Screen.MusicasScreen
)

sealed class Screen(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val name: Int,
){
    object CantoresScreen: Screen("cantores", R.drawable.cantor, R.string.cantores)
    object CantoresDetails: Screen("cantores_detalhes", R.drawable.cantor, R.string.cantores)
    object CantoresAdd: Screen("cantores_add", R.drawable.cantor, R.string.cantores)
    object CantoresRemover: Screen("cantores_remover", R.drawable.cantor, R.string.cantores)

    object MusicasScreen: Screen("musicas", R.drawable.musica, R.string.musica)
    object MusicasDetails: Screen("musicas_detalhes", R.drawable.musica, R.string.musica)
    object MusicasAdd: Screen("musicas_add", R.drawable.musica, R.string.musica)
    object MusicasRemover: Screen("musicas_remover", R.drawable.musica, R.string.musica)


    object GeneroScreen: Screen("genero", R.drawable.genero, R.string.genero)
    object GeneroDetails: Screen("genero_detalhes", R.drawable.genero, R.string.genero)
    object GeneroAdd: Screen("genero_add", R.drawable.genero, R.string.genero)
    object GeneroRemover: Screen("genero_remover", R.drawable.genero, R.string.genero)

}



