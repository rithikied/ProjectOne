package th.co.todsphol.add.projectone.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import th.co.todsphol.add.projectone.R

class NewRegisterActivity : AppCompatActivity() {

    @BindView(R.id.toolbar) lateinit var toolBar : Toolbar
    @BindView(R.id.tv_toolbar_title) lateinit var titleToolbar : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new__register)
        ButterKnife.bind(this)
        setToolbar()
    }

    fun setToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        titleToolbar.text = "Register"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }

}
