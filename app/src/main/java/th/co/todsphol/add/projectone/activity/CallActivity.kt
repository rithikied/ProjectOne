package th.co.todsphol.add.projectone.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import th.co.todsphol.add.projectone.R

class CallActivity : Activity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        val callPhone = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"0821945021"))
        startActivity(callPhone)
        finish()
    }
}
