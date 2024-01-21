package com.example.githubprofiler.auth.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.commit
import com.example.githubprofiler.auth.ui.fragment.AuthTokenFragment
import com.example.githubprofiler.common.ui.activity.BaseActivity
import com.example.guthubprofiler.databinding.ActivityAuthTokenBinding

class AuthTokenActivity : BaseActivity<ActivityAuthTokenBinding>() {
    override fun initUI(layoutInflater: LayoutInflater): ActivityAuthTokenBinding {
        return ActivityAuthTokenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            add(binding.fcvFragmentContainer.id, AuthTokenFragment.newInstance())
        }
    }
}
