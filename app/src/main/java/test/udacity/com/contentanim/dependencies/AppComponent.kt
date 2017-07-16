package test.udacity.com.contentanim.dependencies


import retrofit2.Retrofit
import test.udacity.com.contentanim.Api
import test.udacity.com.contentanim.controllers.ListController
import test.udacity.com.contentanim.presenters.ListPresenter
import test.udacity.com.contentanim.views.ListFragment

/**
 * Created by bernatgomez on 15/7/17.
 */
@javax.inject.Singleton
@dagger.Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(frag : ListFragment)

    fun inject(presenter : ListPresenter)

    fun inject(controller : ListController)

    fun getRetrofit() : Retrofit

    fun getApi() : Api
}