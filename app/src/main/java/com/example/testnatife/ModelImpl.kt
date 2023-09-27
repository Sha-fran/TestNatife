package com.example.testnatife

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ModelImpl:Model {

    override fun getList(): MutableLiveData<List<Data>> {
        val api = ApiClient.client.create(ApiInterface::class.java)
        val listLiveData = MutableLiveData<List<Data>>()

        api.getGif()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val list = it.data
                listLiveData.postValue(list)
            },
                {
                    Log.e("Error", "Error")
                })
        return listLiveData
    }
}
