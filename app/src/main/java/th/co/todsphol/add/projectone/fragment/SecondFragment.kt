package th.co.todsphol.add.projectone.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import th.co.todsphol.add.projectone.R
import th.co.todsphol.add.projectone.activity.MainActivity

class SecondFragment : Fragment() {

    @BindView(R.id.btn_register) lateinit var btnNext : Button
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_second, container, false)
        ButterKnife.bind(this,view)
        getMainActivity().supportActionBar?.show()
        onClickButton()
        return view

    }

    private fun onClickButton() {
        btnNext.setOnClickListener {
            getMainActivity().changeFragment(FirstFragment.newInstance())
        }
    }

    private fun getMainActivity() : MainActivity {
        return activity as MainActivity
    }
    companion object {
        fun newInstance() : Fragment {
            val bundle = Bundle()
            val fragment = SecondFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}