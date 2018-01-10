package th.co.todsphol.add.projectone.activity

import android.annotation.SuppressLint
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import butterknife.BindView
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import th.co.todsphol.add.projectone.R
import android.content.Intent
import android.support.annotation.Nullable
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import butterknife.ButterKnife
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    @Nullable
    @BindView(R.id.toolbar) lateinit var toolBar: Toolbar
    @Nullable
    @BindView(R.id.tv_toolbar_title) lateinit var title: TextView
    @Nullable
    @BindView(R.id.tv_name_client) lateinit var nameClient: TextView
    @Nullable
    @BindView(R.id.tv_surname_client) lateinit var surNameClient: TextView
    @Nullable
    @BindView(R.id.tv_color_client) lateinit var colorCar: TextView
    @Nullable
    @BindView(R.id.tv_brand_client) lateinit var brandCar: TextView
    @Nullable
    @BindView(R.id.tv_county) lateinit var licencePlate: TextView
    @Nullable
    @BindView(R.id.tv_status) lateinit var alarmStatus: TextView
    private lateinit var mMap: GoogleMap
    private var baseR = FirebaseDatabase.getInstance().reference
    private var dataName = baseR.child("User").child("user1").child("DATA_PERS")
    private var dataCar = baseR.child("User").child("user1").child("DATA_CAR")
    private var dataStatus = baseR.child("USer").child("user1").child("STATUS")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        ButterKnife.bind(this)
        setToolBar()
        getDataname()
        getDataCar()
        getDataStatus()
    }

    fun getDataCar() {
        dataCar.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataColorCar = dataSnapshot.child("color").getValue(String::class.java)
                val dataBrand = dataSnapshot.child("Type").getValue(String::class.java)
                val dataLicencePlate = dataSnapshot.child("LP").getValue(String::class.java)
                brandCar.text = dataBrand.toString() ?: "q"
                colorCar.text = dataColorCar.toString() ?: "q"
                licencePlate.text = dataLicencePlate.toString() ?: "q"
            }

            override fun onCancelled(p0: DatabaseError?) {

            }

        })
    }

    fun getDataname() {
        dataName.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val namePer = dataSnapshot.child("name").getValue(String::class.java)
                val surNamePer = dataSnapshot.child("surname").getValue(String::class.java)
                nameClient.text = namePer.toString() ?: "q"
                surNameClient.text = surNamePer.toString() ?: "q"
            }

            override fun onCancelled(p0: DatabaseError?) {

            }

        })
    }

    fun getDataStatus() {
        dataStatus.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataStatusAlarm = dataSnapshot.child("Salarm").getValue(Int::class.java)
                alarmStatus.text = "" ?: "q"
                if (dataStatusAlarm == 0) {
                    alarmStatus.text = "ปลอดภัย"
                } else {
                    alarmStatus.text = "ไม่ปลอดภัย"
                }
            }

            override fun onCancelled(p0: DatabaseError?) {

            }

        })
    }

    fun setToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        title.text = "ตำแหน่งของรถ"
    }

    var latitude = 1.0
    var logitude = 2.0
    fun getlo(x: Double, y: Double) {
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
