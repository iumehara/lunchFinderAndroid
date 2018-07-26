package io.umehara.lunchfinderandroid

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.Arrays.asList

class DefaultMainPresenterTest {
    private lateinit var presenterDefault: DefaultMainPresenter
    private lateinit var mainActivity: FakeMainView

    @Before
    fun setUp() {
        mainActivity = FakeMainView()
        presenterDefault = DefaultMainPresenter(mainActivity)
    }

    @Test
    fun onCreate_setsRestaurants() {
        presenterDefault.getRestaurants()


        val expectedRestaurants = asList(
                Restaurant(1, "First", "一"),
                Restaurant(2, "Second", "二"),
                Restaurant(3, "Third", "三")
        )
        assertThat(mainActivity.setRowArguments, equalTo(expectedRestaurants))
    }
}
