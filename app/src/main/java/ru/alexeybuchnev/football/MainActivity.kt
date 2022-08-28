package ru.alexeybuchnev.football

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.alexeybuchnev.football.presentation.teams.TeamsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToTeamList()
        }
    }

    private fun routeToTeamList(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, TeamsListFragment())
            .commit()
    }
}