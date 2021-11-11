package com.example.assignment.common.baseClasses

import android.os.Bundle
import com.example.assignment.common.utills.IsLoadingEvent
import com.example.assignment.databinding.ActivityMainBinding
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.BACKGROUND)
    fun onEvent(isLoading: IsLoadingEvent) {
        mainBinding.isLoading = isLoading.value
        mainBinding.notifyChange()
    }
}