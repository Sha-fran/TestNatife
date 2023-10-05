package com.example.testnatife

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GifViewModel: ViewModel() {
    private val model = ModelImpl
    private val _uiStateLiveData = MutableLiveData<UIState>(UIState.EmptyList)
    val uiStateLiveData:LiveData<UIState> = _uiStateLiveData

    init {
        viewModelScope.launch (Dispatchers.Default) {
            val response = model.getList()

            if (response.isSuccessful) {
                val list = response.body()?.data!!
                _uiStateLiveData.postValue(UIState.FilledList(list))
            } else {
                _uiStateLiveData.postValue(UIState.Error("Error"))
            }
        }
    }
}

sealed class UIState {
    data object EmptyList:UIState()
    class FilledList(val list:List<Data>):UIState()
    class Error(val description:String):UIState()
}
