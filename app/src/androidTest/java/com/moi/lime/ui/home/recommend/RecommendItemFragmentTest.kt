package com.moi.lime.ui.home.recommend

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.moi.lime.R
import com.moi.lime.db.LimeDbTest
import com.moi.lime.util.DataBindingIdlingResourceRule
import com.moi.lime.util.MusicEntityCreator
import com.moi.lime.util.MusicMapper
import com.moi.lime.util.TestDispatchersRule
import com.moi.lime.vo.MusicInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecommendItemFragmentTest : LimeDbTest() {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    val testDispatchersRule = TestDispatchersRule()


    private lateinit var fragment: RecommendItemFragment

    private lateinit var musicInformation: MusicInformation

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() = runBlocking {
            MusicMapper(MusicEntityCreator.createRecommendMusicEntity(), MusicEntityCreator.createMusicUrlsEntity())
                    .saveMusic(db)
            musicInformation = db.musicInformationDao().getAllMusicInformation().first()
            fragment = RecommendItemFragment.newInstance(musicInformation)
            activityRule.activity.setFragment(fragment)


        // InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    @Test
    fun testUI() {
        onView(withId(R.id.text_name)).check(matches(withText(musicInformation.limeMusic?.name)))
        onView(withId(R.id.text_album)).check(matches(withText(musicInformation.limeAlbum.first().name)))
        onView(withId(R.id.text_artist)).check(matches(withText(musicInformation.limeArtist.first().name)))
        onView(withId(R.id.image_album)).check(matches(isDisplayed()))
    }


}