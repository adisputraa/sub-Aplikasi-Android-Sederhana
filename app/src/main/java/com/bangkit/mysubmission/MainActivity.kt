package com.bangkit.mysubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnime = findViewById(R.id.rv_anime)
        rvAnime.setHasFixedSize(true)

        list.addAll(getListChar())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                // Lakukan navigasi ke aktivitas tentang di sini
                val intent = Intent(this, AboutMe::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListChar(): ArrayList<Character> {
        val dataName = resources.getStringArray(R.array.data_anime)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listChar = ArrayList<Character>()
        for (i in dataName.indices) {
            val char = Character(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listChar.add(char)
        }

        return listChar
    }

    private fun showRecyclerList() {
        rvAnime.layoutManager = LinearLayoutManager(this)
        val listCharAdapter = ListCharAdapter(list)
        rvAnime.adapter = listCharAdapter

        listCharAdapter.setOnItemClickCallback(object : ListCharAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Character) {
                showSelectedChar(data)
            }
        })
    }

    private fun showSelectedChar(char: Character) {
        Toast.makeText(this, "Kamu memilih " + char.name, Toast.LENGTH_SHORT).show()
    }
}