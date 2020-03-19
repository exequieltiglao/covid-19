package com.exequieltiglao.covid.home

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exequieltiglao.covid.entity.CDR
import com.exequieltiglao.covid.entity.CDRresults
import io.reactivex.Notification
import kotlinx.android.synthetic.main.epoxy_view_cdr.view.*
import kotlinx.android.synthetic.main.home_cdr_rib.view.*
import kotlinx.android.synthetic.main.toolbar.view.*

/**
 * Top level view for {@link HomeBuilder.HomeScope}.
 */
class HomeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle), HomeInteractor.HomePresenter {

    private var cases: List<CDRresults> = listOf(CDRresults(listOf(CDR(0, 0, 0))))
    private var isLoaded: Boolean = true

    override fun onFinishInflate() {
        super.onFinishInflate()
        toolbar_heading.text = "COVID-19"
        initRv()
    }

    private fun initRv() {
        if (isLoaded) return

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_cdr.layoutManager = llm
        isLoaded = true

        Log.d("isLoaded,","true")

        rv_cdr.buildModelsWith {
            cases.forEach { cdRresults ->
                cdRresults.results.forEach {
                    total_confirmed.text = it.confirmed.toString()
                    total_deaths.text = it.deaths.toString()
                    total_recovered.text = it.recovered.toString()
                }
                rv_cdr.requestModelBuild()
            }
        }
    }

    override fun setData(results: List<CDRresults>) {
        this.cases = results
        rv_cdr.requestModelBuild()
        Log.d("setData,","true")
    }
}
