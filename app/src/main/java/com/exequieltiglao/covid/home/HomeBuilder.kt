package com.exequieltiglao.covid.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.exequieltiglao.covid.R
import com.exequieltiglao.covid.di.AccountModule
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link HomeScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class HomeBuilder(dependency: ParentComponent) :
    ViewBuilder<HomeView, HomeRouter, HomeBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [HomeRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [HomeRouter].
     */
    fun build(parentViewGroup: ViewGroup): HomeRouter {
        val view = createView(parentViewGroup)
        val interactor = HomeInteractor()
        val component = DaggerHomeBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .context(view.context)
            .interactor(interactor)
            .build()
        return component.homeRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): HomeView? {
        return inflater.inflate(R.layout.show_data, parentViewGroup, false) as HomeView
    }

    interface ParentComponent {
        // TODO: Define dependencies required from your parent interactor here.
    }

    @dagger.Module
    abstract class Module {

        @HomeScope
        @Binds
        internal abstract fun presenter(view: HomeView): HomeInteractor.HomePresenter

        @dagger.Module
        companion object {

            @HomeScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: HomeView,
                interactor: HomeInteractor
            ): HomeRouter {
                return HomeRouter(view, interactor, component)
            }
        }

        // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }

    @HomeScope
    @dagger.Component(modules = [Module::class, AccountModule::class], dependencies = [ParentComponent::class])
    interface Component : InteractorBaseComponent<HomeInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: HomeInteractor): Builder

            @BindsInstance
            fun view(view: HomeView): Builder
            @BindsInstance
            fun context(context: Context): Builder
            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun homeRouter(): HomeRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class HomeScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class HomeInternal
}
