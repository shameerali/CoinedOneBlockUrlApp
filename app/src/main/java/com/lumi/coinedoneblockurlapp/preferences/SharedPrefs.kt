package com.lumi.coinedoneblockurlapp.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs {



    companion object {
        public lateinit var sharedPreferences: SharedPreferences
        public val PREFERENCE_FILE_KEY = "com.lumi.coinedoneblockurlapp"


        fun initializeSharedPref(context: Context) {
            SharedPrefs.sharedPreferences =
                context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
            setPreferences(sharedPreferences)
        }

        fun setPreferences(preferences: SharedPreferences) {
            sharedPreferences = preferences
        }

        fun setBoolean(key: String, value: Boolean) {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun getBoolean(key: String, def: Boolean): Boolean {

            return sharedPreferences.getBoolean(key, def)
        }

        fun setInt(key: String, value: Int) {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt(key, value)
            editor.apply()
        }

        fun getInt(key: String, def: Int): Int {

            return sharedPreferences.getInt(key, def)
        }

        public class Key {
            public var IS_TIME_ADDED: String = "is_time_added"
            public var FROM_TIME: String = "from_time"
            public var TO_TIME: String = "to_time"
            public var IS_CHECKED_BLACKLIST: String = "is_checked_blacklist"
        }

    }





    fun getPreferences(): SharedPreferences {
        return sharedPreferences
    }

    fun clearAllPreferences() {
        sharedPreferences.edit().clear().commit()
    }


    fun setString(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, def: String): String {

        return sharedPreferences.getString(key, def).toString()
    }

//    companion object {

//    }


}