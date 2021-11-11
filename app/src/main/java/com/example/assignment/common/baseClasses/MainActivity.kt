package com.example.assignment.common.baseClasses

import android.os.Bundle
import com.example.assignment.common.Utills.IsLoadingEvent
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }

    override fun onEvent(isLoading: IsLoadingEvent) {
        mainBinding.isLoading = isLoading.value
        mainBinding.notifyChange()
    }
}