package th.co.todsphol.add.projectone.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import th.co.todsphol.add.projectone.R

class FirstPageActivity : AppCompatActivity() {

    @BindView(R.id.btn_new_register) lateinit var newRegister : Button
    @BindView(R.id.btn_goto_login) lateinit var gotoLogin : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)
        ButterKnife.bind(this)
        onClickGotoLogin()
        onClickNewRegister()
    }
    private fun onClickGotoLogin() {
        gotoLogin.setOnClickListener {
            val nextLogin = Intent(this,LoginActivity::class.java)
            startActivity(nextLogin)
            overridePendingTransition(R.anim.slide_right, R.anim.slide_left)
        }

    }
    private fun onClickNewRegister() {

        newRegister.setOnClickListener {
            val newRegister = Intent(this, NewRegisterActivity::class.java)
            startActivity(newRegister)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }
}
