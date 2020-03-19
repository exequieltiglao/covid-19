package com.exequieltiglao.covid.home

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exequieltiglao.covid.entity.CDR
import com.exequieltiglao.covid.entity.CDRresults
import kotlinx.android.synthetic.main.home_cdr_rib.view.*

/**
 * Top level view for {@link HomeBuilder.HomeScope}.
 */
class HomeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle), HomeInteractor.HomePresenter {

    var cdr: List<CDRresults> = listOf(CDRresults(listOf(CDR(0, 0, 0))))
    private var isLoaded: Boolean = true

    override fun onFinishInflate() {
        super.onFinishInflate()
        initRv()
    }

    private fun initRv() {
        if (isLoaded) return

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_cdr.layoutManager = llm
        isLoaded = true

    }

    override fun setData(results: List<CDRresults>) {
        this.cdr = results
        rv_cdr.requestModelBuild()
    }
}
