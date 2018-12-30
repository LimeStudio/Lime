package com.moi.lime.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.moi.lime.R
import com.moi.lime.api.MoiService
import com.moi.lime.db.ProfileDao
import com.moi.lime.di.Injectable
import com.moi.lime.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

class FirstFragment : Fragment(), Injectable {
    @Inject
    lateinit var moiService: MoiService
    @Inject
    lateinit var profileDao: ProfileDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?): Unit = view!!.let { view ->
        super.onActivityCreated(savedInstanceState)
        button.setOnClickListener {
            val fragmentArgs = SecondFragmentArgs.Builder("second").build()
            Navigation.findNavController(view)
                    .navigate(R.id.go_to_second_fragment_from_first, fragmentArgs.toBundle())
        }
        login.setOnClickListener { _ ->
            moiService.signInByPhone("18628393484", "xxxxxx")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                Logger.INS.d(it.toString())
                            },
                            {
                                Logger.INS.d(it.message.toString())
                            }
                    )
        }
        testButton.setOnClickListener { _ ->
            moiService.getRecommendationList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                Logger.INS.d(it.toString())
                            },
                            {
                                Logger.INS.d(it.message.toString())
                            }
                    )
        }

//val uri = "Dada"
//        val constraints = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build()
//
//        val data = workDataOf("imageUri" to uri)
//
//        val uploadWork = OneTimeWorkRequestBuilder<UploadPhotoWorker>()
//                .addTag("upload")
//                .setInputData(data)
//                .setConstraints(constraints)
//                .build()
//
//        val compressWork = OneTimeWorkRequestBuilder<UploadPhotoWorker>()
//                .addTag("compress")
//                .build()
//
//        val uploadWork = OneTimeWorkRequestBuilder<UploadPhotoWorker>()
//                .addTag("upload")
//                .setConstraints(constraints)
//                .build()

        //   WorkManager.getInstance().beginWith(compressWork).then(uploadWork).enqueue()
        //WorkManager.getInstance().enqueue(uploadWork,uploadWork)

//        WorkManager.getInstance()
//                .cancelAllWorkByTag("upload")
//
//        val workState = WorkManager.getInstance()
//                .getStatusesByTag("upload")
//
//        workState.observe(this, Observer {
//            //可以通过 WorkStatus 拿到任务状态
//        })
//
//

//        var isFirst = true
//        val height by lazy { backgroundView.height.toFloat() }
//
//        startButton.setOnClickListener {
//
//            if (isFirst){
//                val animation = ValueAnimator.ofFloat(height, height/2)
//                animation.duration = 2000
//                animation.addUpdateListener { valueAnimator: ValueAnimator ->
//                    com.moi.lime.util.Logger.INS.d((valueAnimator.animatedValue as Float).toString())
//                    backgroundView.layoutParams.height = (valueAnimator.animatedValue as Float).toInt()
//                    backgroundView.requestLayout()
//                }
//                animation.start()
//
//                text.animate()
//                        .scaleX(0.5f)
//                        .scaleY(0.5f)
//                        .translationY(-height/2)
//                        .translationX(30f)
//                        .duration=2000
//                isFirst=false
//            }else{
//                val animation = ValueAnimator.ofFloat(height/2, height)
//                animation.duration = 2000
//                animation.addUpdateListener { valueAnimator: ValueAnimator ->
//                    com.moi.lime.util.Logger.INS.d((valueAnimator.animatedValue as Float).toString())
//                    backgroundView.layoutParams.height = (valueAnimator.animatedValue as Float).toInt()
//                    backgroundView.requestLayout()
//                }
//                animation.start()
//
//                text.animate()
//                        .scaleX(1f)
//                        .scaleY(1f)
//                        .translationYBy(height/2)
//                        .translationXBy(-30f)
//                        .duration=2000
//                isFirst=true
//            }
//
//        }
    }
}