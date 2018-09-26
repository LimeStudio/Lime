package com.moi.lime

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.moi.lime.api.MoiService
import com.moi.lime.di.Injectable
import com.moi.lime.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity(),Injectable {

    @Inject lateinit var service:MoiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.INS.setUpLogger(BuildConfig.DEBUG,"Lime")
        Logger.INS.d("ddudududu")

        service.getMusicUrl("33894312")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                   Logger.INS.d("成功")
                },{
                    Logger.INS.d("失败")
                })
    }
}
