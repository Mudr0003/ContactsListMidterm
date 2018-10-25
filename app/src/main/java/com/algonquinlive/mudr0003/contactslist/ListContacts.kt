package com.algonquinlive.mudr0003.contactslist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ListContacts : Activity() {

    val ACTIVITYNAME = "<<<ListContacts.kt>>>"
    val SAVEDCODE = 99
    val ADDINGCODE =66

    class Contact () {
        var first = "first"
        var last = "last"
        var email = "email"
        var phone = "phone"

    }

    var contacts_count = 0
    var contacts_index = 0
    var contacts = Array(1000){Contact()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_contacts)

        Log.i(ACTIVITYNAME, "begin onCreate()");


        var contact1 = Contact()

        contact1.first = "Robin"
        contact1.last = "Cheese"
        contact1.email = "123@algonquinlive.com"
        contact1.phone = "613-829-1234"


        contacts[0] = contact1

        var contact2 = Contact()
        contact2.first = "Billy"
        contact2.last = "Bob"
        contact2.email = "bill@algonquinlive.com"
        contact2.phone = "613-829-0000"

        contacts[1] = contact2

        var contact3 = Contact()
        contact3.first = "Sally"
        contact3.last = "Smith"
        contact3.email = "sally@algonquinlive.com"
        contact3.phone = "613-829-4321"

        contacts[2] = contact3
        contacts_count=3


        var Mybutton = findViewById<Button>(R.id.listAddButton)
        var listView = findViewById<ListView>(R.id.listContacts)


        Mybutton.setOnClickListener{
            //create an Intent to go to the list activity
            Log.i(ACTIVITYNAME, "User clicked the button")

            val newActivity = Intent( this, NewContact::class.java)

            //transition to new activity

            startActivityForResult(newActivity,ADDINGCODE);
        }



        var listObj = ListAdapter(this)
        listView.adapter = listObj
        listObj.notifyDataSetChanged()



        Log.i(ACTIVITYNAME, "end onCreate()");

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, rawdata: Intent?) {
        super.onActivityResult(requestCode, resultCode, rawdata)

        if (requestCode == SAVEDCODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (rawdata !== null) {

                    var data:Intent = rawdata

                    var index: Int = data.getIntExtra("index", 0)

                    val newfirst = data.getStringExtra("newfirst")
                    val newlast = data.getStringExtra("newlast")
                    val newemail = data.getStringExtra("newemail")
                    val newphone = data.getStringExtra("newphone")

                    contacts[index].first = newfirst
                    contacts[index].last = newlast
                    contacts[index].email = newemail
                    contacts[index].phone = newphone

                    var listView = findViewById<ListView>(R.id.listContacts)
                    var listObj = ListAdapter(this)
                    listView.adapter = listObj
                    listObj.notifyDataSetChanged()
                }
            }
        } else {

            if (requestCode == ADDINGCODE) {
                if (resultCode == Activity.RESULT_OK) {
                    if (rawdata !== null) {

                        var data:Intent = rawdata

                        val newfirst = data.getStringExtra("newfirst")
                        val newlast = data.getStringExtra("newlast")
                        val newemail = data.getStringExtra("newemail")
                        val newphone = data.getStringExtra("newphone")

                        contacts[contacts_count].first = newfirst
                        contacts[contacts_count].last = newlast
                        contacts[contacts_count].email = newemail
                        contacts[contacts_count].phone = newphone

                        contacts_count++

                        var listView = findViewById<ListView>(R.id.listContacts)
                        var listObj = ListAdapter(this)
                        listView.adapter = listObj
                        listObj.notifyDataSetChanged()
                    }
                }
            }



        }
    }






    inner class ListAdapter(ctx : Context) : ArrayAdapter<String>(ctx, 0 ) {

        override fun getCount(): Int {
           return contacts_count
        }

        override fun getItem(position: Int): String {

            return contacts[position].first + " " + contacts[position].last
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var inflater = LayoutInflater.from(parent.context)

            var thisRow: View?


            thisRow = inflater.inflate(R.layout.contactrow, null)

            thisRow.findViewById<TextView>(R.id.item_name)
            //var textView = thisRow.findViewById<TextView>(R.id.out_message_text)

            val contact = thisRow?.findViewById(R.id.item_name) as TextView

            contact.text = getItem(position) // get the string at position



            thisRow.setOnClickListener {
                //create an Intent to go to the edit activity
                Log.i(ACTIVITYNAME, "User clicked the contact row")

                val newActivity = Intent( contact.getContext(), EditContact::class.java)


                //transition to new activity
                newActivity.putExtra("index",position)
                newActivity.putExtra("first",contacts[position].first)
                newActivity.putExtra("last",contacts[position].last)
                newActivity.putExtra("email",contacts[position].email)
                newActivity.putExtra("phone",contacts[position].phone)

                startActivityForResult(newActivity,SAVEDCODE);
            }


                return thisRow
        }



    }



}
