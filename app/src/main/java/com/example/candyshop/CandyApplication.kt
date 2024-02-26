package com.example.candyshop

import android.app.Application
import com.example.candyshop.data.AppContainer
import com.example.candyshop.data.DefaultAppContainer

class CandyApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}