package io.umehara.lunchfinderandroid.category

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Arrays.asList

class DefaultCategoryRepoTest {
    lateinit var categoryCaller: RetrofitCategoryCaller
    lateinit var repo: DefaultCategoryRepo
    lateinit var mockCall: Call<List<Category>>

    @Before
    fun setUp() {
        categoryCaller = mock()
        repo = DefaultCategoryRepo(categoryCaller)
        mockCall = Mockito.mock(Call::class.java) as Call<List<Category>>
    }

    @Test
    fun getAll_handlesSuccessfulResponseFromRequest() {
        whenever(categoryCaller.getAll()).thenReturn(mockCall)
        doAnswer {
            val callback = it.arguments[0] as Callback<List<Category>>
            val successResponse = Response.success(asList(Category(1,"Sushi")))
            callback.onResponse(mockCall, successResponse)
            null
        }.whenever(mockCall).enqueue(any())


        val responseSingle = repo.getAll()


        responseSingle.test().assertResult(asList(Category(1,"Sushi")))
    }
}