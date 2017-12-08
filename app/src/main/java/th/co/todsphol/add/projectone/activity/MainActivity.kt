package th.co.todsphol.add.projectone.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import th.co.todsphol.add.projectone.fragment.FirstFragment
import th.co.todsphol.add.projectone.R

class MainActivity : AppCompatActivity() {
    private lateinit var myToolbar : Toolbar
    private lateinit var tvTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        setToolbar()

    }
    private fun setToolbar() {
        myToolbar = findViewById(R.id.toolbar)
        tvTitle = findViewById(R.id.tv_toolbar_title)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        tvTitle.text = getString(R.string.Hello)
    }
    private fun initFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, FirstFragment.newInstance())
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
