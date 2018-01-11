package th.co.todsphol.add.projectone.activity

import android.annotation.SuppressLint
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import butterknife.BindView
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import th.co.todsphol.add.projectone.R
import android.content.Intent
import android.net.Uri
import android.support.annotation.Nullable
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import butterknife.ButterKnife
import com.google.android.gms.maps.model.CameraPosition
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {


    @BindView(R.id.toolbar) lateinit var toolBar: Toolbar
    @BindView(R.id.tv_toolbar_title) lateinit var title: TextView
    @BindView(R.id.tv_name_client) lateinit var nameClient: TextView
    @BindView(R.id.tv_surname_client) lateinit var surNameClient: TextView
    @BindView(R.id.tv_color_client) lateinit var colorCar: TextView
    @BindView(R.id.tv_brand_client) lateinit var brandCar: TextView
    @BindView(R.id.tv_county) lateinit var licencePlate: TextView
    @BindView(R.id.tv_status) lateinit var alarmStatus: TextView
    lateinit var mMap: GoogleMap
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
                brandCar.text = dataBrand.toString()
                colorCar.text = dataColorCar.toString()
                licencePlate.text = dataLicencePlate.toString()
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
                nameClient.text = namePer.toString()
                surNameClient.text = surNamePer.toString()
            }

            override fun onCancelled(p0: DatabaseError?) {

            }

        })
    }

    fun getDataStatus() {
        dataStatus.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataStatusAlarm = dataSnapshot.child("Salarm").getValue(Int::class.java)
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


    var latitude = 13.904098
    var logitude = 100.528136
    @SuppressLint("MissingPermission")
    fun getlo(lati: Double, longi: Double) {
        latitude = lati
        logitude = longi
//        val googleapi: GoogleApiClient? = null
//        val mlocation: LocationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        val criteria: Criteria? = null
//        val provider = mlocation.getBestProvider(criteria, true)
//        val location = mlocation.getLastKnownLocation(provider)
//        if (location != null) {
//
//        }
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
//        mMap.addMarker(MarkerOptions().position(chaengwattana).title("Chaengwattana"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(chaengwattana))
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f))
        mMap.isMyLocationEnabled = true
        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)
        mMap.setOnMapClickListener {
            val position = CameraPosition.Builder()
                    .target(chaengwattana)
                    .zoom(17.0F)
                    .bearing(0F)
                    .tilt(30F)
                    .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position))
        }


//        val locationManeger: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        try {
//            val criteria: Criteria? = null
//            val provider: String = locationManeger.getBestProvider(criteria, true)
//            val myLocation: Location = locationManeger.getLastKnownLocation(provider)
//            mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
//            val latitude: Double = myLocation.latitude
//            val logtitude: Double = myLocation.longitude
//            val latLng = LatLng(latitude, logtitude)
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(20F))
//            mMap.addMarker(MarkerOptions().position(latLng))
//        } catch (e: NullPointerException) {
//
//        }

    }


    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show()
        getlo(location.latitude, location.longitude)
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.action_favorite -> call()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingPermission")
    private fun call() {
        val callPhone = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0821945021"))
        startActivity(callPhone)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}
