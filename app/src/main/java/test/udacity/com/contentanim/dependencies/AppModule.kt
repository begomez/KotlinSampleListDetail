package test.udacity.com.contentanim.dependencies

import dagger.Module
import dagger.Provides
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
}