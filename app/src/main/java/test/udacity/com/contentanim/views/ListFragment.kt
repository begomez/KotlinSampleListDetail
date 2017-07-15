package test.udacity.com.contentanim.views


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.udacity.com.contentanim.MyApplication
import test.udacity.com.contentanim.views.interfaces.IList
import test.udacity.com.contentanim.presenters.ListPresenter
import test.udacity.com.contentanim.R
import javax.inject.Inject


/**
 * Created by bernatgomez on 15/7/17.
 */
class ListFragment : Fragment(), IList {

    val TAG = ListFragment::class.simpleName

    @Inject
    lateinit var presenter : ListPresenter

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

    override fun onResume() {
        super.onResume()

        this.fetchData()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val v : View? = inflater?.inflate(R.layout.fragment_list, container, false)

        return v
    }

//////////////////////////////////////////////////////////////////////////////////////
// HELPERS
//////////////////////////////////////////////////////////////////////////////////////

    private fun fetchData() {

    }

    private fun injectionWithDagger() {
        (activity.application as MyApplication).graph?.inject(this)
    }

//////////////////////////////////////////////////////////////////////////////////////
// IMPL
//////////////////////////////////////////////////////////////////////////////////////

    override fun onDataReceived() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
