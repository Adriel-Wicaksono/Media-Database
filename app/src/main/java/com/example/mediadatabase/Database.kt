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
    var username = ""
    private var createNewUser = true


    constructor(username: String) {
        this.username = username

        var firebase = FirebaseDatabase.getInstance()
        reference = firebase.getReference("users")

        reference.get().addOnSuccessListener { snapshot ->
            for (user in snapshot.children) {
                userList.add(user.key.toString())
                Log.w("MainActivity", "${user.key.toString()}")
                if (user.key.toString() == username) {
                    createNewUser = false
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
        }
    }

    fun addMedia(media : Media) {
        val mediaInfo = mapOf<String, Any?>(
            "date_watched" to media.dateWatched,
            "where" to media.where,
            "note" to media.note,
            "rating" to media.rating
        )
        reference.child(username)
            .child("media_list")
            .child(media.title)
            .setValue(mediaInfo)

    }
}