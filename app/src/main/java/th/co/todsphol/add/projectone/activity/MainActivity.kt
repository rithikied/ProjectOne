package th.co.todsphol.add.projectone.activity

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import th.co.todsphol.add.projectone.R
import th.co.todsphol.add.projectone.fragment.FirstFragment

class MainActivity : AppCompatActivity() {
    @BindView(R.id.toolbar) lateinit var myToolbar : Toolbar
    @BindView(R.id.tv_toolbar_title) lateinit var tvTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        initFragment()
        setToolbar()


    }
    private fun setToolbar() {
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        tvTitle.text = getString(R.string.Hello)
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
