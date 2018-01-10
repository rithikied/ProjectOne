package th.co.todsphol.add.projectone.activity

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import butterknife.BindView
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import th.co.todsphol.add.projectone.R
import android.content.Intent
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import butterknife.ButterKnife
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    @BindView(R.id.toolbar) lateinit var toolBar : Toolbar
    @BindView(R.id.tv_toolbar_title) lateinit var title : TextView
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        ButterKnife.bind(this)
        setToolBar()


}
    fun setToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        title.text = "ตำแหน่งของรถ"
    }

    var latitude = 1.0
    var logitude = 2.0
    fun getlo( x : Double , y : Double) {
        latitude = x
        logitude = y

    }
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isTiltGesturesEnabled = true
        mMap.uiSettings.isRotateGesturesEnabled = true
        mMap.uiSettings.isScrollGesturesEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        val chaengwattana = LatLng(latitude, logitude)
        mMap.addMarker(MarkerOptions().position(chaengwattana).title("Chaengwattana"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chaengwattana))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f))
        mMap.isMyLocationEnabled = true
        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)
        mMap.setOnInfoWindowClickListener(OnInfoWindowClickListener { marker ->
            val intent1 = Intent(this, CallActivity::class.java)
            val title = marker.title
            intent1.putExtra("markertitle", title)
            startActivity(intent1)
        })

    }


    override fun onMyLocationClick(location: Location) {
        Log.d("Data Location", location.latitude.toString())
        Log.d("Data Location", location.longitude.toString())
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show()
        getlo(location.latitude, location.longitude)
        Log.d("Latitude", latitude.toString())
        Log.d("Longitude", logitude.toString())
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
