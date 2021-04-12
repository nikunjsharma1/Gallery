package com.nikunj.galleryapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.system.Os.open
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikunj.galleryapp.GalleryApplication
import com.nikunj.galleryapp.GalleryViewModelFactory
import com.nikunj.galleryapp.R
import com.nikunj.galleryapp.data.model.Photo
import com.nikunj.galleryapp.databinding.ActivityMainBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var mainAdapter: MainAdapter? = null

    @Inject
    lateinit var galleryViewModelFactory: GalleryViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
       setSupportActionBar(binding.toolBar)

        injectDagger()

        createViewModel()

        setBinding()


        observeViewModel()
        val drawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolBar , R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(drawerToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerToggle.isDrawerIndicatorEnabled = true;
        drawerToggle.syncState()


         val navView=binding.navigationView
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Main Activity", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            android.R.id.home -> {
//                binding.drawerLayout.openDrawer(GravityCompat.START)
//               val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                Toast.makeText(this, "Main Activity", Toast.LENGTH_SHORT).show()
//                true
//            }
//            else -> this.onOptionsItemSelected(item)
//        }
//    }
    private fun showList(photo: List<Photo>) {
        mainAdapter = MainAdapter(photo)
        resultsBeanRecyclerView.layoutManager = GridLayoutManager(this, 2)

        binding.resultsBeanRecyclerView.adapter = mainAdapter
        mainAdapter?.notifyDataSetChanged()



    }

    private fun observeViewModel() {
        mainViewModel.getGallery(
            "flickr.photos.getRecent",
            20,
            1,
            "6f102c62f41998d151e5a1b48713cf13",
            "json",
            1,
            "url_s"
        )
        mainViewModel.galleryList.observe(this, Observer { list ->
            list.forEach {
                Log.d("nikunji", it.photos.photo.toString())
                showList(it.photos.photo)
            }
        })
    }

    private fun setBinding() {


    }

    private fun createViewModel() {
        mainViewModel =
            ViewModelProviders.of(this, galleryViewModelFactory)[MainViewModel::class.java]
    }

    private fun injectDagger() {
        GalleryApplication.instance.libraryComponent.inject(this)
    }
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}