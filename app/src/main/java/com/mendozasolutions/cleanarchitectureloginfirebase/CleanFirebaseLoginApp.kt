package com.mendozasolutions.cleanarchitectureloginfirebase

import android.app.Application
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.di.DaggerPresentationComponent
import com.mendozasolutions.cleanarchitectureloginfirebase.presentation.di.PresentationComponent

class CleanFirebaseLoginApp : Application() {

    private var appComponent : PresentationComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerPresentationComponent.create()
    }
    fun getAppComponent() : PresentationComponent? = appComponent

}