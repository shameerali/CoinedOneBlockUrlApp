package com.lumi.coinedoneblockurlapp

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.lumi.coinedoneblockurlapp.preferences.SharedPrefs

class App : MultiDexApplication() {

    val TAG: String = App::class.simpleName.toString()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: APP"+TAG)
        SharedPrefs.initializeSharedPref(this)
    }
}