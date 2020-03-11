package com.moi.lime

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.lime.testing.OpenForTesting
import com.moi.lime.api.SignInExpireInterceptor
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

@OpenForTesting
class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    @Inject
    lateinit var signInExpireInterceptor: SignInExpireInterceptor

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
        initNavGraph()
        signInExpireInterceptor.onLoginExpire = {
            navController(R.id.frag_nav_simple).navigate(MainNavDirections.actionGlobalSignInFragment())
        }

    }


    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    /**
     * Created to be able to override in tests
     */
    fun navController(@IdRes viewId: Int) = findNavController(viewId)

    /**
     * Created to be able to override in tests
     */
    fun initNavGraph() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frag_nav_simple) as NavHostFragment
        val navMain = navHostFragment.navController.navInflater.inflate(R.navigation.nav_main)
        navHostFragment.navController.graph = navMain

    }
}
