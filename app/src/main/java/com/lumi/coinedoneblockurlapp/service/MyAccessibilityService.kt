package com.lumi.coinedoneblockurlapp.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.lumi.coinedoneblockurlapp.preferences.SharedPrefs
import com.lumi.coinedoneblockurlapp.ui.startup.AccessDeniedActivity
import com.lumi.coinedoneblockurlapp.ui.startup.SplashActivity

class MyAccessibilityService : AccessibilityService() {

    val customSelectUrl = arrayOf<String>("facebook.com", "twitter.com", "instagram.com",
            "reddit.com", "9gag.com/", "amazon.in")

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("TAG==>", "onAccessibilityEvent")
        val source = event!!.source

        if (source == null) {
            return;
        }

        var packageName = source.packageName

        var browser_list = "com.android.chrome,com.other_url"

//        if (!BROWSER_LIST?.contains(packageName)) {
//
//            Log.d("TAG==>", "!browserList.contains")
//            return;
//        }

        if (event.getEventType()
                == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            if (!browser_list.contains(packageName)) {

                Log.d("TAG==>", "!browserList.contains")
                return;
            }
        } else {
            Log.d("TAG==>", "!browserList.contains")
            return;
        }

        try {

            var evType: String = AccessibilityEvent.eventTypeToString(event.eventType)
            if (evType.contains("WINDOW")) {
                var nodeInfo: AccessibilityNodeInfo = event.source;

                getUrlsFromViews(nodeInfo)

            }

        } catch (ex: StackOverflowError) {
            ex.printStackTrace();
        } catch (ex: Exception) {
            ex.printStackTrace();
        }

    }


    public fun getUrlsFromViews(info: AccessibilityNodeInfo) {

        Log.d("TAG==>", "666666")

        try {
            if (info == null)
                return

            if (info.text != null && info.text.length > 0) {
                val capturedText: String = info.text.toString()
                Log.d("TAG==>", "777777")
                Log.d("TAG==>", "777777  $capturedText")

                if (SharedPrefs.getBoolean("is_checked_blacklist", false)) {

                    for (selectedUrl in customSelectUrl) {
                        Log.d("TAG==>", "88888888" + selectedUrl)
                        Log.d("TAG==>", "88888888++" + capturedText)
                        if (capturedText.contains(selectedUrl)) {
                            Log.d("TAG==>", "9999999999")
                            var intent = Intent(applicationContext, AccessDeniedActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            applicationContext.startActivity(intent)
                            return
                        }
                    }
                } else {
                    var intent = Intent(applicationContext, AccessDeniedActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    applicationContext.startActivity(intent)
                }

            }

        } catch (ex: StackOverflowError) {
            ex.printStackTrace();
        } catch (ex: Exception) {
            ex.printStackTrace();
        }

    }


    override fun onInterrupt() {
        Log.d("TAG==>", "onInterrupt")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("TAG==>", "onServiceConnected")
    }
}