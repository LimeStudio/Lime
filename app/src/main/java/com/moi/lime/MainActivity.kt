package com.moi.lime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moi.lime.util.Logger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.INS.setUpLogger(BuildConfig.DEBUG,"Lime")
        Logger.INS.d("ddudududu")
    }
}
