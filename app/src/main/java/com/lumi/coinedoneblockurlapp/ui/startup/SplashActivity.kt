package com.lumi.coinedoneblockurlapp.ui.startup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.lumi.coinedoneblockurlapp.base.BaseActivity
import com.lumi.coinedoneblockurlapp.databinding.ActivitySplashBinding
import com.lumi.coinedoneblockurlapp.ui.schedule.ScheduleActivity

class SplashActivity : BaseActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        initView()


    }

    override fun initView() {
        Handler(Looper.getMainLooper()).
        postDelayed({

//            var url = "data:image/png;base64,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";
//            var url = "https://prutech.org/callefyapi";
//
//            var intent = Intent(Intent.ACTION_VIEW)
//            intent.setData(Uri.parse(url))
//            startActivity(intent)

                      startActivity(Intent(this, ScheduleActivity::class.java))
            finish()

        },2000)
    }

    override fun setToolbar(): Boolean {
        return false;
    }

    override fun hideStatusbar(): Boolean {
        return true;
    }

    override fun setFullScreen(): Boolean {
        return true;
    }
}