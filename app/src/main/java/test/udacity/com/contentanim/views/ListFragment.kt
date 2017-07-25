package test.udacity.com.contentanim.views


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import test.udacity.com.contentanim.R
import test.udacity.com.contentanim.controllers.ListController
import test.udacity.com.contentanim.dependencies.AppModule
import test.udacity.com.contentanim.dependencies.DaggerAppComponent
import test.udacity.com.contentanim.models.PhotoModel
import test.udacity.com.contentanim.models.PhotoViewModel
import test.udacity.com.contentanim.presenters.ListPresenter
import test.udacity.com.contentanim.views.adapters.ListAdapter
import test.udacity.com.contentanim.views.interfaces.IList
import javax.inject.Inject


/**
 * Main fragment content
 *
 * Created by bernatgomez on 15/7/17.
 */
class ListFragment constructor() : Fragment(), IList {

    @Inject
    protected lateinit var presenter : ListPresenter


//////////////////////////////////////////////////////////////////////////////////////
// CONS
//////////////////////////////////////////////////////////////////////////////////////

    companion object {
        fun newInstance() : ListFragment {
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

        if (this.mustFetchData()) {
            this.fetchData()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater?.inflate(R.layout.fragment_list, container, false)
    }

//////////////////////////////////////////////////////////////////////////////////////
// HELPERS
//////////////////////////////////////////////////////////////////////////////////////

    private fun mustFetchData() : Boolean = this.loading.visibility == View.VISIBLE

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

    override fun hideLoading() {
        this.loading.visibility = View.INVISIBLE
        this.list.visibility = View.VISIBLE
    }

    override fun showLoading() {
        this.loading.visibility = View.VISIBLE
        this.list.visibility = View.INVISIBLE
    }

    fun createAdapterAndConfigureList(data : List<PhotoModel>) {
        this.createAdapter(data)
        this.configureList()
    }

    fun createAdapter(data : List<PhotoModel>) {

        //XXX: if lambda is the last argument, we can put it out the parenthesis
        val adapter =
            ListAdapter(this.context, data) {this.navigateWithSharedElem(it)}

        adapter.notifyDataSetChanged()

        this.list.adapter = adapter
    }

    fun configureList() {
        val COLS = 3

        val mgr : GridLayoutManager = GridLayoutManager(this.context, COLS)


        mgr.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int) : Int {
                var span : Int = 0

                when (position % 6) {
                    0 -> span = 1
                    1 -> span = 2
                    2 -> span = 3
                    3 -> span = 3
                    4 -> span = 2
                    5 -> span = 1
                }

                return span
            }
        }

        this.list.layoutManager = mgr
        this.list.addItemDecoration(GridDecoration(this.resources.getInteger(R.integer.item_decor_pad)))
        this.list.setHasFixedSize(true)
    }

    fun navigateWithSharedElem(viewModel: PhotoViewModel) {
        val i = Intent(this.activity, DetailActivity::class.java)

        i.action = Intent.ACTION_VIEW
        i.data = this.getUrl(viewModel.model)
        i.putExtra(DetailActivity.EXTRA_PHOTO, viewModel.model)

        val b : Bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity, viewModel.v, viewModel.v.transitionName).toBundle()

        this.activity.startActivity(i, b)
    }

    fun navigateWithContentAnim(viewModel: PhotoViewModel) {
        val i : Intent = Intent(this.activity, DetailActivity::class.java)

        i.action = Intent.ACTION_VIEW
        i.data = this.getUrl(viewModel.model)
        i.putExtra(DetailActivity.EXTRA_PHOTO, viewModel.model)

        val b : Bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity).toBundle()

        this.activity.startActivity(i, b)
    }

    fun getUrl(photo : PhotoModel) : Uri {
        return Uri.parse(ListController.LIST_URL + this.resources.displayMetrics.widthPixels + ListController.LIST_ITEM_PATH + photo.id)
    }
}
