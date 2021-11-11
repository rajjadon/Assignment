package com.example.assignment.common.baseClasses

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.common.Utills.IsLoadingEvent
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
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

    @Subscribe(sticky = true, threadMode = ThreadMode.BACKGROUND)
    abstract fun onEvent(isLoading: IsLoadingEvent)

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