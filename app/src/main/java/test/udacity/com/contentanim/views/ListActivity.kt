package test.udacity.com.contentanim.views


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import test.udacity.com.contentanim.R


/**
 *
 */
class ListActivity : AppCompatActivity() {

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_list)

        if (savedInstanceState == null) {
            this.launchContentFragment()
        }
    }

    /**
     *
     */
    private fun launchContentFragment() {
        val trans : FragmentTransaction = supportFragmentManager.beginTransaction()

        trans.replace(R.id.mainContainer, ListFragment.newInstance())

        trans.commit()
    }
}
