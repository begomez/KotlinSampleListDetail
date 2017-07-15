package test.udacity.com.contentanim

import android.app.Application
import test.udacity.com.contentanim.dependencies.AppComponent
import test.udacity.com.contentanim.dependencies.AppModule
import test.udacity.com.contentanim.dependencies.DaggerAppComponent

/**
 * Created by bernatgomez on 15/7/17.
 */
class MyApplication : Application() {

    var graph : AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        this.initGraph()
    }

    fun initGraph() {
        graph = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}