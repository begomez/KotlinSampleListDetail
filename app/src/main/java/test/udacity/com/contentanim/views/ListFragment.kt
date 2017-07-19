package test.udacity.com.contentanim.views


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import test.udacity.com.contentanim.R
import test.udacity.com.contentanim.dependencies.AppModule
import test.udacity.com.contentanim.dependencies.DaggerAppComponent
import test.udacity.com.contentanim.models.PhotoModel
import test.udacity.com.contentanim.presenters.ListPresenter
import test.udacity.com.contentanim.views.adapters.ListAdapter
import test.udacity.com.contentanim.views.interfaces.IList
import javax.inject.Inject


/**
 * Main activity content
 * Created by bernatgomez on 15/7/17.
 */
class ListFragment : Fragment(), IList {

    @Inject
    protected lateinit var presenter : ListPresenter


//////////////////////////////////////////////////////////////////////////////////////
// CONS
//////////////////////////////////////////////////////////////////////////////////////

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

//////////////////////////////////////////////////////////////////////////////////////
// LIFE CYCLE
//////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.injectionWithDagger()
    }

    override fun onStart() {
        super.onStart()

        this.presenter.bindView(this)

        this.presenter.start()
    }

    override fun onStop() {
        super.onStop()

        this.presenter.stop()
    }

    override fun onResume() {
        super.onResume()

        this.fetchData()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater?.inflate(R.layout.fragment_list, container, false)
    }

//////////////////////////////////////////////////////////////////////////////////////
// HELPERS
//////////////////////////////////////////////////////////////////////////////////////

    private fun fetchData() {
        this.presenter.getData()
    }

    private fun injectionWithDagger() {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

//////////////////////////////////////////////////////////////////////////////////////
// IMPL
//////////////////////////////////////////////////////////////////////////////////////

    override fun onDataReceived(data : List<PhotoModel>) {
        this.createAdapterAndConfigureList(data)
    }

    fun createAdapterAndConfigureList(data : List<PhotoModel>) {
        val SPAN = 2

        var adapter = ListAdapter(this.context, data)
        adapter.notifyDataSetChanged()

        this.list.adapter = adapter
        this.list.layoutManager = GridLayoutManager(this.context, SPAN)
        this.list.setHasFixedSize(true)
    }

}
