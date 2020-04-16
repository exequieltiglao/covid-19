package com.exequieltiglao.covid.utils

import android.view.View

interface DialogHelper {

    fun showLoading(view: View)
    fun dismissAll()
    fun hideAll()
    fun showInternetPermission(listener: () -> Unit)
    fun showConfirmDialog(title: String, message: String, buttonText: String, listener: () -> Unit )
}