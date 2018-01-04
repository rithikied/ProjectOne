package th.co.todsphol.add.projectone.activity

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import th.co.todsphol.add.projectone.R
import th.co.todsphol.add.projectone.fragment.FirstFragment

class FirstPageActivity : AppCompatActivity() {
    @BindView(R.id.btn_login) lateinit var login : Button
    @BindView(R.id.btn_new_register) lateinit var newRegister : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        onClickNewRegister()
        onClickLogin()
//        Glide.with(this).load(drawable.shoot).crossFade().into(vImageBackground)
    }

    private fun onClickLogin() {
        login.setOnClickListener {
            val homeIntent = Intent(this, Home::class.java)
            startActivity(homeIntent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    private fun onClickNewRegister() {
        newRegister.setOnClickListener {
            val newMember = Intent(this, NewRegisterActivity::class.java)
            startActivity(newMember)
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
