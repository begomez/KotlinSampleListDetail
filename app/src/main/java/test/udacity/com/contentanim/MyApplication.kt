package test.udacity.com.contentanim


import android.app.Application
import test.udacity.com.contentanim.dependencies.AppComponent
import test.udacity.com.contentanim.dependencies.AppModule
import test.udacity.com.contentanim.dependencies.DaggerAppComponent


/**
 * Created by bernatgomez on 15/7/17.
 */
//XXX: private constructor generates access error
class MyApplication /*private*/ constructor() : Application() {

    lateinit protected var graph : AppComponent

    companion object {
        lateinit var instance : MyApplication
    }

    init {
        this.initGraph()
    }

//////////////////////////////////////////////////////////////////////////////////////
// LIFE
//////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     */
    override fun onCreate() {
        super.onCreate()
    }

//////////////////////////////////////////////////////////////////////////////////////
// HELPERS
//////////////////////////////////////////////////////////////////////////////////////

    fun initGraph() {
        this.graph = DaggerAppComponent.builder().appModule(AppModule()).build()
    }


//////////////////////////////////////////////////////////////////////////////////////
// ACCESSORS
//////////////////////////////////////////////////////////////////////////////////////

    fun getInstance() : MyApplication {
        if (MyApplication.instance == null) {
            MyApplication.instance = MyApplication()
        }

        return instance
    }
}