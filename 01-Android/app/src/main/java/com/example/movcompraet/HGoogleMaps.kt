package com.example.movcompraet

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class HGoogleMaps : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hgoogle_maps)
        solicitarPermisos()

        val boton = findViewById<Button>(R.id.btn_ir_carolina)
        boton.setOnClickListener{
            irCarolina()
        }
        solicitarPermisos()
        iniciarLogicaMapa()
    }
    fun irCarolina () {
        val carolina = LatLng(-0.1825684318486696,
        -78.48447277600916)
        val zoom = 17f
        moverCamaraConZoom(carolina, zoom)
    }
    fun iniciarLogicaMapa () {
        val fragmentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync {googleMap ->
            if (googleMap != null) {
                mapa = googleMap
                establecerConfiguracionesMapa()
                irCarolina()
                val quicentro = LatLng (4.598056, -74.075833)
                val titulo = "Quicentro"
                val zoom = 17f
                anadirMarcador(quicentro, titulo)
                val poliLineaUno = googleMap.addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            LatLng(4.598056,-74.075833),
                            LatLng(5.598056,-75.075833),
                            LatLng(3.598056,-73.075833),
                            LatLng(2.598056,-72.075833)
                        )
                )
                poliLineaUno.tag = "linea-1"
                val poligonoUno = googleMap.addPolygon(
                    PolygonOptions()
                        .clickable(true)
                        .add(
                            LatLng(4.598056,-74.075833),
                            LatLng(5.598056,-75.075833),
                            LatLng(3.598056,-73.075833),
                            LatLng(2.598056,-72.075833)
                        )
                )
                poligonoUno.fillColor = -0xc771c4
                poligonoUno.tag = "poligono-1"
                escucharListener()
            }
        }
    }
    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val permisoFineLocation = ContextCompat.checkSelfPermission(
            contexto,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermisos = permisoFineLocation == PackageManager.PERMISSION_GRANTED

        if ( tienePermisos ) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this, //contexto
                arrayOf( //arreglo de permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 //código de petición de los permisos
            )
        }
    }
    fun anadirMarcador (latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions().position(latLng).title(title)
        )!!
    }
    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng,zoom)
        )
    }
    fun escucharListener() {
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener ${it}")
            it.tag //ID
        }
        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolylineClickListener ${it}")
            it.tag //ID
        }
        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMapClickListener ${it}")
            it.tag //ID
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
        }
        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }
    fun establecerConfiguracionesMapa () {
        val contexto = this.applicationContext
        with(mapa) {//verificar que el mapa existe (if(mapa != null))
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                uiSettings.isMyLocationButtonEnabled == true // no tenemos aun permisos
                mapa.isMyLocationEnabled == true // no tenemos aun permisos
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

}