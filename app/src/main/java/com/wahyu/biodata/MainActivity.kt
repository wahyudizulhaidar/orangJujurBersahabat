package com.wahyu.biodata

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wahyu.biodata.adapter.ListPersonAdapter
import com.wahyu.biodata.data.Person
import com.wahyu.biodata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var rvMain: RecyclerView
    private val list = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvMain = findViewById(R.id.rv_main)
        rvMain.setHasFixedSize(true)

        list.addAll(getListPersons())
        showRecyclerList()

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getListPersons(): ArrayList<Person> {
        val personName = resources.getStringArray(R.array.name_person)
        val personNick = resources.getStringArray(R.array.nick_person)
        val personDesc = resources.getStringArray(R.array.desc_person)
        val personPhoto = resources.obtainTypedArray(R.array.photo_person)
        val listPerson = ArrayList<Person>()
        for (i in personName.indices) {
            val person = Person(personName[i], personNick[i], personDesc[i], personPhoto.getResourceId(i, -1))
            listPerson.add(person)
        }

        personPhoto.recycle()
        return listPerson
    }

    private fun showRecyclerList() {
        rvMain.layoutManager = LinearLayoutManager(this)
        val listPersonAdapter = ListPersonAdapter(list)
        rvMain.adapter = listPersonAdapter

        listPersonAdapter.setOnItemClickCallback(object : ListPersonAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Person) {
                showSelectedPerson(data)
            }
        })
    }

    private fun showSelectedPerson(person: Person) {
        Toast.makeText(this, "Kamu memilih " + person.name, Toast.LENGTH_SHORT).show()
    }
}