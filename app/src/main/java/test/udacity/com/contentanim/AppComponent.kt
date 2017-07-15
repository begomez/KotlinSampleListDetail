package test.udacity.com.contentanim


import dagger.Component
import javax.inject.Singleton


/**
 * Created by bernatgomez on 15/7/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(frag : ListFragment)
}