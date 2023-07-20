package com.mapstest

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private  lateinit var langtitude : TextView
    private lateinit var longtitude : TextView
    private  lateinit var  allititude : TextView
    private  lateinit var  accuracy : TextView
    private  lateinit var  bearing : TextView
    private  lateinit var  speed : TextView
    private  lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        langtitude = findViewById(R.id.latitude)
        longtitude =findViewById(R.id.longitude)
        allititude = findViewById(R.id.altitude)
        accuracy = findViewById(R.id.akurasi)
        bearing = findViewById(R.id.bearing)
        speed = findViewById(R.id.speed)

         val button = findViewById<Button>(R.id.btn_find)

        button.setOnClickListener {
            getlocation()
        }

    }

    fun  getlocation() {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
            return
        }

        val  location = fusedLocationClient.lastLocation
        location.addOnSuccessListener {
            if(it!=null){
                val textlangtidude = it.latitude.toString()
                val textlongtidude = it.longitude.toString()
                val textallitidude  = it.altitude.toString()
                val textaccuracy =  it.accuracy.toString()
                val textbearing =  it.bearing.toString()
                val textspeed =  it.speed.toString()


                longtitude.text = textlongtidude
                langtitude.text = textlangtidude
                allititude.text = textallitidude
                accuracy.text = textaccuracy
                bearing.text = textbearing
                speed.text = textspeed
            }
        }





    }
}



