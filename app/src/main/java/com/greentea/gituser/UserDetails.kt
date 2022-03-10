package com.greentea.gituser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.greentea.gituser.databinding.ActivityUserDetailsBinding

class UserDetails : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail User"

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        binding.imgItemPhoto.setImageResource(user.profilePhoto)
        binding.tvItemName.text = user.name
        binding.tvItemUsername.text = user.username
        binding.tvFollowerNumber.text = user.followers
        binding.tvFollowingNumber.text = user.following
        binding.tvItemCompany.text = user.company
        binding.tvItemLocation.text = user.location
        binding.tvRepositories.text = user.repositories
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}