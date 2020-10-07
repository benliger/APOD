package com.benliger.nasaapod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.benliger.nasaapod.ui.list.ListAstronomyPictureFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListAstronomyPictureFragment.newInstance())
                .commitNow()
        }
    }
}