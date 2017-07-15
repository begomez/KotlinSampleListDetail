package test.udacity.com.contentanim

import dagger.Module
import dagger.Provides

/**
 * Created by bernatgomez on 15/7/17.
 */
@Module
class AppModule {

    @Provides
    fun provideListPresenter() : ListPresenter {
        return ListPresenter()
    }
}