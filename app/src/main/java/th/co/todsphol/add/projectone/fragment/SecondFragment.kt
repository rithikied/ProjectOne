package th.co.todsphol.add.projectone.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar

import th.co.todsphol.add.projectone.R

class SecondFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_second, container, false)
        return view
    }

    companion object {
        fun newInstance() : Fragment{
            val bundle = Bundle()
            val fragment = SecondFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
