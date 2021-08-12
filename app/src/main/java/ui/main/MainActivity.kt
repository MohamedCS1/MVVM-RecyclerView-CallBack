package ui.main

import android.annotation.SuppressLint
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rest.R
import pojo.Post_Model
import java.util.*
import kotlin.collections.ArrayList
import android.net.wifi.WifiManager
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    var Post_View_Model:Post_View_Model? = null
    var my_layout:LinearLayout? = null
    var adapter:Adapter_Posts? = null
    var d:AlertDialog? = null
    var bu_wifi:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view:View = View.inflate(this@MainActivity,R.layout.dialog_check_wifi,null)
        val alertDialog = AlertDialog.Builder(this@MainActivity)
        d = alertDialog.setView(view).create()
        d!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        adapter = Adapter_Posts()

        my_layout = findViewById(R.id.my_layout)

        val recycler_view = findViewById<RecyclerView>(R.id.rv_posts)

        recycler_view.adapter = adapter

        val lm = LinearLayoutManager(this)

        recycler_view.layoutManager = lm

        Post_View_Model = Post_View_Model()

        Post_View_Model!!.get_Posts()

        Post_View_Model!!.MutableLiveData_Posts.observe(this,object :Observer<List<Post_Model>>
        {
            override fun onChanged(t: List<Post_Model>?) {
                adapter!!.setList(t as ArrayList<Post_Model>)
            }

        })


    }



    override fun onStart() {
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(wifiStateReceiver, intentFilter)
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiStateReceiver);
    }
    private val wifiStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        @SuppressLint("NotifyDataSetChanged")
        override fun onReceive(context: Context, intent: Intent) {
            val wifiStateExtra = intent.getIntExtra(
                WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN
            )

            when (wifiStateExtra) {

                WifiManager.WIFI_STATE_ENABLED -> {

                    Log.d("internet","STATE_ENABLED")
                    d!!.dismiss()
                }
                WifiManager.WIFI_STATE_DISABLED -> {

                    Log.d("internet","STATE_DISABLED")
                    d!!.show()
                    d!!.setCancelable(false)
                    bu_wifi = d!!.findViewById(R.id.bu_wifi)
                    bu_wifi!!.setOnClickListener {

                        startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    }

                }
            }
        }
    }
}