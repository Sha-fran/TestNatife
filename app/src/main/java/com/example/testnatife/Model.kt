package com.example.testnatife

import androidx.lifecycle.MutableLiveData

interface Model {

    fun getList():MutableLiveData<List<Data>>
}
