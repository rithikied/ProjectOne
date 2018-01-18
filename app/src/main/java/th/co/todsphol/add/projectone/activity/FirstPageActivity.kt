package th.co.todsphol.add.projectone.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import th.co.todsphol.add.projectone.R

class FirstPageActivity : AppCompatActivity() {

    @BindView(R.id.btn_new_register) lateinit var newRegister : Button
    @BindView(R.id.btn_back_to_login) lateinit var gotoLogin : Button

    var baseR = FirebaseDatabase.getInstance().reference
    var dataStatus = baseR.child("User").child("user1").child("STATUS")
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

     override fun onStart() {
        dataStatus.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val loginStatus = dataSnapshot.child("Slogin").getValue(Int::class.java)
                when {
                    loginStatus == 1 -> gotoMaps()
                }
            }

            override fun onCancelled(p0: DatabaseError?) {

            }
        })
        super.onStart()
    }

    private fun gotoMaps() {
        val mapIntent = Intent(this@FirstPageActivity, MapsActivity::class.java)
        mapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(mapIntent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
