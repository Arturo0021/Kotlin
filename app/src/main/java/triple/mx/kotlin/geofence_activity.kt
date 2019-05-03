package triple.mx.kotlin

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class geofence_activity : AppCompatActivity() {

    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    private val INTERVAL: Long = 2000
    private val FASTEST_INTERVAL: Long = 1000
    lateinit var mLastLocation : Location
    internal lateinit var  mLocationRequest: LocationRequest
    private val REQUEST_PERMISSION_LOCATION = 10

    lateinit var btnStartupdate: Button
    lateinit var btnStopUpdates: Button
    lateinit var txtLat: TextView
    lateinit var txtLong: TextView
    lateinit var txtTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.geofence_activity)

        mLocationRequest = LocationRequest()

        btnStartupdate = findViewById(R.id.btn_start_upds)
        btnStopUpdates = findViewById(R.id.btn_stop_upds)
        txtLat = findViewById(R.id.txtLat);
        txtLong = findViewById(R.id.txtLong);
        txtTime = findViewById(R.id.txtTime);

        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGPS()
        }

        if(checkPermissionForLocation(this)) {
            startLocationUpdates()
        }

        btnStartupdate.setOnClickListener {
            if (checkPermissionForLocation(this)) {
                startLocationUpdates()
                btnStartupdate.isEnabled = false
                btnStopUpdates.isEnabled = true
            }
        }

        btnStopUpdates.setOnClickListener {
            stopLocationUpdates()
            txtTime.text = "Updates Stoped"
            btnStartupdate.isEnabled = true
            btnStopUpdates.isEnabled = false
        }

    }

    private fun buildAlertMessageNoGPS() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("GPS INACTIVO")
                .setCancelable(false)
                . setPositiveButton("Activar") { dialog, id ->
                    startActivityForResult(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),11)
                }
                .setNegativeButton("Cancelar"){ dialog, id ->
                    dialog.cancel()
                    finish()
                }
    }

    protected fun startLocationUpdates() {
        mLocationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest!!.setInterval(INTERVAL)
        mLocationRequest!!.setFastestInterval(FASTEST_INTERVAL)

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           return
        }

        mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation
            onLocationChanged(locationResult.lastLocation)
        }
    }

    fun onLocationChanged(location: Location){
        mLastLocation = location
        val date: Date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("hh:mm:ss a")

        txtTime.text = "Actualizado : " + sdf.format(date)
        txtLat.text = "LATITUD : " + mLastLocation.latitude
        txtLong.text = "LONGITUD : " + mLastLocation.longitude
    }

    private fun stopLocationUpdates() {
        mFusedLocationProviderClient!!.removeLocationUpdates(mLocationCallback)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            } else {
                Toast.makeText(this@geofence_activity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkPermissionForLocation(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_PERMISSION_LOCATION)
                false
            }
        } else {
            true
        }
    }

}
