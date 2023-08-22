package com.mogaka.touristnews.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mogaka.touristnews.R
import com.mogaka.touristnews.data.models.Tourist


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: Screens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    touristViewModel: TouristViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentScreen = Screens.valueOf(
                backStackEntry?.destination?.route ?: Screens.Start.name
            )
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }

    ) { innerPadding ->
        /**
        Manage navigation from a central point to make refactoring easier
         */
        NavHost(
            navController = navController,
            startDestination = Screens.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screens.Start.name) {
                val tabData = listOf("News", "Tourists")
                val pagerState = rememberPagerState(0)

                Column(modifier = Modifier.fillMaxSize()) {

                    TabLayout(tabData, pagerState)
                    TabContent(
                        tabData,
                        pagerState,
                        newsViewModel,
                        touristViewModel,
                        onNextButtonClicked = {
                            /*
                            Pass the Tourist details to the details page because the respose for tourist
                            details is the same. Saves the extra network call and allows details to be viewed
                            when offline
                             */
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "data",
                                it
                            )
                            navController.navigate(Screens.TouristDetails.name)
                        })
                }
            }

            composable(route = Screens.TouristDetails.name) {
                val data =
                    navController.previousBackStackEntry?.savedStateHandle?.get<Tourist>("data")
                if (data != null) {
                    TouristDetails(data)
                }

            }

        }
    }


}



