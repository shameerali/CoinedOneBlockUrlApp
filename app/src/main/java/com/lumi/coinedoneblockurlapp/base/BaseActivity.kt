package com.lumi.coinedoneblockurlapp.base

import android.R
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


public abstract class BaseActivity : AppCompatActivity() {
    val TAG: String = BaseActivity::class.simpleName.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (hideStatusbar()){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            );

        }

        if (setFullScreen()){
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }

        if (setToolbar()) {
//            val toolbar: Toolbar = findViewById(R.id.toolbar)
//            setSupportActionBar(toolbar)
        }

    }

    public abstract fun initView()

    abstract fun setToolbar(): Boolean

    abstract fun hideStatusbar(): Boolean

    abstract fun setFullScreen(): Boolean




}