package com.greentea.gituser

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greentea.gituser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvUser: RecyclerView
    private val list = ArrayList<User>()

    private val listUsers: ArrayList<User>
        get(){
            resources.apply {
                val dataName = this.getStringArray(R.array.data_name)
                val dataUsername = this.getStringArray(R.array.data_username)
                val dataCompany = this.getStringArray(R.array.data_company)
                val dataLocation = this.getStringArray(R.array.data_location)
                val dataPhoto = this.obtainTypedArray(R.array.data_photo)
                val dataFollowers = this.getStringArray(R.array.data_followers)
                val dataFollowing = this.getStringArray(R.array.data_following)
                val dataRepositories = this.getStringArray(R.array.data_repositories)

                val listUser = ArrayList<User>()
                for (i in dataName.indices){
                    val user = User(dataName[i], dataUsername[i], dataCompany[i], dataLocation[i],
                        dataPhoto.getResourceId(i, -1), dataFollowers[i], dataFollowing[i],
                        dataRepositories[i])
                    listUser.add(user)
                }
                return listUser
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "GitHub User\'s"

        rvUser = binding.rvListUser
        rvUser.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecylerList()
    }

    private fun showRecylerList(){
        if(applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            rvUser.layoutManager = GridLayoutManager(this, 2)
        } else{
            rvUser.layoutManager = LinearLayoutManager(this)
        }

        val listUserAdapter = UserAdapter(list)
        rvUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User){
        val intentToUserDetails = Intent(this@MainActivity, UserDetails::class.java)
        intentToUserDetails.putExtra(UserDetails.EXTRA_USER, user)
        startActivity(intentToUserDetails)
    }
}