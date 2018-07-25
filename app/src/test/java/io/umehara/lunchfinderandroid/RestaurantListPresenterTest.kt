package io.umehara.lunchfinderandroid

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.Arrays.asList

class RestaurantListPresenterTest {
    private lateinit var presenter: RestaurantListPresenter
    private lateinit var mainActivity: FakeRestaurantListView

    @Before
    fun setUp() {
        mainActivity = FakeRestaurantListView()
        presenter = RestaurantListPresenter()
        presenter.setView(mainActivity)
    }

    @Test
    fun onCreate_setsRestaurants() {
        presenter.onCreate()


        val expectedRestaurants = asList(
                Restaurant(1, "First", "一"),
                Restaurant(2, "Second", "二"),
                Restaurant(3, "Third", "三")
        )
        assertThat(mainActivity.setRowArguments, equalTo(expectedRestaurants))
    }
}
