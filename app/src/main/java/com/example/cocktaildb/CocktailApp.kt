package com.example.cocktaildb

import android.app.Application
import android.content.Context
import com.example.cocktaildb.data.AppDatabase
import com.facebook.stetho.Stetho

open class CocktailApp : Application() {
    companion object {
        lateinit var appDatabase: AppDatabase
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = AppDatabase.getInstance(this)
        appContext = this
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}