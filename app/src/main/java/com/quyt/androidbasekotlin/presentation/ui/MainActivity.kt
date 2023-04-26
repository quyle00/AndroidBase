package com.quyt.androidbasekotlin.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.quyt.androidbasekotlin.R
import com.quyt.androidbasekotlin.databinding.ActivityMainBinding
import com.quyt.androidbasekotlin.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onViewReady(savedInstance: Bundle?) {

    }
}