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
import android.widget.Button
import android.widget.EditText


@Suppress("DEPRECATION")
class NewRegisterActivity : AppCompatActivity() {



    @BindView(R.id.toolbar) lateinit var toolBar: Toolbar
    @BindView(R.id.tv_toolbar_title) lateinit var titleToolbar: TextView
    @BindView(R.id.edt_first_name) lateinit var edtFirstName : EditText
    @BindView(R.id.edt_last_name) lateinit var edtLastName : EditText
    @BindView(R.id.edt_age) lateinit var edtAge : EditText
    @BindView(R.id.spinner_brand) lateinit var spinnerBrand: MaterialSpinner
    @BindView(R.id.edt_color) lateinit var edtColor : EditText
    @BindView(R.id.edt_licence_plate) lateinit var edtLicencePlate : EditText
    @BindView(R.id.edt_phone_number) lateinit var edtPhoneNumber : EditText
    @BindView(R.id.edt_password)lateinit var edtPassword : EditText
    @BindView(R.id.edt_confirm_password) lateinit var edtConfirmPassword : EditText
    @BindView(R.id.btn_new_register) lateinit var register : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new__register)
        ButterKnife.bind(this)
        setToolbar()
        spinnerBrand()
    }

    fun registerButtonClicked() {

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
