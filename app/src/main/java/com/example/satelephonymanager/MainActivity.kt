package com.example.satelephonymanager

import android.Manifest
import android.content.Context
import android.content.Intent.ACTION_CONFIGURATION_CHANGED
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main)
        registerReceiver(MyREceiver(), IntentFilter(ACTION_CONFIGURATION_CHANGED))
        Log.d("mytag", "Registered call receiver.")




        var arr  = arrayOf(android.Manifest.permission.READ_PHONE_STATE,android.Manifest.permission.SEND_SMS)
        ActivityCompat.requestPermissions(this,arr, 123)

        var arr1  = arrayOf(android.Manifest.permission.CHANGE_CONFIGURATION
            )
        ActivityCompat.requestPermissions(this,arr1, 1)


        var telManager : TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


      var myclass = MyClass()


telManager.listen(myclass,PhoneStateListener.LISTEN_CALL_STATE)





        buttonShow.setOnClickListener {




            var softwarever = if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                    var softwarever = telManager.deviceSoftwareVersion
                textViewSoftwareVersion.setText("Software version is : $softwarever")

                var iemi = Settings.Secure.getString(applicationContext.contentResolver,Settings.Secure.ANDROID_ID)
                textViewIEMI.setText("IEMI numb : $iemi")

                var phnumber = telManager.line1Number
                textViewphonenumb.setText("phone number is : $phnumber")

               /*var simnumber =  telManager.simSerialNumber
                textViewSimNumber.setText("Sim number is : $simnumber")*/


            } else{
                        Toast.makeText(this, "PERMISSION NOT GRANTED",Toast.LENGTH_SHORT).show()
            }

        }





    }


    inner class MyClass : PhoneStateListener(){

        override fun onCallStateChanged(state: Int, phoneNumber: String?) {
            super.onCallStateChanged(state, phoneNumber)

            if(state == TelephonyManager.CALL_STATE_IDLE){
            }
            else if (state == TelephonyManager.CALL_STATE_RINGING){

                Log.i("mytag","incoming Number is : $phoneNumber")


            }
            else if(state == TelephonyManager.CALL_STATE_OFFHOOK){


            }
        }

    }
}