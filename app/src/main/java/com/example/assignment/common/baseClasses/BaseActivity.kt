package com.example.assignment.common.baseClasses

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.data.remote.apiCallAndReciver.ApiCallsImplementer
import com.example.assignment.ui.MatchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private val matchFragmentViewModelEvent: MatchFragmentViewModel by viewModels()

    @Inject
    lateinit var apiCallsImplementer: ApiCallsImplementer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        setApiObserver()
    }

    private fun setApiObserver() {
        matchFragmentViewModelEvent.getPersonList.observe(this, { event ->

            event.getContentIfNotHandled()?.let(apiCallsImplementer::personListResponse)
        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val v = currentFocus
        try {
            if (v != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) &&
                v is EditText &&
                !v.javaClass.name.startsWith("android.webkit.")
            ) {
                val sourceCoordinates = IntArray(2)
                v.getLocationOnScreen(sourceCoordinates)
                val x = ev.rawX + v.getLeft() - sourceCoordinates[0]
                val y = ev.rawY + v.getTop() - sourceCoordinates[1]
                if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                    hideKeyboard(this)
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboard(activity: Activity?) {
        if (activity != null && activity.window != null) {
            activity.window.decorView
            val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}