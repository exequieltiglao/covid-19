package com.exequieltiglao.covid.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.androidadvance.topsnackbar.TSnackbar
import com.exequieltiglao.covid.R

class DialogHelperImpl (private val context: Context) : DialogHelper {

    private var dialog : Dialog? = null
    private var snackbar: TSnackbar? = null

    override fun showConfirmDialog(title: String, message: String, buttonText: String, listener: () -> Unit ) {
        dialog?.dismiss()
        dialog = MaterialDialog(context).show {
            title(text = title)
            message(text = message)
            positiveButton(text = buttonText) { dialog ->
                dialog.dismiss()
                listener()
            }
            cancelOnTouchOutside(false)
        }
    }

    override fun dismissAll() {
        dialog?.dismiss()
    }

    override fun hideAll() {
        snackbar?.dismiss()
    }

    override fun showInternetPermission(listener: () -> Unit) {
        showConfirmDialog(title = "No Internet Connection", message = "Please make sure that you have a" +
                "internet connection.", buttonText = "RETRY", listener = listener)
    }

    override fun showLoading(view: View) {
        snackbar = TSnackbar.make(view, "Loading...", TSnackbar.LENGTH_INDEFINITE)
        val sbView = snackbar?.view
        val textView = sbView?.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.orange1))
        val params = sbView.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        sbView.layoutParams = params
        snackbar?.show()
    }
}