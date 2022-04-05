package com.lumi.coinedoneblockurlapp.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.lumi.coinedoneblockurlapp.R
import com.lumi.coinedoneblockurlapp.base.BaseActivity
import com.lumi.coinedoneblockurlapp.databinding.ActivityScheduleBinding
import com.lumi.coinedoneblockurlapp.ui.schedule.schedule_start.ScheduleStartFragment
import java.util.*

class ScheduleActivity : BaseActivity() {

    private lateinit var binding: ActivityScheduleBinding

    var mActiveFragment: Fragment? = null
    var mFragmentListStack: Hashtable<String, Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
        launchFragment(ScheduleStartFragment.getInstance(), "SCHEDULE_START")

        if (!checkAccessibilityPermission()){
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    override fun initView() {
        mFragmentListStack?.put("SCHEDULE_START", ScheduleStartFragment.getInstance())
    }

    private fun checkAccessibilityPermission(): Boolean {
        var accessEnable = 0
        try {
            accessEnable = Settings.Secure.getInt(this.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED)

            Log.d("TAG==>", "checkAccessibilityPermission:++++ ")
        }catch (e : Settings.SettingNotFoundException){

            Log.d("TAG==>", "checkAccessibilityPermission: ")
            e.printStackTrace()

        }



        return if (accessEnable == 0){
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            true
        }else{
            false
        }

    }

    fun launchFragment(fragment: Fragment, fragment_tag: String) {

        supportFragmentManager.beginTransaction()
            .replace(binding.containerFragment.id, fragment, fragment_tag)
            .commit()
    }


    override fun setToolbar(): Boolean {
        return false;
    }

    override fun hideStatusbar(): Boolean {
        return false;
    }

    override fun setFullScreen(): Boolean {
        return false;
    }
}