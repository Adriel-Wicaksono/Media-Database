package com.example.mediadatabase

import android.util.Log
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Database : ChildEventListener {
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
                    userReference = reference.child(username)
                    userReference.addChildEventListener(this)
                    updateMediaList(user.child("media_list"))
                }
            }
        }
    }

    fun updateMediaList(mlSnapshot : DataSnapshot) {
        Log.w("MainActivity", "List was updated")
        mediaList.clear()
        for (media in mlSnapshot.children) {
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

        mediaList.sortByDescending { it.dateWatched }
    }

    fun addMedia(media : Media) {
        if (createNewUser) {
            userReference = reference.child(username)
            userReference.addChildEventListener(this)
        }

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

    override fun onChildAdded(
        snapshot: DataSnapshot,
        previousChildName: String?
    ) {
        Log.w("MainActivity", "Child updated")
        updateMediaList(snapshot)
    }

    override fun onChildChanged(
        snapshot: DataSnapshot,
        previousChildName: String?
    ) {
        Log.w("MainActivity", "Child changed")
        updateMediaList(snapshot)
    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
        Log.w("MainActivity", "Child removed")
        updateMediaList(snapshot)
    }

    override fun onChildMoved(
        snapshot: DataSnapshot,
        previousChildName: String?
    ) {
    }

    override fun onCancelled(error: DatabaseError) {
    }
}