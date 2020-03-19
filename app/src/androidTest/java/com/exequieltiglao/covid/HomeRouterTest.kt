package com.exequieltiglao.covid

import com.exequieltiglao.covid.home.HomeBuilder
import com.exequieltiglao.covid.home.HomeInteractor
import com.exequieltiglao.covid.home.HomeRouter
import com.exequieltiglao.covid.home.HomeView
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: HomeBuilder.Component
  @Mock internal lateinit var interactor: HomeInteractor
  @Mock internal lateinit var view: HomeView

  private var router: HomeRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = HomeRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

