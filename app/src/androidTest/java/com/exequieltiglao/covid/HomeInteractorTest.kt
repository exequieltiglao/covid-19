package com.exequieltiglao.covid

import com.exequieltiglao.covid.home.HomeInteractor
import com.exequieltiglao.covid.home.HomeRouter
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: HomeInteractor.HomePresenter
  @Mock internal lateinit var router: HomeRouter

  private var interactor: HomeInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestHomeInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<HomeInteractor.HomePresenter, HomeRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}