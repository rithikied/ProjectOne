package th.co.todsphol.add.projectone.fragment


import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import th.co.todsphol.add.projectone.R
import th.co.todsphol.add.projectone.activity.FirstPageActivity
import th.co.todsphol.add.projectone.activity.NewRegisterActivity

class FirstFragment : Fragment() {

    @Nullable
    @BindView(R.id.edt_phone_number) lateinit var edtPhone: EditText
    @Nullable
    @BindView(R.id.edt_password) lateinit var edtPassword: EditText
    @Nullable
    @BindView(R.id.btn_new_register) lateinit var newRegister: Button
    @Nullable
    @BindView(R.id.btn_register) lateinit var login: Button

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_first, container, false)
        ButterKnife.bind(this, view)
        getMainActivity().supportActionBar?.hide()
        onClickNewRegister()
        return view
    }

    fun onClickNewRegister() {
        newRegister.setOnClickListener {
            getMainActivity().changeFragment(SecondFragment.newInstance())
        }
    }

//    fun onClickLogin() {
//        login.setOnClickListener({
//            val loginIntent = Intent(context, NewRegisterActivity::class.java)
//            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(loginIntent)
//        })
//    }

    private fun getMainActivity(): FirstPageActivity {
        return activity as FirstPageActivity
    }

    companion object {
        fun newInstance(): Fragment {
            val bundle = Bundle()
            val fragment = FirstFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
