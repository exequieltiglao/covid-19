package com.exequieltiglao.covid.home

import android.util.Log
import com.exequieltiglao.covid.entity.Data
import com.exequieltiglao.covid.service.ApiRepository
import com.uber.autodispose.ObservableScoper
import com.uber.autodispose.SingleScoper
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.HttpException
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

        presenter.showLoading()

        showData()

    }


    fun showData() {
        apiRepository.data()
            .observeOn(AndroidSchedulers.mainThread())
            .to(SingleScoper<Data>(this))
            .subscribe({
                presenter.setData(it)
            }, {
                // TODO : move to RootInteractor and improve code logic
                if (it is HttpException) {
                    Log.d("HttpException", it.code().toString())
                } else {
                    presenter.showInternetDialog()
                }
            })
    }

    override fun willResignActive() {
        super.willResignActive()

        // TODO: Perform any required clean up here, or delete this method entirely if not needed.
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface HomePresenter {

        fun showInternetDialog()
        fun showLoading()
        fun setData(data: Data)
    }
}
