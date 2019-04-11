package com.moi.lime.ui.home.recommend

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.moi.lime.vo.Resource
import com.moi.lime.vo.Status
import java.util.*

class LoadingRecommendSwitcher(private val sp: SharedPreferences, private val resettingTime: Int) {

    companion object {
        const val RECOMMEND_DAY_KEY = "LoadingRecommendSwitcher.key"
    }

    fun isShouldFetchFromDb(currentTime: Long): Boolean {
        val lastSuccessfulDate = getLastSuccessfulDate() ?: return false
        return lastSuccessfulDate.time > getResettingDate(currentTime).time
    }

    fun bindRecommendResource(lifecycleOwner: LifecycleOwner, liveData: LiveData<out Resource<*>>) {
        liveData.observe(lifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                sp.edit().apply {
                    putLong(RECOMMEND_DAY_KEY,System.currentTimeMillis())
                    apply()
                }
            }
        })
    }

    private fun getResettingDate(currentTime: Long): Date {
        val date = Date(currentTime)
        val calendar = GregorianCalendar(TimeZone.getTimeZone("Asia/Shanghai"))
        calendar.time = date
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        if (hour < resettingTime) {
            calendar.add(Calendar.DATE, -1)
            calendar.set(Calendar.HOUR_OF_DAY, resettingTime)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, resettingTime)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
        }
        return calendar.time
    }

    private fun getLastSuccessfulDate(): Date? {
        val time = sp.getLong(RECOMMEND_DAY_KEY, -1L)
        if (time == -1L) return null
        return Date(time)
    }

}