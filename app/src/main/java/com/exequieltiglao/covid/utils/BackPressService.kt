package com.exequieltiglao.covid.utils

import android.content.Intent

interface BackPressService {
    fun addBackPressListener(listener: Listener)
    fun removeBackPressListener(listener: Listener)
    interface Listener : ActivityResultService.Listener {
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        fun onBackPressed(): Boolean
    }
}