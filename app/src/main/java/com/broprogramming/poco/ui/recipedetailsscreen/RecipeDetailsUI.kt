package com.broprogramming.poco.ui.recipedetailsscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.broprogramming.poco.model.Ingredient
import com.broprogramming.poco.model.Recipe
import com.broprogramming.poco.model.RecipeDetailsScreenNavArgs
import com.broprogramming.poco.model.Step
import com.broprogramming.poco.ui.CircularProgressBar
import com.broprogramming.poco.ui.theme.Black
import com.broprogramming.poco.ui.theme.Purple200
import com.broprogramming.poco.ui.theme.Purple800
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber

@Destination(route = "recipe", navArgsDelegate = RecipeDetailsScreenNavArgs::class)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecipeDetailsUI() {
    val viewModel = hiltViewModel<RecipeDetailsViewModel>()

    val recipe by viewModel.recipe.collectAsState(initial = Recipe())
    Timber.d("RecipeDetailsUI: recipeId = ${viewModel.recipeDetailsId}")

    Column() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressBar(
                isDisplayed = viewModel.loading.value
            )
            Timber.d("RecipeDetailsUI: recipe = $recipe")
            recipe?.let { TabsWithSwiping(recipe = it) }
        }

    }

}

@Composable
fun RecipeNameAndDescriptionCard(recipe: Recipe) {
    Card (
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Purple200,
        contentColor = Black,
        border = BorderStroke(2.dp, Purple800),
        elevation = 6.dp,
    ){
        Column{
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
            ) {
                Text(
                    text = recipe.recipe_name,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
            ) {
                Text(
                    text = recipe.description,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsWithSwiping(recipe: Recipe) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Ingredients", "Steps")
    val pagerState = rememberPagerState() // 2.
    Column {
        RecipeNameAndDescriptionCard(recipe = recipe)
        TabRow(selectedTabIndex = tabIndex,
            indicator = { tabPositions -> // 3.
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState,
                        tabPositions
                    )
                )
            },
        backgroundColor = Purple200) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) })
            }
        }
        HorizontalPager( // 4.
            count = tabTitles.size,
            state = pagerState,

        ) { tabIndex ->
            // Determine which screen to show based on the current tab index.
            when (tabIndex) {
                0 -> recipe.ingredients?.let { IngredientListComponent(ingredients = it) }
                1 -> recipe.recipe_steps?.let { StepListComponent(steps = it) }
            }
        }
    }
}

@Composable
fun IngredientListComponent(ingredients: List<Ingredient>) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
            ){
        items(items = ingredients) {
            IngredientRow(ingredient = it)
        }
    }
}

@Composable
fun IngredientRow(ingredient: Ingredient){
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
    ) {
        Text(text = "${ingredient.ingredient_quantity}  ${ingredient.ingredient_measurement} ${ingredient.ingredient_name}")
    }
}

@Composable
@Preview
fun IngredientListComponentPreview() {
    IngredientListComponent(ingredients = listOf(Ingredient("cup", "crap", "1"), Ingredient("given", "shits", "2")))
}

@Composable
fun StepListComponent(steps: List<Step>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items = steps){
            StepRow(step = it)
        }
    }
}
@Composable
fun StepRow(step: Step){
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
    ) {
        Text(text = "${step.step_num}. ${step.step_info}")
    }
}