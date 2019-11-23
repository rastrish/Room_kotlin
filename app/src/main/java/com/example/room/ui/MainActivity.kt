package com.example.room.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.data.roomdatabase.Entity.Word
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.view.*
import kotlinx.android.synthetic.main.delete_dialog.view.*

class MainActivity : AppCompatActivity() {

    private  val newWordActivityRequestCode = 1

    private lateinit var activityViewModel : ActivityViewModel
    private lateinit var mdialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = RecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        
//


        val fab = findViewById<FloatingActionButton>(R.id.fab)

        activityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        activityViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })

        fab.setOnClickListener {
            val intent = Intent(MainActivity@ this, AllWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        materialButtonDelete.setOnClickListener {
            activityViewModel.delete()
        }

        materialButtonDeleteWord.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this@MainActivity)
            val dialogView = layoutInflater.inflate(R.layout.delete_dialog, null)
            dialogBuilder.setView(dialogView)
            dialogBuilder.setPositiveButton("Submit") { dialogInterface, i ->
                activityViewModel.deleteWord(dialogView.etView.text.toString())
            }
            val b = dialogBuilder.create()
            b.show()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AllWordActivity.EXTRA_REPLY)?.let {
                val word = Word(it)
                activityViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "empty",
                Toast.LENGTH_LONG).show()
        }
    }
}
