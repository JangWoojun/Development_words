package com.example.developmentwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {
    var mBackWait:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onBackPressed() {
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toasty.warning(this, "한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT, true).show()
        } else {
            finish() //액티비티 종료
        }
    }
}