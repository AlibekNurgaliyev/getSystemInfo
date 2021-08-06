package com.example.myapplication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION.SDK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        allFunctions()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun allFunctions() {
        displayDate()
        displayTime()
//        wifiStatus()
        phoneModel()
        SDK()
        device()
        product()
        getSerial()
        IMEI()
    }

    private fun displayDate() {
        val date = SimpleDateFormat("dd/M/yyyy")
        val currentDate = date.format(Date())
        show_date.setText(currentDate)
        show_date.setOnClickListener {

            Toast.makeText(this, currentDate.toString(), Toast.LENGTH_SHORT).show()

        }
    }

    private fun displayTime() {
        val time = SimpleDateFormat("hh:mm:ss")
        val currentTime = time.format(Date())
        show_time.text = currentTime
        show_time.setOnClickListener {

            Toast.makeText(this, currentTime.toString(), Toast.LENGTH_SHORT).show()

        }
    }

//    private fun wifiStatus()
//    {
//        val cm = applicationContext. getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val wifi = cm?. getNetworkInfo(ConnectivityManager.TYPE_WIFI)
//        var status: String
//
//        if ( wifi.isConnected)
//        {
//            status = "Connected"
//            show_wifi.setText(status)
//            show_wifi.setOnClickListener{
//
//                Toast.makeText(this, "Connected",Toast.LENGTH_SHORT ).show()
//
//            }
//        }
//        else
//        {
//            status = "Disconnected"
//            show_wifi.setText(status)
//            show_wifi.setOnClickListener{
//
//                Toast.makeText(this, "Disconnected",Toast.LENGTH_SHORT ).show()
//
//            }
//        }
//    }

    private fun phoneModel() {
        val myDeviceModel: String = Build.MODEL
        show_phone_model.setText(myDeviceModel)
        show_phone_model.setOnClickListener {

            Toast.makeText(this, myDeviceModel, Toast.LENGTH_SHORT).show()
        }
    }

    private fun SDK() {
        val mySDK: String = SDK
        show_SDK.setText(mySDK)
        show_SDK.setOnClickListener {

            Toast.makeText(this, mySDK, Toast.LENGTH_SHORT).show()

        }
    }

    private fun device() {
        val myDevice: String = Build.DEVICE
        show_device.setText(myDevice)
        show_device.setOnClickListener {

            Toast.makeText(this, myDevice, Toast.LENGTH_SHORT).show()

        }
    }

    private fun product() {
        val myBuildProduct: String = Build.PRODUCT
        show_product.setText(myBuildProduct)
        show_product.setOnClickListener {

            Toast.makeText(this, myBuildProduct, Toast.LENGTH_SHORT).show()

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getSerial() {
        try {
            var serial = "unknown"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && serial == "unknown") {
                if (ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.READ_PHONE_STATE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    serial = Settings.Secure.getString(
                        applicationContext.contentResolver,
                        Settings.Secure.ANDROID_ID
                    )
                }
            } else
                serial = Build.SERIAL

            show_serial.text = serial
            show_serial.setOnClickListener {
                Toast.makeText(this, serial, Toast.LENGTH_SHORT).show()
            }
        } catch (ex: Exception) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun IMEI() {
        var myIMEI: String? = null
        try {
            val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val IMEI = tm.getImei()
            if (IMEI != null) {
                myIMEI = IMEI
                show_IMEI.setText(myIMEI)
                show_IMEI.setOnClickListener {

                    Toast.makeText(this, myIMEI, Toast.LENGTH_SHORT).show()

                }
            } else {
                show_IMEI.setText("IMEI is null")
            }
        } catch (ex: Exception) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()

        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.READ_PHONE_STATE
                )
            ) {

            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_PHONE_STATE),
                    2
                )

            }
        }
    }

}