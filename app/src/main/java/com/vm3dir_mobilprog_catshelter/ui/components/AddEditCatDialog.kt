package com.vm3dir_mobilprog_catshelter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vm3dir_mobilprog_catshelter.data.Cat
import com.vm3dir_mobilprog_catshelter.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCatDialog(
    cat: Cat?,
    onDismiss: () -> Unit,
    onSave: (Cat) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf(cat?.name ?: "") }
    var age by remember { mutableStateOf(cat?.age?.toString() ?: "") }
    var breed by remember { mutableStateOf(cat?.breed ?: "") }
    var color by remember { mutableStateOf(cat?.color ?: "") }
    var gender by remember { mutableStateOf(cat?.gender ?: "Female") }
    var description by remember { mutableStateOf(cat?.description ?: "") }
    var arrivalDate by remember { mutableStateOf(cat?.arrivalDate ?: "") }
    var medicalNotes by remember { mutableStateOf(cat?.medicalNotes ?: "") }
    var specialNeeds by remember { mutableStateOf(cat?.specialNeeds ?: false) }
    
    var nameError by remember { mutableStateOf("") }
    var colorError by remember { mutableStateOf("") }
    var arrivalDateError by remember { mutableStateOf("") }
    var genderError by remember { mutableStateOf("") }
    var showErrors by remember { mutableStateOf(false) }
    
    var showGenderMenu by remember { mutableStateOf(false) }
    val genderOptions = listOf("Female", "Male")
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (cat == null) "Add New Cat" else "Edit Cat",
                color = TextPink,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { 
                        name = it
                        if (showErrors && nameError.isNotEmpty()) {
                            nameError = if (it.isBlank()) "Name is required" else ""
                        }
                    },
                    label = { Text("Cat Name*", color = TextPink) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = nameError.isNotEmpty(),
                    supportingText = if (nameError.isNotEmpty()) {
                        { Text(nameError, color = MaterialTheme.colorScheme.error) }
                    } else null,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentPink,
                        unfocusedBorderColor = Pink200,
                        focusedLabelColor = AccentPink,
                        unfocusedLabelColor = TextPink,
                        errorBorderColor = MaterialTheme.colorScheme.error,
                        errorLabelColor = MaterialTheme.colorScheme.error
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Pets,
                            contentDescription = null,
                            tint = AccentPink
                        )
                    }
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        label = { Text("Age", color = TextPink) },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AccentPink,
                            unfocusedBorderColor = Pink200,
                            focusedLabelColor = AccentPink,
                            unfocusedLabelColor = TextPink
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null,
                                tint = AccentPink
                            )
                        }
                    )
                    
                    OutlinedTextField(
                        value = breed,
                        onValueChange = { breed = it },
                        label = { Text("Breed", color = TextPink) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AccentPink,
                            unfocusedBorderColor = Pink200,
                            focusedLabelColor = AccentPink,
                            unfocusedLabelColor = TextPink
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Category,
                                contentDescription = null,
                                tint = AccentPink
                            )
                        }
                    )
                }
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = color,
                        onValueChange = { 
                            color = it
                            if (showErrors && colorError.isNotEmpty()) {
                                colorError = if (it.isBlank()) "Color is required" else ""
                            }
                        },
                        label = { Text("Color*", color = TextPink) },
                        modifier = Modifier.weight(1f),
                        isError = colorError.isNotEmpty(),
                        supportingText = if (colorError.isNotEmpty()) {
                            { Text(colorError, color = MaterialTheme.colorScheme.error) }
                        } else null,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AccentPink,
                            unfocusedBorderColor = Pink200,
                            focusedLabelColor = AccentPink,
                            unfocusedLabelColor = TextPink,
                            errorBorderColor = MaterialTheme.colorScheme.error,
                            errorLabelColor = MaterialTheme.colorScheme.error
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Palette,
                                contentDescription = null,
                                tint = AccentPink
                            )
                        }
                    )
                    
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = gender,
                            onValueChange = { },
                            readOnly = true,
                            label = { Text("Gender*", color = TextPink) },
                            modifier = Modifier.fillMaxWidth(),
                            isError = genderError.isNotEmpty(),
                            supportingText = if (genderError.isNotEmpty()) {
                                { Text(genderError, color = MaterialTheme.colorScheme.error) }
                            } else null,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = AccentPink,
                                unfocusedBorderColor = Pink200,
                                focusedLabelColor = AccentPink,
                                unfocusedLabelColor = TextPink,
                                errorBorderColor = MaterialTheme.colorScheme.error,
                                errorLabelColor = MaterialTheme.colorScheme.error
                            ),
                            trailingIcon = {
                                IconButton(onClick = { showGenderMenu = true }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Select Gender",
                                        tint = AccentPink
                                    )
                                }
                            }
                        )
                        
                        DropdownMenu(
                            expanded = showGenderMenu,
                            onDismissRequest = { showGenderMenu = false }
                        ) {
                            genderOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        gender = option
                                        showGenderMenu = false
                                        if (showErrors && genderError.isNotEmpty()) {
                                            genderError = ""
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
                
                OutlinedTextField(
                    value = arrivalDate,
                    onValueChange = { 
                        arrivalDate = it
                        if (showErrors && arrivalDateError.isNotEmpty()) {
                            arrivalDateError = if (it.isBlank()) "Arrival date is required" else ""
                        }
                    },
                    label = { Text("Arrival Date (YYYY-MM-DD)*", color = TextPink) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = arrivalDateError.isNotEmpty(),
                    supportingText = if (arrivalDateError.isNotEmpty()) {
                        { Text(arrivalDateError, color = MaterialTheme.colorScheme.error) }
                    } else null,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentPink,
                        unfocusedBorderColor = Pink200,
                        focusedLabelColor = AccentPink,
                        unfocusedLabelColor = TextPink,
                        errorBorderColor = MaterialTheme.colorScheme.error,
                        errorLabelColor = MaterialTheme.colorScheme.error
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = AccentPink
                        )
                    }
                )
                
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description", color = TextPink) },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    maxLines = 3,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentPink,
                        unfocusedBorderColor = Pink200,
                        focusedLabelColor = AccentPink,
                        unfocusedLabelColor = TextPink
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = AccentPink
                        )
                    }
                )
                
                OutlinedTextField(
                    value = medicalNotes,
                    onValueChange = { medicalNotes = it },
                    label = { Text("Medical Notes", color = TextPink) },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    maxLines = 3,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentPink,
                        unfocusedBorderColor = Pink200,
                        focusedLabelColor = AccentPink,
                        unfocusedLabelColor = TextPink
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.MedicalServices,
                            contentDescription = null,
                            tint = AccentPink
                        )
                    }
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = if (specialNeeds) AccentPink else LightPink,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable { specialNeeds = !specialNeeds },
                        contentAlignment = Alignment.Center
                    ) {
                        if (specialNeeds) {
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
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    nameError = if (name.isBlank()) "Name is required" else ""
                    colorError = if (color.isBlank()) "Color is required" else ""
                    arrivalDateError = if (arrivalDate.isBlank()) "Arrival date is required" else ""
                    genderError = if (gender.isBlank()) "Gender is required" else ""
                    
                    showErrors = true
                    
                    if (nameError.isEmpty() && colorError.isEmpty() && 
                        arrivalDateError.isEmpty() && genderError.isEmpty()) {
                        val newCat = Cat(
                            id = cat?.id ?: 0,
                            name = name.trim(),
                            age = age.toIntOrNull() ?: 0,
                            breed = breed.trim(),
                            color = color.trim(),
                            gender = gender,
                            description = description.trim(),
                            arrivalDate = arrivalDate.trim(),
                            medicalNotes = medicalNotes.trim(),
                            isAdopted = cat?.isAdopted ?: false,
                            adoptionDate = cat?.adoptionDate,
                            specialNeeds = specialNeeds
                        )
                        onSave(newCat)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentPink,
                    contentColor = SoftWhite
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = if (cat == null) "Add Cat" else "Update Cat",
                    fontWeight = FontWeight.Medium
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = TextPink
                )
            ) {
                Text("Cancel")
            }
        },
        containerColor = SoftWhite,
        shape = RoundedCornerShape(16.dp)
    )
}