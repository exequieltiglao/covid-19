package com.exequieltiglao.covid.root


import com.exequieltiglao.covid.home.HomeBuilder
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    val homeBuilder: HomeBuilder) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    private var childRouter: ViewRouter<*, *, *>? = null

    fun detachCurrentChild() {
        childRouter ?: return
        detachChild(childRouter)
        view.removeView(childRouter?.view)
        childRouter = null
    }

    fun attachHomeRib() {
        detachCurrentChild()
        childRouter = homeBuilder.build(view)
        view.addView(childRouter?.view)
        attachChild(childRouter)
    }

}
