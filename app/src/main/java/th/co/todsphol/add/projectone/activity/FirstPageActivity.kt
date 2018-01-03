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
    @BindView(R.id.btn_new_register) lateinit var newRegister : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        newRegister.setOnClickListener {
            val newMember = Intent(this, NewRegisterActivity::class.java)
            newMember.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(newMember)
            overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out)
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
