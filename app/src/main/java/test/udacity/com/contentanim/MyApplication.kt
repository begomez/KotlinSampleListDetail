package test.udacity.com.contentanim

import android.app.Application
import dagger.android.DaggerApplication
import dagger.android.DaggerApplication_MembersInjector

/**
 * Created by bernatgomez on 15/7/17.
 */
class MyApplication : Application() {

    var graph : AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        initGraph()
    }

    fun initGraph() {
        graph = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}