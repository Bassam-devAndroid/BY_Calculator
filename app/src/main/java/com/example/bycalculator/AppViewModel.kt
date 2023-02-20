package com.example.bycalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class AppViewModel: ViewModel() {

    private val _operation = MutableLiveData<String>()
    val operation: LiveData<String>
    get() = _operation
    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
    get() = _result


    fun setResult(string: String){
        _result.value=string;
    }
    fun setOperation(string: String){
        _operation.value=string;
    }

}