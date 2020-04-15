package com.exequieltiglao.covid.home

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exequieltiglao.covid.entity.Data
import kotlinx.android.synthetic.main.home_data_rib.view.*
import kotlinx.android.synthetic.main.show_data.view.*

/**
 * Top level view for {@link HomeBuilder.HomeScope}.
 */
class HomeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle), HomeInteractor.HomePresenter {

    private var isLoaded: Boolean = true
    private var loadData : Data? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        initRv()
    }

    private fun initRv() {
        if (isLoaded) return

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_data.layoutManager = llm
        isLoaded = true

        Log.d("isLoaded,","true")

    }

    override fun setData(data: Data) {
        loadData = data

        total_affected.text = data.data.confirmed.toString()
        total_recovered.text = data.data.recovered.toString()
        total_death.text = data.data.deaths.toString()
        total_active.text = data.data.active.toString()

        /*
        val format = SimpleDateFormat("MM-dd-yyyy", Locale.US)
        val dateString = format.format(data.dt)

        as_of.text = dateString.toString()
        */

    }

}
