package com.vm3dir_mobilprog_catshelter.repository

import com.vm3dir_mobilprog_catshelter.data.Cat
import com.vm3dir_mobilprog_catshelter.data.CatDao
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*

class CatRepository(private val catDao: CatDao) {
    
    fun getAllAvailableCats(): Flow<List<Cat>> = catDao.getAllAvailableCats()
    
    fun getAllAdoptedCats(): Flow<List<Cat>> = catDao.getAllAdoptedCats()
    
    fun getAllCats(): Flow<List<Cat>> = catDao.getAllCats()
    
    suspend fun getCatById(catId: Long): Cat? = catDao.getCatById(catId)
    
    suspend fun insertCat(cat: Cat): Long = catDao.insertCat(cat)
    
    suspend fun updateCat(cat: Cat) = catDao.updateCat(cat)
    
    suspend fun deleteCat(cat: Cat) = catDao.deleteCat(cat)
    
    suspend fun adoptCat(catId: Long) {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        catDao.markAsAdopted(catId, currentDate)
    }
    
    suspend fun makeCatAvailable(catId: Long) = catDao.markAsAvailable(catId)
}

