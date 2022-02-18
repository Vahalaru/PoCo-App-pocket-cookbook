package com.broprogramming.poco.ui.cookbookscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.broprogramming.poco.model.Recipes
import com.broprogramming.poco.ui.CircularProgressBar
import com.broprogramming.poco.ui.destinations.RecipeDetailsUIDestination
import com.broprogramming.poco.ui.theme.Black
import com.broprogramming.poco.ui.theme.Purple200
import com.broprogramming.poco.ui.theme.Purple800
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigateTo
import timber.log.Timber

@Destination(route =
"cookbook")
@Composable
fun RecipeListUi(navController: NavController) {
    val newViewModel = hiltViewModel<RecipesViewModel>()


    val recipeList by newViewModel.itemsFlow.collectAsState(initial = emptyList())
    Timber.d("RecipeListUi flow list: $recipeList")
    val context = LocalContext.current

    LazyColumn{
        items(items = recipeList) { recipe ->
            RecipeListItem(recipe = recipe, onClick = {
                navController.navigateTo(RecipeDetailsUIDestination())//recipe.recipe_details_id)) //recipe.recipe_details_id))
            })//Toast.makeText(context, "DocResource Id: (as string) ${recipe.recipe_details_id.toString()}", Toast.LENGTH_LONG).show() })
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularProgressBar(
            isDisplayed = newViewModel.loading.value
        )
    }
}

@Composable
private fun RecipeListItem(
    recipe: Recipes?,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Purple200,
        contentColor = Black,
        border = BorderStroke(2.dp, Purple800),
        elevation = 6.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(2.dp)
            ) {
                recipe?.recipe_name?.let {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = it,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(2.dp)
            ) {
                recipe?.recipe_description?.let {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}