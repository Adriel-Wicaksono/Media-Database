package com.example.mediadatabase

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Database {
    var firebase = FirebaseDatabase.getInstance()
    lateinit var reference : DatabaseReference
    lateinit var userReference : DatabaseReference
    var mediaList = ArrayList<Media>()
    var userList = ArrayList<String>()
    constructor(username: String) {
        var firebase = FirebaseDatabase.getInstance()
        reference = firebase.getReference("users")

        var userFound = false

        reference.get().addOnSuccessListener { snapshot ->
            for (user in snapshot.children) {
                userList.add(user.key.toString())
                Log.w("MainActivity", "${user.key.toString()}")
                if (user.key.toString() == username) {
                    userFound = true
                    for (media in user.child("media_list").children) {
                        val title = media.key
                        val where = media.child("where").value
                        val rating = media.child("rating").value
                        val note = media.child("note").value
                        val dateWatched = media.child("date_watched").value
                        if (title != null && dateWatched != null) {
                            Log.w("MainActivity", title.toString())
                            mediaList.add(
                                Media(
                                    title.toString(),
                                    where.toString(),
                                    dateWatched.toString().toLong(),
                                    rating.toString().toFloat(),
                                    note.toString()
                                )
                            )
                        }
                    }
                }
            }

            if (!userFound) {
                Log.w("MainActivity", "User not found, creating new one")
                val newUser = mapOf(
                    "media_list" to emptyMap<String, Any>()
                )
                reference.child(username).setValue(newUser)
                    .addOnSuccessListener {
                        Log.w("MainActivity", "User successfully created!")
                    }
                    .addOnFailureListener { e ->
                        Log.e("MainActivity", "Failed to create user: ${e.message}")
                    }

                reference.child(username).get().addOnSuccessListener { snapshot ->
                    Log.w("MainActivity", "New user snapshot: ${snapshot.value}")
                }
            }
        }
    }
}