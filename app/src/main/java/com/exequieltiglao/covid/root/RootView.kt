package com.exequieltiglao.covid.root

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.root.view.*

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle), RootInteractor.RootPresenter {
    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun home(): Observable<Unit> = RxView.clicks(tap_here).map { }

}
