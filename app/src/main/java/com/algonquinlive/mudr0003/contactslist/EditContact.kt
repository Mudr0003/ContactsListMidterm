package com.algonquinlive.mudr0003.contactslist

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView

class EditContact : Activity() {

    val ACTIVITYNAME = "<<<EditContact.kt>>>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        Log.i(ACTIVITYNAME, "begin onCreate()");

        var first: String = intent.getStringExtra("first")
        var last: String = intent.getStringExtra("last")
        var email: String = intent.getStringExtra("email")
        var phone: String = intent.getStringExtra("phone")
        var index: Int = intent.getIntExtra("index",0)

        var firstfield = findViewById<TextView>(R.id.editFirstName)
        var lastfield = findViewById<TextView>(R.id.editLastName)
        var emailfield = findViewById<TextView>(R.id.editEmail)
        var phonefield = findViewById<TextView>(R.id.editPhone)

        firstfield.text = first
        lastfield.text = last
        emailfield.text = email
        phonefield.text = phone


        var Mybutton = findViewById<Button>(R.id.editSaveButton)

        Mybutton.setOnClickListener{
            //create an Intent to go to the list activity
            Log.i(ACTIVITYNAME, "User clicked the save button")

            val listActivity = Intent( this, ListContacts::class.java)

            //transition to new activity
            listActivity.putExtra("newfirst",firstfield.text.toString())
            listActivity.putExtra("newlast",lastfield.text.toString())
            listActivity.putExtra("newemail",emailfield.text.toString())
            listActivity.putExtra("newphone",phonefield.text.toString())
            listActivity.putExtra("index",index)

            setResult(RESULT_OK, listActivity)
            this.finish()
        }





        var emailbutton = findViewById<ImageButton>(R.id.editEmailButton)

        emailbutton.setOnClickListener{
            //create an Intent to go to the list activity
            Log.i(ACTIVITYNAME, "User clicked the email button")

            val listActivity = Intent( this, ListContacts::class.java)

            //transition to new activity


            var emailaddr = findViewById<TextView>(R.id.editEmail).text.toString()

            val addresses = arrayOf(emailaddr)


                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:") // only email apps should handle this
                            putExtra(Intent.EXTRA_EMAIL, addresses)
                        }
                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        }

        }



        var phonebutton = findViewById<ImageButton>(R.id.editPhoneButton)

        phonebutton.setOnClickListener{
            //create an Intent to go to the list activity
            Log.i(ACTIVITYNAME, "User clicked the phone button")


            var phonenumber = findViewById<TextView>(R.id.editPhone).text.toString()


            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phonenumber")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)


        }

    }


        Log.i(ACTIVITYNAME, "end onCreate()");
    }
}
