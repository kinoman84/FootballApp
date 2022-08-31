package ru.alexeybuchnev.football

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.alexeybuchnev.football.presentation.palyerslist.PlayersListFragment
import ru.alexeybuchnev.football.presentation.teamdetails.TeamDetailsFragment
import ru.alexeybuchnev.football.presentation.teams.TeamsListFragment

class MainActivity :
    AppCompatActivity(),
    TeamsListFragment.TeamListItemClickListener,
    TeamDetailsFragment.PlayerButtonCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToTeamList()
        }
    }

    private fun routeToTeamList() {

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                TeamsListFragment.newInstance(),
                TeamsListFragment::class.java.simpleName
            )
            .commit()
    }

    //TODO пересмотреть лекцию про фрагменты и перечитать доку
    private fun routeToTeamDetails(teamId: Int) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                TeamDetailsFragment.newInstance(teamId),
                TeamDetailsFragment::class.java.simpleName
            )
            .addToBackStack("trans:${TeamDetailsFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeToPlayersList(teamId: Int) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                PlayersListFragment.newInstance(teamId),
                PlayersListFragment::class.java.simpleName
            )
            .addToBackStack("trans:${PlayersListFragment::class.java.simpleName}")
            .commit()
    }

    override fun onTeamSelected(teamId: Int) {
        routeToTeamDetails(teamId)
    }

    override fun routeToPlayerList(teamId: Int) {
        routeToPlayersList(teamId)
    }
}