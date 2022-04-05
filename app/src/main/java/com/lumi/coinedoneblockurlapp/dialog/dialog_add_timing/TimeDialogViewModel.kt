package com.lumi.coinedoneblockurlapp.dialog.dialog_add_timing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimeDialogViewModel: ViewModel() {

    val fromTime = MutableLiveData<String>()
    val toTime = MutableLiveData<String>()

    fun sendTime(fromTime : String, toTime : String){
        this.fromTime.value = fromTime
        this.toTime.value = toTime
    }

}