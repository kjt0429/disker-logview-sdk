package com.disker.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.disker.sdk.LogDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("HIVE", "Start MainActivity")
        Log.d("HIVE", "Start MainActivity - 1")

        findViewById<Button>(R.id.showButton).setOnClickListener {
            LogDialog(this, "HIVE").show()
        }
    }
}