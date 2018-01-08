package th.co.todsphol.add.projectone.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.jaredrummler.materialspinner.MaterialSpinner
import th.co.todsphol.add.projectone.R
import android.support.design.widget.Snackbar


@Suppress("DEPRECATION")
class NewRegisterActivity : AppCompatActivity() {

    @BindView(R.id.toolbar) lateinit var toolBar: Toolbar
    @BindView(R.id.tv_toolbar_title) lateinit var titleToolbar: TextView
    @BindView(R.id.spinner_brand) lateinit var spinnerBrand: MaterialSpinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new__register)
        ButterKnife.bind(this)
        setToolbar()
        spinnerBrand()
    }

    private fun spinnerBrand() {
        spinnerBrand.setTextColor(resources.getColor(R.color.colorGreen))
        spinnerBrand.setArrowColor(resources.getColor(R.color.colorGreen))
        spinnerBrand.textSize = resources.getDimension(R.dimen.textSizeInSpinner   )
        spinnerBrand.setItems("Honda", "YAMAHA", "SUZUKI", "Kawasaki", "DUCATI", "Vespa", "HARLEY", "SHOPPER")
        spinnerBrand.setOnItemSelectedListener { view, _, _,
                                                 item ->
            Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show()
        }
    }

    fun setToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        titleToolbar.text = getString(R.string.register)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }

}
