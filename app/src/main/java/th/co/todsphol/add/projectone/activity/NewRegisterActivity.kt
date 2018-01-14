package th.co.todsphol.add.projectone.activity

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
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
import android.widget.Toast
import butterknife.OnClick
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener




@Suppress("DEPRECATION")
class NewRegisterActivity : AppCompatActivity() {

    private var baseR = FirebaseDatabase.getInstance().reference
    private var dataName = baseR.child("User").child("user1").child("DATA_PERS")
    private var dataCar = baseR.child("User").child("user1").child("DATA_CAR")
    private var dataStatus = baseR.child("User").child("user1").child("STATUS")
    private var dataREG = baseR.child("User").child("user1").child("DATA_REG")
    private var dataLocation = baseR.child("User").child("user1").child("DATA_LOCATION")

    @Nullable
    @BindView(R.id.toolbar) lateinit var toolBar: Toolbar
    @Nullable
    @BindView(R.id.tv_toolbar_title) lateinit var titleToolbar: TextView
    @Nullable
    @BindView(R.id.edt_first_name) lateinit var edtFirstName: EditText
    @Nullable
    @BindView(R.id.edt_last_name) lateinit var edtLastName: EditText
    @Nullable
    @BindView(R.id.edt_age) lateinit var edtAge: EditText
    @Nullable
    @BindView(R.id.spinner_brand) lateinit var spinnerBrand: MaterialSpinner
    @Nullable
    @BindView(R.id.edt_color) lateinit var edtColor: EditText
    @Nullable
    @BindView(R.id.edt_licence_plate) lateinit var edtLicencePlate: EditText
    @Nullable
    @BindView(R.id.edt_phone_number) lateinit var edtPhoneNumber: EditText
    @Nullable
    @BindView(R.id.edt_password) lateinit var edtPassword: EditText
    @Nullable
    @BindView(R.id.edt_confirm_password) lateinit var edtConfirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new__register)
        ButterKnife.bind(this)
        setToolbar()
        registerButtonClicked()
        spinnerBrand()
    }

    @OnClick(R.id.btn_register)
    fun registerButtonClicked() {
            dataName.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataName.child("name").setValue(edtFirstName.text.toString())
                    dataName.child("surname").setValue(edtLastName.text.toString())
                    dataName.child("age").setValue(edtAge.text.toString())

                }

                override fun onCancelled(p0: DatabaseError?) {

                }

            })
            dataCar.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot?) {
                    spinnerBrand()
                    dataCar.child("color").setValue(edtColor.text.toString())
                    dataCar.child("LP").setValue(edtLicencePlate.text.toString())
                }

                override fun onCancelled(p0: DatabaseError?) {

                }

            })
            dataREG.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot?) {
                    dataREG.child("telephone").setValue(edtPhoneNumber.text.toString())
                    if (edtPassword.text.toString() != edtConfirmPassword.text.toString()) {
                        Toast.makeText(this@NewRegisterActivity,"กรุณากรอก Password ให้เหมือนกัน", Toast.LENGTH_SHORT).show()
                    } else {
                        dataREG.child("password").setValue(edtPassword.text.toString())
                    }
                }

                override fun onCancelled(p0: DatabaseError?) {

                }

            })
            dataLocation.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val longitude = dataSnapshot.child("Longtitude").getValue(Int::class.java)
                    val latitude = dataSnapshot.child("latitude").getValue(Int::class.java)
                    val vir = dataSnapshot.child("Vir").getValue(Int::class.java)
                if (vir == 0) {
                    dataStatus.child("Salarm").setValue(0)
                    dataStatus.child("Slogin").setValue(0)
                } else {
                    dataStatus.child("Salarm").setValue(0)
                    dataStatus.child("Slogin").setValue(0)
                }

                }

                override fun onCancelled(p0: DatabaseError?) {

                }

            })
    }

    fun spinnerBrand() {

        spinnerBrand.setTextColor(resources.getColor(R.color.colorGreen))
        spinnerBrand.setArrowColor(resources.getColor(R.color.colorGreen))
        spinnerBrand.textSize = resources.getDimension(R.dimen.textSizeInSpinner)
        spinnerBrand.setItems("Honda", "YAMAHA", "SUZUKI", "Kawasaki"
                , "DUCATI", "Vespa", "HARLEY", "SHOPPER","BMW")
        spinnerBrand.setOnItemSelectedListener { view, _, _,
                                                 item ->
            Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show()
            dataCar.child("Type").setValue(item.toString())
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
