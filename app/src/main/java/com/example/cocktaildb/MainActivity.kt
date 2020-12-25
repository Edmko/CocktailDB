package com.example.cocktaildb

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        this.getPreferences(Context.MODE_PRIVATE).edit().clear().apply()
        super.onDestroy()
    }

}
