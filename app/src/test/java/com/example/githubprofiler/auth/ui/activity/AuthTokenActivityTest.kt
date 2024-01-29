package com.example.githubprofiler.auth.ui.activity

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubprofiler.auth.ui.fragment.AuthTokenFragment
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class AuthTokenActivityTest {

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `test AuthTokenActivity`() {
        launchActivity<AuthTokenActivity>().use { scenario ->
            scenario.onActivity { activity ->
                val authTokenFragment = activity.supportFragmentManager.fragments.firstOrNull {
                    it is AuthTokenFragment
                }
                assertThat(authTokenFragment).isNotNull()
            }
        }
    }
}
