package test.udacity.com.contentanim.dependencies

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.udacity.com.contentanim.Api
import test.udacity.com.contentanim.presenters.ListPresenter

/**
 * Created by bernatgomez on 15/7/17.
 */
@Module
class AppModule {

    @Provides
    fun provideListPresenter() : ListPresenter {
        return ListPresenter()
    }

    @Provides
    fun provideRetrofit() : Retrofit {
        val URL : String = "https://unsplash.it"
        var adapter : Retrofit? = null

        adapter =
            Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                    .build()

        return adapter
    }

    @Provides
    fun provideApiService(adapter : Retrofit) : Api {
        return adapter.create(Api::class.java)
    }

}