package com.maestre.preferenciasapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import com.google.android.material.appbar.MaterialToolbar
import com.maestre.preferenciasapp.databinding.ActivityMainBinding
import com.maestre.preferenciasapp.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        // Establecer el tema antes de inflar las vistas
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        //tema
        val themeName = sharedPreferences?.getString("pref_themes", "AppNewTheme") ?: "AppNewTheme"
        // Lee el nombre del tema
        when (themeName) {
            "AppNewTheme" -> setTheme(R.style.AppNewTheme)
            "Base.Theme.PreferenciasApp" -> setTheme(R.style.Base_Theme_PreferenciasApp)
        }
        //modo nocturno
        val isDarkModeEnabled = sharedPreferences?.getBoolean("def_nigthmode", false) ?: false
        if (isDarkModeEnabled){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        Toast.makeText(this, "Setting-Tema: $themeName ", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Setting-Modo Nocturno: $isDarkModeEnabled ", Toast.LENGTH_SHORT).show()

        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configurar MaterialToolbar como actionBar
        val toolbar: MaterialToolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mi_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.preferencias -> {
                var settingIntent: Intent = Intent(this, SettingActivity::class.java)
                startActivity(settingIntent)
                finish()
                true
            }
            R.id.home -> {
                var settingIntent: Intent = Intent(this, MainActivity::class.java)
                startActivity(settingIntent)
                finish()
                true
            }
            else -> false
        }
    }
}