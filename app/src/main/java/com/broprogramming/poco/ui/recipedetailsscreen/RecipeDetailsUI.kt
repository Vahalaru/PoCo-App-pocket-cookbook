package com.broprogramming.poco.ui.recipedetailsscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.broprogramming.poco.repository.model.Ingredient
import com.broprogramming.poco.repository.model.Recipe
import com.broprogramming.poco.repository.model.Step
import com.broprogramming.poco.ui.theme.Black
import com.broprogramming.poco.ui.theme.Purple200
import com.broprogramming.poco.ui.theme.Purple800
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecipeDetailsUI(viewModel: RecipeDetailsViewModel) {
    val recipe by viewModel.itemFlow.collectAsState(initial = Recipe(recipeDescription = "test", recipeName = "tester", recipeIngredients = listOf(Ingredient(ingredientName = "test ing", ingredientQuantity = "1", ingredientMeasurement = "cup")), recipeSteps = listOf(
        Step(step = "1. test description")
    ))
    )
    val item: Recipe = recipe as Recipe

    Timber.d("RecipeDetailsUI: $item")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipe") },
                backgroundColor = Purple800
            )
        },
        content = {
            tabsWithSwiping(recipe = item)
        }
    )
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
                    text = recipe.recipeName,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
            ) {
                Text(
                    text = recipe.recipeDescription,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun tabsWithSwiping(recipe: Recipe) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Ingredients", "Steps")
    val pagerState = rememberPagerState() // 2.
    Column {
        RecipeNameAndDescriptionCard(recipe = Recipe("", "Grilled Cheese", "Grilled Cheese", recipeDescription = "Grilled Cheese"))
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
                0 -> ingredientListComponent(ingredients = recipe.recipeIngredients!!)
                1 -> stepListComponent(steps = recipe.recipeSteps!!)
            }
        }
    }
}

@Composable
fun ingredientListComponent(ingredients: List<Ingredient>) {
    LazyColumn {
        items(items = ingredients) {
            ingredientRow(ingredient = it)
        }
    }
}

@Composable
fun ingredientRow(ingredient: Ingredient){
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
    ) {
        Text(text = "${ingredient.ingredientQuantity}  ${ingredient.ingredientMeasurement} ${ingredient.ingredientName}")
    }
}

@Composable
@Preview
fun ingredientListComponentPreview() {
    ingredientListComponent(ingredients = listOf(Ingredient("cup", "crap", "1"), Ingredient("given", "shits", "2")))
}

@Composable
fun stepListComponent(steps: List<Step>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(items = steps){
            stepRow(step = it)
        }
    }
}
@Composable
fun stepRow(step: Step){
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
    ) {
        Text(text = "${step.step}")
    }
}