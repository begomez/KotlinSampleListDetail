package test.udacity.com.contentanim.dependencies


import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.udacity.com.contentanim.Api
import test.udacity.com.contentanim.controllers.ListController


/**
 * Created by bernatgomez on 15/7/17.
 */
@Module
class AppModule {

    @Provides
    fun provideRetrofit() : Retrofit {
        val URL : String = ListController.LIST_URL

        val adapter =
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

    @Provides
    fun provideBus() : EventBus {
        return EventBus.getDefault()
    }

}