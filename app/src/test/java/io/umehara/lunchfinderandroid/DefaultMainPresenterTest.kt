package io.umehara.lunchfinderandroid

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.category.StubCategoryRepo
import io.umehara.lunchfinderandroid.restaurant.EmptyStubRestaurantRepo
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import io.umehara.lunchfinderandroid.restaurant.SuccessStubRestaurantRepo
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.Arrays.asList

class DefaultMainPresenterTest {
    private lateinit var defaultMainPresenter: DefaultMainPresenter
    private lateinit var view: FakeMainView
    private lateinit var restaurantRepoSuccess: SuccessStubRestaurantRepo
    private lateinit var categoryRepo: StubCategoryRepo

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        view = FakeMainView()
        restaurantRepoSuccess = SuccessStubRestaurantRepo()
        categoryRepo = StubCategoryRepo()
    }

    @Test
    fun getRestaurants_setsRestaurantsToRestaurantList() {
        defaultMainPresenter = DefaultMainPresenter(view, restaurantRepoSuccess, categoryRepo)
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
        defaultMainPresenter = DefaultMainPresenter(view, restaurantRepoSuccess, categoryRepo)
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
        defaultMainPresenter = DefaultMainPresenter(view, restaurantRepoSuccess, categoryRepo)
        defaultMainPresenter.getRestaurants()

        val expectedRestaurant= Restaurant(1, "First", "一", "", null, emptyList())
        assertThat(view.setDetailArgument, equalTo(expectedRestaurant))
    }

    @Test
    fun getCategories_setsCategoriesToCategoryList() {
        defaultMainPresenter = DefaultMainPresenter(view, restaurantRepoSuccess, categoryRepo)
        defaultMainPresenter.getCategories()


        val expectedCategories = asList(
                Category(1, "Pizza", 0),
                Category(2, "Sushi", 0)
        )
        assertThat(view.setCategoryListArguments, equalTo(expectedCategories))
    }

    @Test
    fun getCategoryRestaurants_setsRestaurantsToRestaurantList() {
        defaultMainPresenter = DefaultMainPresenter(view, restaurantRepoSuccess, categoryRepo)
        defaultMainPresenter.selectCategory(1)

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(view.setRestaurantListArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun getCategoryRestaurants_setsRestaurantsToMap() {
        defaultMainPresenter = DefaultMainPresenter(view, restaurantRepoSuccess, categoryRepo)
        defaultMainPresenter.selectCategory(1)

        val expectedRestaurants = asList(
                Restaurant(1, "First", "一", "", null, emptyList()),
                Restaurant(2, "Second", "二", "", null, emptyList()),
                Restaurant(3, "Third", "三", "", null, emptyList())
        )
        assertThat(view.updateMapArguments, equalTo(expectedRestaurants))
    }

    @Test
    fun getCategoryRestaurants_setsRestaurantToDetail() {
        defaultMainPresenter = DefaultMainPresenter(view, restaurantRepoSuccess, categoryRepo)
        defaultMainPresenter.selectCategory(1)

        val expectedRestaurant= Restaurant(1, "First", "一", "", null, emptyList())
        assertThat(view.setDetailArgument, equalTo(expectedRestaurant))
    }

    @Test
    fun getCategoryRestaurants_doesNotSetRestaurantToDetailIfThereAreNoRestaurants() {
        defaultMainPresenter = DefaultMainPresenter(view, EmptyStubRestaurantRepo(), categoryRepo)
        defaultMainPresenter.selectCategory(1)

        assertNull(view.setDetailArgument)
    }
}
