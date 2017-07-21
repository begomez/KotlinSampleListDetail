package test.udacity.com.contentanim.views


import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.transition.Slide
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.animation.LinearInterpolator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

import test.udacity.com.contentanim.R
import test.udacity.com.contentanim.controllers.ListController
import test.udacity.com.contentanim.models.PhotoModel


/**
 * Content detail screen
 */
class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_PHOTO = "extra_photo"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_detail)

        this.configActionBar()

        this.setValues()

        this.configAnimations()
    }

    private fun configActionBar() {
        val bar : ActionBar? = this.supportActionBar

        bar!!.setTitle(R.string.app_name)
        bar.setDisplayHomeAsUpEnabled(true)
    }


    private fun setValues() {
        var data : PhotoModel?

        if (this.intent.extras.containsKey(EXTRA_PHOTO)) {
            data = this.intent.getSerializableExtra(EXTRA_PHOTO) as PhotoModel

            Picasso.with(this).load(this.getUrl(data)).into(this.photo)

            this.screenTitle.text = data.author
            this.content.text = this.getString(R.string.fake_descrip)
        }

    }

    private fun getUrl(photo : PhotoModel) : Uri {
        return Uri.parse(ListController.LIST_URL + this.resources.displayMetrics.widthPixels + ListController.LIST_ITEM_PATH + photo.id)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {

            android.R.id.home -> {
                super.onBackPressed()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    fun configAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.animateText()
        }
    }

    private fun animateText() {
        val slide : Slide = Slide(Gravity.BOTTOM)

        slide.addTarget(R.id.content)

        slide.duration = this.resources.getInteger(R.integer.anim_duration) + 0L

        slide.interpolator = LinearInterpolator()

        this.window.enterTransition = slide
    }
}
