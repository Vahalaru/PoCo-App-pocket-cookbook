package com.broprogramming.poco.ui.cookbookscreen

//import com.broprogramming.poco.MyDestination
import android.widget.Toast
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
import com.broprogramming.poco.repository.model.Recipes
import com.broprogramming.poco.ui.CircularProgressBar
import com.broprogramming.poco.ui.theme.Black
import com.broprogramming.poco.ui.theme.Purple200
import com.broprogramming.poco.ui.theme.Purple800
import timber.log.Timber

@Composable
fun RecipeListUi(
    newViewModel: RecipesViewModel,
) {

    val recipeList by newViewModel.itemsFlow.collectAsState(initial = emptyList())
    val list: List<Recipes> = recipeList
    Timber.d("RecipeListUi flow list: $list")
    val context = LocalContext.current

    LazyColumn{
        items(items = list) { recipe ->
            RecipeListItem(recipe = recipe, onClick = {
                Toast.makeText(context, "DocResource Id: (as string) ${recipe.recipe_details_id.toString()}", Toast.LENGTH_LONG).show() })
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