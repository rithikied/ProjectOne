package th.co.todsphol.add.projectone.activity

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnTextChanged
import th.co.todsphol.add.projectone.PhoneNumberWatcher
import th.co.todsphol.add.projectone.R
import th.co.todsphol.add.projectone.fragment.FirstFragment

class LoginActivity : AppCompatActivity() {
    @BindView(R.id.btn_login) lateinit var login : Button
    @BindView(R.id.edt_phone_number) lateinit var edtPhone : EditText
    @BindView(R.id.edt_password) lateinit var edtPassword : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        onClickLogin()
        onText(edtPhone.toString())
        edtPhone.addTextChangedListener(PhoneNumberWatcher(edtPhone))
//        Glide.with(this).load(drawable.shoot).crossFade().into(vImageBackground)
    }

    @OnTextChanged(R.id.edt_phone_number, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    fun onText(phoneNumber : CharSequence) {
        if (phoneNumber.toString().length == 12) {
            edtPassword.visibility = android.view.View.VISIBLE
            return
        }
        edtPassword.visibility = android.view.View.GONE
    }

    private fun onClickLogin() {
        login.setOnClickListener {
            val homeIntent = Intent(this, MapsActivity::class.java)
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(homeIntent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }


    private fun initFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, FirstFragment.newInstance())
                .commit()
    }
     fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
