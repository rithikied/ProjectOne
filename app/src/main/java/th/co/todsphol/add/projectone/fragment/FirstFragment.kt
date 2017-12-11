package th.co.todsphol.add.projectone.fragment


import android.app.ActionBar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import th.co.todsphol.add.projectone.R
import th.co.todsphol.add.projectone.activity.MainActivity

class FirstFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_first, container, false)
        initButton(view)
        return view

    }
    private fun initButton(view: View) {
        val btn = view.findViewById<Button>(R.id.btn_next)
        btn.setOnClickListener {
            getMainActivity().changeFragment(SecondFragment.newInstance())
        }
    }

    private fun getMainActivity() : MainActivity {
        return activity as MainActivity
    }
    companion object {
        fun newInstance() : Fragment {
            val bundle = Bundle()
            val fragment = FirstFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}