package io.github.disker

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader

class LogDialog(private val activity: Activity, private val tag: String) :
    Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen) {

    var t: Thread? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.log_dialog)

        val pid = android.os.Process.myPid()
        //val commandArray = mutableListOf("logcat", "-s", "HIVE:*", "-v", "time", "--pid=$pid")
        val commandArray = mutableListOf("logcat", "-s", "$tag:*", "-v", "time", "--pid=$pid")
        val logcat = Runtime.getRuntime().exec(commandArray.toTypedArray())
        val br = BufferedReader(InputStreamReader(logcat?.inputStream), 4 * 1024)

        val sb = StringBuffer()
        var line: String
        val separator = System.getProperty("line.separator")


        t = Thread {

            while (br.readLine().also { line = it } != null) {

                try {
                    sb.append(line + separator)

                    activity.runOnUiThread {
                        with(findViewById<TextView>(R.id.logTextView)) {
                            text = sb.toString()
                        }
                    }
                } catch (_: Exception) {
                }
            }
        }
        t?.start()
    }

    override fun dismiss() {
        t?.interrupt()
        super.dismiss()
    }
}