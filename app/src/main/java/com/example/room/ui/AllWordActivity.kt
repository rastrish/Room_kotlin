package com.example.room.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.room.R
import kotlinx.android.synthetic.main.activity_main2.*


class AllWordActivity : AppCompatActivity() {

    companion object
    {
        const val EXTRA_REPLY = "com.example.room"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button.setOnClickListener {
            textView2.text = textview.text!!.substring(0,1).capitalize()

            val replyIntent = Intent()
            if (TextUtils.isEmpty(textview.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = textview.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }
}
