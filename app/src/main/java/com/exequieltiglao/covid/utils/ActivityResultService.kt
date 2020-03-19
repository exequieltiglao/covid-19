package com.exequieltiglao.covid.utils

import android.content.Intent

interface ActivityResultService {
    fun addActivtyResultListener(listener: Listener)
    fun removeActivityResultListener(listener: Listener)
    interface Listener {
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}