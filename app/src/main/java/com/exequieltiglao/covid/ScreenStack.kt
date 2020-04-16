package com.exequieltiglao.covid


import android.view.ViewGroup
import androidx.annotation.UiThread
import com.uber.rib.core.screenstack.ScreenStackBase
import com.uber.rib.core.screenstack.ViewProvider
import java.util.*

@UiThread
class ScreenStack(private val parentViewGroup: ViewGroup) : ScreenStackBase {
    private val backStack = ArrayDeque<ViewProvider>()

    private val currentViewProvider: ViewProvider?
        get() = if (backStack.isEmpty()) {
            null
        } else backStack.peek()


    override fun pushScreen(viewProvider: ViewProvider) {
        pushScreen(viewProvider, false)
    }

    override fun pushScreen(viewProvider: ViewProvider, shouldAnimate: Boolean) {
        removeCurrentView()
        onCurrentViewHidden()
        backStack.push(viewProvider)
        // order matters here
        addCurrentView()
        onCurrentViewAppeared()
    }

    override fun popScreen() = popScreen(false)

    override fun popScreen(shouldAnimate: Boolean) {
        if (backStack.isEmpty()) {
            return
        }

        removeCurrentView()
        onCurrentViewRemoved()
        backStack.pop()
        addCurrentView()
        onCurrentViewAppeared()
    }

    override fun popBackTo(index: Int, shouldAnimate: Boolean) {
        for (size in backStack.size - 1 downTo index + 1) {
            popScreen()
        }
    }

    override fun handleBackPress() = handleBackPress(false)

    override fun handleBackPress(shouldAnimate: Boolean): Boolean {
        if (backStack.size == 1) {
            return false
        }
        popScreen()
        return true
    }

    override fun size(): Int {
        return backStack.size
    }

    /**
     * Returns the index of the last item in the stack.
     * @return -1 is return when the backstack is empty.
     */
    fun indexOfLastItem(): Int {
        return size() - 1
    }

    private fun onCurrentViewHidden() {
        currentViewProvider?.onViewHidden()
    }

    private fun onCurrentViewAppeared() {
        currentViewProvider?.onViewAppeared()

    }

    private fun onCurrentViewRemoved() {
        currentViewProvider?.onViewRemoved()
    }

    private fun addCurrentView() {
        currentViewProvider?.let {
            parentViewGroup.addView(it.buildView(parentViewGroup))
        }
    }

    private fun removeCurrentView() {
        if (parentViewGroup.childCount > 0) {
            parentViewGroup.removeViewAt(parentViewGroup.childCount - 1)
        }
    }
}