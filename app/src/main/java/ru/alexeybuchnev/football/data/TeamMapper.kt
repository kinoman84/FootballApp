package ru.alexeybuchnev.football.data

import ru.alexeybuchnev.football.data.local.room.model.TeamDbModel
import ru.alexeybuchnev.football.data.local.room.model.TeamWithVenueDbModel
import ru.alexeybuchnev.football.data.local.room.model.VenueDbModel
import ru.alexeybuchnev.football.data.remote.retrofit.dto.PlayerDto
import ru.alexeybuchnev.football.data.remote.retrofit.dto.TeamItemDto
import ru.alexeybuchnev.football.domain.entity.Player
import ru.alexeybuchnev.football.domain.entity.Team
import ru.alexeybuchnev.football.domain.entity.Venue

object TeamMapper {

    fun teamDbModelToTeam(teamDb: TeamDbModel, venueDbModel: VenueDbModel? = null): Team =
        Team(
            id = teamDb.id,
            name = teamDb.name,
            founded = teamDb.founded,
            logoUrl = teamDb.logoUrl,
            venueDbModel?.let { venueDbToVenue(it) }
        )

    fun venueDbToVenue(venueDbModel: VenueDbModel): Venue =
        Venue(
            id = venueDbModel.id,
            name = venueDbModel.name,
            address = venueDbModel.address,
            city = venueDbModel.city,
            capacity = venueDbModel.capacity,
            imageUrl = venueDbModel.imageUrl
        )

    fun teamItemDtoToTeamDbModel(teamItemDto: TeamItemDto): TeamDbModel {
        with(teamItemDto.teamData) {
            return TeamDbModel(
                id = this.id,
                name = this.name,
                founded = this.founded,
                logoUrl = this.logoUrl
            )
        }

    }

    fun teamItemDtoToVenueDbModel(teamItemDto: TeamItemDto): VenueDbModel {
        with(teamItemDto.venueData) {
            return VenueDbModel(
                id = this.id,
                name = this.name,
                address = this.address.mnemonicToChar(),
                city = this.city,
                capacity = this.capacity,
                imageUrl = this.imageUrl,
                teamId = teamItemDto.teamData.id
            )
        }
    }

    fun teamWithVenueDbModelToTeam(teamWithVenueDbModel: TeamWithVenueDbModel): Team {
        return Team(
            id = teamWithVenueDbModel.id,
            name = teamWithVenueDbModel.name,
            founded = teamWithVenueDbModel.founded,
            logoUrl = teamWithVenueDbModel.logoUrl,
            venueDbToVenue(teamWithVenueDbModel.venueDbModel)
        )
    }

    fun playerDtoToPlayer(playerDto: PlayerDto): Player {
        return Player(
            id = playerDto.id,
            name = playerDto.name,
            age = playerDto.age,
            number = playerDto.number,
            position = playerDto.position,
            photoUrl = playerDto.photoUrl
        )
    }

    fun playerDtoListToPlayerList(playersDto: List<PlayerDto>): List<Player> {
        return playersDto.map { playerDtoToPlayer(it) }
    }

    /**
     * В некоторых случаях текст содержал HTML мнемоники типа &apos; функция их уберает.
     * + можно будет быстро добавить замену других мнемоников при необходимости
     */
    private fun String.mnemonicToChar() : String {

        var clearedString = this

        val mnemonics = mapOf(
            "&apos;" to "'"
        )

        for (mnemonic in mnemonics) {
            clearedString = clearedString.replace(mnemonic.key, mnemonic.value)
        }

        return clearedString
    }
}