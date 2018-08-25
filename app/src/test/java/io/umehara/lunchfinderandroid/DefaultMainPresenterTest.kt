package io.umehara.lunchfinderandroid

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.umehara.lunchfinderandroid.category.StubCategoryRepo
import io.umehara.lunchfinderandroid.map.FakeMultipleMarkerMap
import io.umehara.lunchfinderandroid.map.Geolocation
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import io.umehara.lunchfinderandroid.restaurant.SuccessStubRestaurantRepo
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.util.Arrays.asList

class DefaultMainPresenterTest {
    private lateinit var defaultMainPresenter: DefaultMainPresenter
    private lateinit var fakeMainView: FakeMainView
    private lateinit var restaurantRepoSuccess: SuccessStubRestaurantRepo
    private lateinit var categoryRepo: StubCategoryRepo
    private lateinit var fakeMultipleMarkerMap: FakeMultipleMarkerMap

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        fakeMainView = FakeMainView()
        restaurantRepoSuccess = SuccessStubRestaurantRepo()
        categoryRepo = StubCategoryRepo()
        fakeMultipleMarkerMap = FakeMultipleMarkerMap()
        defaultMainPresenter = DefaultMainPresenter(fakeMainView, restaurantRepoSuccess, categoryRepo, fakeMultipleMarkerMap)
    }

    @Test
    fun setup_createsMapWithRestaurantMarkers() {
        defaultMainPresenter.setup()

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertNotNull(fakeMainView.setMapArguments)
        assertThat(fakeMultipleMarkerMap.setMarkersArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun setup_setsRestaurantList() {
        defaultMainPresenter.setup()

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(fakeMainView.setRestaurantListArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun setup_setsRestaurantDetail() {
        defaultMainPresenter.setup()

        val expectedRestaurant = Restaurant(1, "First", "一", "", null, emptyList())
        assertThat(fakeMainView.setRestaurantDetailArgument, equalTo(expectedRestaurant))
    }

    @Test
    fun selectCategory_setsRestaurantList() {
        defaultMainPresenter.selectCategory(1)

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(fakeMainView.setRestaurantListArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun selectCategory_setsRestaurantDetail() {
        defaultMainPresenter.selectCategory(1)

        val expectedRestaurant = Restaurant(1, "First", "一", "", null, emptyList())
        assertThat(fakeMainView.setRestaurantDetailArgument, equalTo(expectedRestaurant))
    }

    @Test
    fun selectCategory_setsMapMarkers() {
        defaultMainPresenter.selectCategory(1)

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(fakeMultipleMarkerMap.updateRestaurantsArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun selectRestaurant_updatesMarker() {
        val stubRestaurant = Restaurant(1, "First", "一", "", Geolocation(BigDecimal(1.11111), BigDecimal(2.22222)), emptyList())
        defaultMainPresenter.selectRestaurant(stubRestaurant)

        assertThat(fakeMultipleMarkerMap.updateMarkerArguments, equalTo(Geolocation(BigDecimal(1.11111), BigDecimal(2.22222))))
    }

    @Test
    fun selectRestaurant_setsRestaurantDetail() {
        val stubRestaurant = Restaurant(1, "First", "一", "", Geolocation(BigDecimal(1.11111), BigDecimal(2.22222)), emptyList())
        defaultMainPresenter.selectRestaurant(stubRestaurant)

        assertThat(fakeMainView.setRestaurantDetailArgument, equalTo(stubRestaurant))
    }

    @Test
    fun selectAllRestaurants_setsRestaurantList() {
        defaultMainPresenter.selectAllRestaurants()

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(fakeMainView.setRestaurantListArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun selectAllRestaurants_setsMarkers() {
        defaultMainPresenter.selectAllRestaurants()

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(fakeMultipleMarkerMap.updateRestaurantsArguments, equalTo(expectedRestaurants))
    }
}
