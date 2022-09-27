package com.example.aagniyaproject.user

import kotlinx.coroutines.flow.Flow

class MyRepository(private val myDataBase: MyDataBase) {

    fun register(user: MyTable)= myDataBase.studentDao().register(user)

    fun verifyUserLogin(userName: String, password: String): Flow<MyTable?>? {
        return myDataBase.studentDao().verifyUserLogin(userName, password)
    }

    fun findByEmail(email: String): Flow<MyTable?>? {
        return myDataBase.studentDao().findByEmail(email)
    }

}