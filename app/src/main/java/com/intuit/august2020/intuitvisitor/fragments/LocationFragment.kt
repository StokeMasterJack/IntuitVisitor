package com.intuit.august2020.intuitvisitor.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.intuit.august2020.intuitvisitor.R
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_location, container, false)
        val button = rootView.findViewById<Button>(R.id.buttonLocate)
        button.setOnClickListener {
            Toast.makeText(activity, "Acquiring Location", Toast.LENGTH_LONG).show()
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    val cafeteriaLocation = Location(LocationManager.GPS_PROVIDER)
                    cafeteriaLocation.latitude = 37.4298583
                    cafeteriaLocation.longitude = -122.0966004
                    if (location != null) {
                        rootView.findViewById<TextView>(R.id.textLocation).text =
                            "You are ${cafeteriaLocation.distanceTo(location)}m from cafeteria\n${location.latitude},${location.longitude}"
                    }
                }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            )
        {
            // If the Permissions API is available (Android 6+)
            // we request permission (see result in onRequestPermissionResult)
            // TODO: Show a message if the user denied the permission
            requestPermissions(
                listOf("android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.ACCESS_FINE_LOCATION").toTypedArray(),
                200)
        }


        // Inflate the layout for this fragment
        return rootView
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}