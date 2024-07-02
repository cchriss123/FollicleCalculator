package com.example.folliclecalulator.service

import com.example.folliclecalulator.poko.Zone

object ZoneService {
    private val zones = mutableListOf<Zone>()

    fun addZone(zoneName: String) {
        if (zoneName.isNotBlank()) {
            zones.add(Zone(name = zoneName))
        }
    }

    fun getAllZones(): List<Zone> {
        println("Getting all zones: ${zones.map { it.name }}")
        return zones.toList()
    }
}
