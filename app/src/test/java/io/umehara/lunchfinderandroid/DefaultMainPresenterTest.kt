package io.umehara.lunchfinderandroid

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import io.umehara.lunchfinderandroid.restaurant.StubRestaurantRepo
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.Arrays.asList

class DefaultMainPresenterTest {
    private lateinit var defaultMainPresenter: DefaultMainPresenter
    private lateinit var view: FakeMainView
    private lateinit var repo: StubRestaurantRepo

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        view = FakeMainView()
        repo = StubRestaurantRepo()
        defaultMainPresenter = DefaultMainPresenter(view, repo)
    }

    @Test
    fun getRestaurants_setsRestaurantsToRestaurantList() {
        defaultMainPresenter.getRestaurants()

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(view.setRestaurantListArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun getRestaurants_setsRestaurantsToMap() {
        defaultMainPresenter.getRestaurants()

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(view.setMapArguments, equalTo(expectedRestaurants))
    }
}
