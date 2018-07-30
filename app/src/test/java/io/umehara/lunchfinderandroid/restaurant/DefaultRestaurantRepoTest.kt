package io.umehara.lunchfinderandroid.restaurant

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Arrays.asList


class DefaultRestaurantRepoTest {
    lateinit var restaurantCaller: RetrofitRestaurantCaller
    lateinit var repo: DefaultRestaurantRepo
    lateinit var mockCall: Call<List<Restaurant>>

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        restaurantCaller= mock()
        repo = DefaultRestaurantRepo(restaurantCaller)

        mockCall = Mockito.mock(Call::class.java) as Call<List<Restaurant>>
        whenever(restaurantCaller.getAll()).thenReturn(mockCall)
    }

    @Test
    fun getAll_handlesSuccessfulResponseFromRequest() {
        doAnswer {
            val callback = it.arguments[0] as Callback<List<Restaurant>>
            val successResponse = Response.success(asList(Restaurant(1, "1", "1")))

            callback.onResponse(mockCall, successResponse)
            null
        }.whenever(mockCall).enqueue(any())


        val responseSingle = repo.getAll()


        responseSingle.test().assertResult(asList(Restaurant(1, "1", "1")))
    }
}