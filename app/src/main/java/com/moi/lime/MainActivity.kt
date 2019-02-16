package com.moi.lime

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.moi.lime.core.rxjava.RxBus
import com.moi.lime.vo.SignInExpireEvent
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var disposable :Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                statusBarColor = Color.TRANSPARENT
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }
        setContentView(R.layout.activity_main)
        disposable = RxBus.INSTANCE.toFlowable<SignInExpireEvent>()
                .subscribe({
                    findNavController(R.id.frag_nav_simple).navigate(MainNavDirections.actionGlobalSignInFragment())
                },{})
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navigationController.navigateUp()
//    }

//    private fun testWork() {
//        val constraints = Constraints.Builder()
//                .setRequiresCharging(true)
//                .build()
//        val pullRequest = OneTimeWorkRequestBuilder<PullWorker>()
//                .setConstraints(constraints)
//                .build()
//
//        WorkManager.getInstance().enqueue(pullRequest)
//
//        Logger.INS.d(pullRequest.id.toString())
//
//        WorkManager.getInstance().getWorkInfoByIdLiveData(pullRequest.id)
//                .observe(this, Observer { status ->
//                    status?.let {
//                        Logger.INS.d(it.state.name)
//                        Logger.INS.d(it.outputData.getString(KEY_RESULT) ?: "")
//                    }
//                })
//    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}
