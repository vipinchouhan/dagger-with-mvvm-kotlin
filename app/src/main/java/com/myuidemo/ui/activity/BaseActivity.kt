package com.myuidemo.ui.activity

import android.os.*
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView

import android.widget.Toast
import dagger.android.AndroidInjection

open class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureDagger()
    }
    private fun configureDagger() {
        AndroidInjection.inject(this)
    }

    fun showToast(msg: String): Unit {
        Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_LONG).show()
    }
    fun setupToolbar(
        toolbar: Toolbar

    ) {
        toolbar.title = title

        configureToolBar(toolbar)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }



    }

    fun configureToolBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.show()

    }



}