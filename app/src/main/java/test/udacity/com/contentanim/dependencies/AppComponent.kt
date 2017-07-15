package test.udacity.com.contentanim.dependencies


import test.udacity.com.contentanim.views.ListFragment

/**
 * Created by bernatgomez on 15/7/17.
 */
@javax.inject.Singleton
@dagger.Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(frag : ListFragment)
}