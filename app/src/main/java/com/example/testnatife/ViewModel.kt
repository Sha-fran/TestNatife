package com.example.testnatife

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class GifViewModel: ViewModel() {
    private val model:Model = ModelImpl
    private val _uiStateLiveData = MutableLiveData<UIState>(UIState.EmptyList)
    val uiStateLiveData:LiveData<UIState> = _uiStateLiveData

    private val observer = Observer<List<Data>> {
        _uiStateLiveData.postValue(UIState.FilledList(list = it))
    }

    init {
        model.getList().observeForever(observer)
    }
}

sealed class UIState {
    object EmptyList:UIState()
    class FilledList(val list:List<Data>):UIState()
}
