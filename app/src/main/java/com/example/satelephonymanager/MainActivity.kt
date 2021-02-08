package com.example.satelephonymanager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var arr  = arrayOf(android.Manifest.permission.READ_PHONE_STATE)
        ActivityCompat.requestPermissions(this,arr, 123)



        var telManager : TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        buttonShow.setOnClickListener {


var iemi = Settings.Secure.getString(applicationContext.contentResolver,Settings.Secure.ANDROID_ID)
            textViewIEMI.setText("IEMI numb : $iemi")

            var softwarever = if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                    var softwarever = telManager.deviceSoftwareVersion
                textViewSoftwareVersion.setText("Software version is : $softwarever")

             

                var phnumber = telManager.line1Number
                textViewphonenumb.setText("phone number is : $phnumber")

               /*var simnumber =  telManager.simSerialNumber
                textViewSimNumber.setText("Sim number is : $simnumber")*/


            } else{

            }

        }



    }
}