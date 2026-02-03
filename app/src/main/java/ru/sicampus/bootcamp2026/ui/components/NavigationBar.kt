package ru.sicampus.bootcamp2026.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.sicampus.bootcamp2026.ui.screens.InvitationListScreen
import ru.sicampus.bootcamp2026.ui.screens.ProfileScreen
import ru.sicampus.bootcamp2026.ui.screens.TimetableScreen
import ru.sicampus.bootcamp2026.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf("Timetable") }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                selectedItem = selectedItem,
                onItemSelected = { item ->
                    selectedItem = item
                    when (item) {
                        "Invitations" -> navController.navigate("invitations")
                        "Timetable" -> navController.navigate("timetable")
                        "Profile" -> navController.navigate("profile")
                    }
                }
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = "timetable",
            modifier = Modifier.padding(contentPadding)
        ) {
            composable("invitations") { InvitationListScreen() }
            composable("timetable") { TimetableScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}

@Composable
fun BottomNavBar(selectedItem: String, onItemSelected: (String) -> Unit) {
    NavigationBar {
        val items = listOf("Invitations", "Timetable", "Profile")

        items.forEach { item ->
            NavigationBarItem(
                selected = selectedItem == item,
                onClick = { onItemSelected(item) },
                icon = {
                    when (item) {
                        "Invitations" -> Icon(Icons.Default.MailOutline, contentDescription = "Invitations")
                        "TimeTable" -> Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = "Timetable")
                        "Profile" -> Icon(Icons.Outlined.Person, contentDescription = "Profile")
                        else -> Icon(painterResource(id = R.drawable.calendar), contentDescription = item)
                    }
                },
                label = { Text(item) }
            )
        }
    }
}