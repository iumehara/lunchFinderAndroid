package io.umehara.lunchfinderandroid.category

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class DefaultCategoryRepo @Inject constructor(private val categoryCaller: RetrofitCategoryCaller): CategoryRepo {
    override fun getAll(): Single<List<Category>> {
        return Single.create { observer ->
            categoryCaller.getAll().enqueue(object: Callback<List<Category>> {
                override fun onResponse(call: Call<List<Category>>?, response: Response<List<Category>>?) {
                    if (response == null || !response.isSuccessful || response.body() == null) {
                        observer.onError(IOException())
                        return
                    }

                    val categories = response.body() ?: return
                    observer.onSuccess(categories)
                }
                override fun onFailure(call: Call<List<Category>>?, t: Throwable?) {
                    if (t != null) observer.onError(IOException(t.message))
                }
            })
        }
    }
}