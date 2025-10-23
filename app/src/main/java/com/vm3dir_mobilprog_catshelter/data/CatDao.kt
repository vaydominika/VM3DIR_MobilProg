package com.vm3dir_mobilprog_catshelter.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cats WHERE isAdopted = 0 ORDER BY name ASC")
    fun getAllAvailableCats(): Flow<List<Cat>>
    
    @Query("SELECT * FROM cats WHERE isAdopted = 1 ORDER BY adoptionDate DESC")
    fun getAllAdoptedCats(): Flow<List<Cat>>
    
    @Query("SELECT * FROM cats ORDER BY name ASC")
    fun getAllCats(): Flow<List<Cat>>
    
    @Query("SELECT * FROM cats WHERE id = :catId")
    suspend fun getCatById(catId: Long): Cat?
    
    @Insert
    suspend fun insertCat(cat: Cat): Long
    
    @Update
    suspend fun updateCat(cat: Cat)
    
    @Delete
    suspend fun deleteCat(cat: Cat)
    
    @Query("UPDATE cats SET isAdopted = 1, adoptionDate = :adoptionDate WHERE id = :catId")
    suspend fun markAsAdopted(catId: Long, adoptionDate: String)
    
    @Query("UPDATE cats SET isAdopted = 0, adoptionDate = NULL WHERE id = :catId")
    suspend fun markAsAvailable(catId: Long)
}

