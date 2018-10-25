package com.algonquinlive.mudr0003.contactslist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button

class Welcome : Activity() {

    val ACTIVITYNAME = "<<<Welcome.kt>>>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Log.i(ACTIVITYNAME, "begin onCreate()");

        var Mybutton = findViewById<Button>(R.id.welcomeButton)


        Mybutton.setOnClickListener{
            //create an Intent to go to the list activity
            Log.i(ACTIVITYNAME, "User clicked the button")

            val newActivity = Intent( this, ListContacts::class.java)

            //transition to new activity

            startActivity(newActivity);

        }


        Log.i(ACTIVITYNAME, "end onCreate()");

    }
}
