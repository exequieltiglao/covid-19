package com.exequieltiglao.covid.root

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.exequieltiglao.covid.utils.DialogHelper
import com.exequieltiglao.covid.utils.DialogHelperFactory
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.root.view.*


/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle), RootInteractor.RootPresenter {

    private lateinit var dialogHelper: DialogHelper
    private val retrySubject = PublishSubject.create<String>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        dialogHelper = DialogHelperFactory.create(context)
    }

    override fun home(): Observable<Unit> = RxView.clicks(tap_here).map { }

}
