package com.moi.lime

import androidx.annotation.IdRes
import androidx.navigation.NavController
import com.moi.lime.util.mock

class TestMainActivity : MainActivity() {
    val navController = mock<NavController>()
    override fun navController(@IdRes viewId: Int) = navController
    override fun initNavGraph() {

    }
}