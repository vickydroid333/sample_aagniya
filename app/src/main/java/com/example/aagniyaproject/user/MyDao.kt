package com.example.aagniyaproject.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Query("SELECT * FROM user_table where email LIKE  :email")
    fun findByEmail(email: String?): Flow<MyTable?>?

    @Insert
    fun register(user: MyTable)

    @Query("SELECT * FROM user_table where user_name LIKE  :user_name AND password LIKE :password")
    fun verifyUserLogin(user_name: String?, password: String?): Flow<MyTable?>?

}