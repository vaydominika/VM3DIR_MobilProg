package com.vm3dir_mobilprog_catshelter.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vm3dir_mobilprog_catshelter.data.Cat
import com.vm3dir_mobilprog_catshelter.ui.components.AddEditCatDialog
import com.vm3dir_mobilprog_catshelter.ui.components.CatCard
import com.vm3dir_mobilprog_catshelter.ui.theme.*
import com.vm3dir_mobilprog_catshelter.viewmodel.CatViewModel
import androidx.compose.material.icons.filled.Pets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    viewModel: CatViewModel,
    modifier: Modifier = Modifier
) {
    val availableCats by viewModel.availableCats.collectAsStateWithLifecycle()
    val adoptedCats by viewModel.adoptedCats.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    
    var showAddDialog by remember { mutableStateOf(false) }
    var catToEdit by remember { mutableStateOf<Cat?>(null) }
    var showAdoptedCats by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf<Cat?>(null) }
    
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightPink)
        ) {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Pets,
                            contentDescription = null,
                            tint = TextPink,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Cat Shelter",
                            color = TextPink,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { showAdoptedCats = !showAdoptedCats }
                    ) {
                        Icon(
                            imageVector = if (showAdoptedCats) Icons.Default.Home else Icons.Default.Favorite,
                            contentDescription = if (showAdoptedCats) "Show Available" else "Show Adopted",
                            tint = TextPink
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SoftWhite
                )
            )
            
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = AccentPink,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
                
                showAdoptedCats && adoptedCats.isEmpty() -> {
                    EmptyState(
                        icon = Icons.Default.Favorite,
                        title = "No Adopted Cats",
                        subtitle = "Cats that have been adopted will appear here",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                
                !showAdoptedCats && availableCats.isEmpty() -> {
                    EmptyState(
                        icon = Icons.Default.Pets,
                        title = "No Cats in Shelter",
                        subtitle = "Add your first cat to get started!",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = AccentPink)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = if (showAdoptedCats) Icons.Default.Home else Icons.Default.Pets,
                                        contentDescription = null,
                                        tint = SoftWhite,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = if (showAdoptedCats) "Recently Adopted" else "Available for Adoption",
                                        color = SoftWhite,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        
                        val catsToShow = if (showAdoptedCats) adoptedCats else availableCats
                        items(catsToShow) { cat ->
                            CatCard(
                                cat = cat,
                                onEditClick = { catToEdit = cat },
                                onAdoptClick = { catToAdopt ->
                                    if (catToAdopt.isAdopted) {
                                        viewModel.makeCatAvailable(catToAdopt.id)
                                    } else {
                                        viewModel.adoptCat(catToAdopt.id)
                                    }
                                },
                                onDeleteClick = { showDeleteConfirmation = cat },
                                onSpecialNeedsToggle = { catToUpdate, specialNeeds ->
                                    val updatedCat = catToUpdate.copy(specialNeeds = specialNeeds)
                                    viewModel.updateCat(updatedCat)
                                }
                            )
                        }
                        
                        item {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
            }
        }
        
        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = AccentPink,
            contentColor = SoftWhite
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Cat"
            )
        }
    }
    
    if (showAddDialog || catToEdit != null) {
        AddEditCatDialog(
            cat = catToEdit,
            onDismiss = {
                showAddDialog = false
                catToEdit = null
            },
            onSave = { cat ->
                if (catToEdit != null) {
                    viewModel.updateCat(cat)
                } else {
                    viewModel.addCat(cat)
                }
                showAddDialog = false
                catToEdit = null
            }
        )
    }
    
    showDeleteConfirmation?.let { cat ->
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = null },
            title = {
                Text(
                    text = "Delete Cat",
                    color = TextPink
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to remove ${cat.name} from the shelter? This action cannot be undone.",
                    color = TextPink
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteCat(cat)
                        showDeleteConfirmation = null
                    }
                ) {
                    Text(
                        text = "Delete",
                        color = DarkPink
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteConfirmation = null }
                ) {
                    Text(
                        text = "Cancel",
                        color = TextPink
                    )
                }
            },
            containerColor = SoftWhite
        )
    }
}

@Composable
private fun EmptyState(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Pink200
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = TextPink,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            color = Pink200,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

