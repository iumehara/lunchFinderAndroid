package io.umehara.lunchfinderandroid

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.category.StubCategoryRepo
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
    private lateinit var restaurantRepo: StubRestaurantRepo
    private lateinit var categoryRepo: StubCategoryRepo

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        view = FakeMainView()
        restaurantRepo = StubRestaurantRepo()
        categoryRepo = StubCategoryRepo()
        defaultMainPresenter = DefaultMainPresenter(view, restaurantRepo, categoryRepo)
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

    @Test
    fun getRestaurants_setsRestaurantToDetail() {
        defaultMainPresenter.getRestaurants()

        val expectedRestaurant= Restaurant(1, "First", "一", "", null, emptyList())
        assertThat(view.setDetailArgument, equalTo(expectedRestaurant))
    }

    @Test
    fun getCategories_setsCategoriesToCategoryList() {
        defaultMainPresenter.getCategories()


        val expectedCategories = asList(
                Category(1, "Pizza", 0),
                Category(2, "Sushi", 0)
        )
        assertThat(view.setCategoryListArguments, equalTo(expectedCategories))
    }

    @Test
    fun getCategoryRestaurants_setsRestaurantsToRestaurantList() {
        defaultMainPresenter.getCategoryRestaurants(1)

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(view.setRestaurantListArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun getCategoryRestaurants_setsRestaurantsToMap() {
        defaultMainPresenter.getCategoryRestaurants(1)

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(view.setMapArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun getCategoryRestaurants_setsRestaurantToDetail() {
        defaultMainPresenter.getCategoryRestaurants(1)

        val expectedRestaurant= Restaurant(1, "First", "一", "", null, emptyList())
        assertThat(view.setDetailArgument, equalTo(expectedRestaurant))
    }
}
