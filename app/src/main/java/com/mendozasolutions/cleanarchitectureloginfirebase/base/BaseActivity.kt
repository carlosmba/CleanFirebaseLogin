package com.mendozasolutions.cleanarchitectureloginfirebase.base

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

abstract class BaseActivity : AppCompatActivity() {
    protected val TAG = "BaseActivity"



    fun Context.toast(message : String) = Toast.makeText(this, message, Toast.LENGTH_LONG ).show()




}