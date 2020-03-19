package com.exequieltiglao.covid

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import com.exequieltiglao.covid.root.RootBuilder
import com.exequieltiglao.covid.root.RootInteractor
import com.exequieltiglao.covid.utils.ActivityResultService
import com.exequieltiglao.covid.utils.AndroidEventsService
import com.exequieltiglao.covid.utils.BackPressService
import com.tbruyelle.rxpermissions2.RxPermissions
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.LinkedBlockingDeque

open class RootActivity : RibActivity(), AndroidEventsService {

    private var rootInteractor: RootInteractor? = null
    private val rootLifecycleSubject = PublishSubject.create<RootLifecycleEvent>()
    private lateinit var rxPermissions: RxPermissions

    private val activityResultListeners = LinkedBlockingDeque<ActivityResultService.Listener>()
    private val backpressListeners = LinkedBlockingDeque<BackPressService.Listener>()

    override fun onCreate(savedInstanceState: Bundle?) {
        rxPermissions = RxPermissions(this)
        super.onCreate(savedInstanceState)
        rootLifecycleSubject.onNext(RootLifecycleEvent.OnCreate(savedInstanceState))
    }

    override fun createRouter(parentViewGroup: ViewGroup?): ViewRouter<*, *, *>? {
        val rootRouter = RootBuilder(object : RootBuilder.ParentComponent {}).build(
            parentViewGroup,
            rootActivity = this,
            androidEventsService = this,
            rootLifecycleSteam = rootLifecycleSubject.hide()
        )

        rootInteractor = rootRouter.interactor
        return rootRouter

    }

    override fun addActivtyResultListener(listener: ActivityResultService.Listener) {
        activityResultListeners.add(listener)
    }

    override fun removeActivityResultListener(listener: ActivityResultService.Listener) {
        activityResultListeners.remove(listener)
    }

    override fun addBackPressListener(listener: BackPressService.Listener) {
        activityResultListeners.add(listener)
    }

    override fun removeBackPressListener(listener: BackPressService.Listener) {
        activityResultListeners.remove(listener)
    }


    private val TIME_INTERVAL = 2000 // # milliseconds, desired time passed between two back presses.
    private var backPressedTime: Long = 0

    override fun onBackPressed() {
        super.onBackPressed()
        backpressListeners.descendingIterator().forEach {
            if (it.onBackPressed())
                return
        }
        super.onBackPressed()

        if (backPressedTime + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }

        backPressedTime = System.currentTimeMillis()
    }

    override fun onStart() {
        super.onStart()
        rootLifecycleSubject.onNext(RootLifecycleEvent.OnStart)
    }

    override fun onResume() {
        super.onResume()
        rootLifecycleSubject.onNext(RootLifecycleEvent.OnResume)
    }

    override fun onPause() {
        super.onPause()
        rootLifecycleSubject.onNext(RootLifecycleEvent.OnPause)
    }

    override fun onDestroy() {
        super.onDestroy()
        rootLifecycleSubject.onNext(RootLifecycleEvent.OnDestroy)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        rootLifecycleSubject.onNext(RootLifecycleEvent.OnLowMemory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultListeners.descendingIterator().forEach { it.onActivityResult(requestCode, resultCode, data) }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        rootLifecycleSubject.onNext(RootLifecycleEvent.OnSaveInstanceState(outState))
    }

}