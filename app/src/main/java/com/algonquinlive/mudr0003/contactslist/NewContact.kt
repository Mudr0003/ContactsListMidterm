package com.algonquinlive.mudr0003.contactslist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_new_contact.*

class NewContact : Activity() {

    val ACTIVITYNAME = "<<<NewContact.kt>>>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        Log.i(ACTIVITYNAME, "begin onCreate()");


        var MyAcceptbutton = findViewById<Button>(R.id.newAcceptButton)


        MyAcceptbutton.setOnClickListener{
            //create an Intent to go to the list activity
            Log.i(ACTIVITYNAME, "User clicked the button")


            //transition to new activity

            val listActivity = Intent( this, ListContacts::class.java)

            //transition to new activity
            listActivity.putExtra("newfirst",newFirstName.text.toString())
            listActivity.putExtra("newlast",newLastName.text.toString())
            listActivity.putExtra("newemail",newEmail.text.toString())
            listActivity.putExtra("newphone",newPhone.text.toString())


            setResult(RESULT_OK, listActivity)
            //startActivity(listActivity)
            this.finish()

        }



        var MyCancelbutton = findViewById<Button>(R.id.newCancelButton)


        MyCancelbutton.setOnClickListener{
            //create an Intent to go to the list activity
            Log.i(ACTIVITYNAME, "User clicked the button")

            val newActivity = Intent( this, ListContacts::class.java)

            //transition to new activity

            this.finish()

        }



        Log.i(ACTIVITYNAME, "end onCreate()");

    }
}
