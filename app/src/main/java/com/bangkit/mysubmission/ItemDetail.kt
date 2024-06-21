package com.bangkit.mysubmission

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ItemDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val dataNinja = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("ninja", Character::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("ninja")
        }

        val image = findViewById<ImageView>(R.id.img_chara)
        dataNinja?.photo?.let { image.setImageResource(it) }
        val name = findViewById<TextView>(R.id.name_chara)
        name.text = dataNinja?.name
        val description = findViewById<TextView>(R.id.desc_chara)
        description.text = dataNinja?.description

        val shareBtn = findViewById<Button>(R.id.share_btn)
        shareBtn.setOnClickListener {
            val value = "${name.text} - ${description.text}"
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, value)
                type = "text/plain"
            }
            startActivity(sendIntent)
        }
    }
}