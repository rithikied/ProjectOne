package th.co.todsphol.add.projectone.fragment


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

class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_first, container, false)
        ButterKnife.bind(this, view)
        getMainActivity().supportActionBar?.hide()
        return view
    }

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
