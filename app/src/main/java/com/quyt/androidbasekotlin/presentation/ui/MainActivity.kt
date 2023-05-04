package com.quyt.androidbasekotlin.presentation.ui

import android.os.Bundle
import com.quyt.androidbasekotlin.R
import com.quyt.androidbasekotlin.databinding.ActivityMainBinding
import com.quyt.androidbasekotlin.presentation.base.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onViewReady(savedInstance: Bundle?) {

    }
}