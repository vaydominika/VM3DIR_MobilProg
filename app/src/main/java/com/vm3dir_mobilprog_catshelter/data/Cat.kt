package com.vm3dir_mobilprog_catshelter.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cats")
data class Cat(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val breed: String,
    val color: String,
    val gender: String,
    val description: String,
    val arrivalDate: String,
    val medicalNotes: String,
    val isAdopted: Boolean = false,
    val adoptionDate: String? = null,
    val specialNeeds: Boolean = false
) : Serializable

