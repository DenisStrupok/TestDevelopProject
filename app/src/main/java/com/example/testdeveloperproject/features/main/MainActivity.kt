package com.example.testdeveloperproject.features.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import com.example.testdeveloperproject.R
import com.example.testdeveloperproject.common.ui.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!chekInternetConnection()){
           showDialog()
        }
    }

    private fun chekInternetConnection(): Boolean {
        val connectionManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo

        if (networkInfo != null){
            if (networkInfo.isConnected){
                return true
            }
            return false
        }
        return false
    }

    private fun showDialog() {
       AlertDialog.Builder(this).apply {
           this.setTitle(getString(R.string.dialog_internet_connection_title))
           this.setMessage(getString(R.string.dialog_internet_connection_message))
           this.setCancelable(true)
           this.show()
       }
    }
}