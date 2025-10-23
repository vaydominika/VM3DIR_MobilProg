package com.vm3dir_mobilprog_catshelter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vm3dir_mobilprog_catshelter.data.CatDatabase
import com.vm3dir_mobilprog_catshelter.repository.CatRepository
import com.vm3dir_mobilprog_catshelter.ui.screens.CatListScreen
import com.vm3dir_mobilprog_catshelter.ui.theme.VM3DIR_MobilProg_CatShelterTheme
import com.vm3dir_mobilprog_catshelter.viewmodel.CatViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val database = CatDatabase.getDatabase(this)
        val repository = CatRepository(database.catDao())
        val viewModel = CatViewModel(repository)
        
        setContent {
            VM3DIR_MobilProg_CatShelterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CatListScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatShelterPreview() {
    VM3DIR_MobilProg_CatShelterTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
        }
    }
}