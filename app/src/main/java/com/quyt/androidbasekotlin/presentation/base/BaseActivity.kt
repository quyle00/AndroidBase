package com.quyt.androidbasekotlin.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    private lateinit var mViewDataBinding: B

    val binding: B get() = mViewDataBinding

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun onViewReady(savedInstance: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)
        if (getLayoutId() != 0) {
            mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
            onViewReady(savedInstanceState)
        }
    }
}