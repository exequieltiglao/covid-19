package com.exequieltiglao.covid.home

import android.util.Log
import com.exequieltiglao.covid.entity.CDRresults
import com.exequieltiglao.covid.service.ApiRepository
import com.uber.autodispose.ObservableScoper
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Notification
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Coordinates Business Logic for [HomeScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class HomeInteractor : Interactor<HomeInteractor.HomePresenter, HomeRouter>() {

    @Inject lateinit var presenter: HomePresenter
    @Inject lateinit var apiRepository: ApiRepository

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        showData()
    }

    fun showData() {
        apiRepository.cdr()
            .toObservable()
            .materialize()
            .observeOn(AndroidSchedulers.mainThread())
            .to(ObservableScoper<Notification<List<CDRresults>>>(this))
            .subscribe {
                when {
                    it.isOnNext -> {
                        Log.d("isOnNext", "show_data")
                        presenter.setData(it.value!!)
                    }
                    it.isOnError -> {
                        Log.d("isOnError", "error_data")
                    }
                }
            }
    }

    override fun willResignActive() {
        super.willResignActive()

        // TODO: Perform any required clean up here, or delete this method entirely if not needed.
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface HomePresenter {
        fun setData(results: List<CDRresults>)
    }
}
