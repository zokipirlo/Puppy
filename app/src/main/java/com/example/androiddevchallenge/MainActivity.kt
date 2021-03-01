package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.ui.screen.PetsList
import com.example.androiddevchallenge.ui.screen.PetDetails
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.kyant.pixelmusic.ui.sharedelements.SharedElementsRoot

class MainActivity : AppCompatActivity() {
    private val petsViewModel by viewModels<PetsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(petsViewModel)
            }
        }
    }
}

@Composable
fun MyApp(viewModel: PetsViewModel = viewModel()) {
    val pet: Pet? by viewModel.pet.collectAsState()
    SharedElementsRoot {
        when (pet) {
            null -> {
                PetsList(viewModel)
            }
            else -> {
                PetDetails(pet = pet!!) {
                    viewModel.closePet()
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
