package com.vm3dir_mobilprog_catshelter.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.* 
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vm3dir_mobilprog_catshelter.data.Cat
import com.vm3dir_mobilprog_catshelter.ui.theme.*
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatCard(
    cat: Cat,
    onEditClick: (Cat) -> Unit,
    onAdoptClick: (Cat) -> Unit,
    onDeleteClick: (Cat) -> Unit,
    onSpecialNeedsToggle: (Cat, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = SoftWhite
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cat.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPink,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                if (cat.isAdopted) {
                    Surface(
                        color = AccentPink,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null,
                                tint = SoftWhite,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "Adopted",
                                color = SoftWhite,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    CatDetailRow(
                        icon = Icons.Default.Pets,
                        text = "${cat.age} years old",
                        color = Pink200
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CatDetailRow(
                        icon = Icons.Default.Category,
                        text = cat.breed,
                        color = Pink200
                    )
                }
                
                Column(modifier = Modifier.weight(1f)) {
                    CatDetailRow(
                        icon = Icons.Default.Palette,
                        text = cat.color,
                        color = Pink200
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CatDetailRow(
                        icon = Icons.Default.CalendarToday,
                        text = cat.arrivalDate,
                        color = Pink200
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    CatDetailRow(
                        icon = if (cat.gender == "Female") Icons.Default.Female else Icons.Default.Male,
                        text = cat.gender,
                        color = if (cat.gender == "Female") Pink200 else DarkPink
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            
            if (cat.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = cat.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextPink,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            if (cat.medicalNotes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = LightPink,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MedicalServices,
                            contentDescription = null,
                            tint = TextPink,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = cat.medicalNotes,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextPink,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = if (cat.specialNeeds) AccentPink else LightPink,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable { onSpecialNeedsToggle(cat, !cat.specialNeeds) },
                    contentAlignment = Alignment.Center
                ) {
                    if (cat.specialNeeds) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Special Needs",
                            tint = SoftWhite,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Text(
                    text = "Special Needs",
                    color = TextPink,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (cat.isAdopted) {
                    OutlinedButton(
                        onClick = { onAdoptClick(cat) },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = AccentPink
                        ),
                        border = BorderStroke(1.dp, AccentPink),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Make Available",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Available")
                    }
                } else {
                    Button(
                        onClick = { onAdoptClick(cat) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AccentPink,
                            contentColor = SoftWhite
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Adopt",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Adopt")
                    }
                }
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onEditClick(cat) },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = LightPink,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = TextPink,
                            containerColor = Color.Transparent
                        ),
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier.size(20.dp),
                            tint = TextPink
                        )
                    }
                    
                    IconButton(
                        onClick = { onDeleteClick(cat) },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = LightPink,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = DarkPink,
                            containerColor = Color.Transparent
                        ),
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier.size(20.dp),
                            tint = DarkPink
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CatDetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = color
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = TextPink,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

