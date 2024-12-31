package com.example.myapplicationfragmentsnav

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplicationfragmentsnav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(Toolbar(this))


        val navController = findNavController(R.id.nav_host_fragment_activity_bottem_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //  navController.navigate(R.id.navigation_homfragemnt)

      //  val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,
      //      R.id.addNoteFragment))
      //  setupActionBarWithNavController(navController, appBarConfiguration)


    }


}