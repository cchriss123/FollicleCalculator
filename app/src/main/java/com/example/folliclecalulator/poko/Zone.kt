package com.example.folliclecalulator.poko

import java.util.UUID

data class Zone(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    var caliber: Double = 0.0,
    var fuPerCm2: Double = 0.0,
    var hairPerCm2: Double = 0.0,
    var areaInCm2: Double = 0.0,
    var donorDesiredCoverageValue: Double = 0.0,
    var recipientDesiredCoverageValue: Double = 0.0
) {
    val fuPerZone: Double
        get() = areaInCm2 * fuPerCm2

    val hairPerFu: Double
        get() = hairPerCm2 / fuPerCm2

    val coverageValue: Double
        get() = caliber * hairPerCm2

    val hairPerZone: Double
        get() = areaInCm2 * hairPerCm2

    val fuExtractedToReachDonorDesiredCoverageValue: Double
        get() = fuPerZone - ((areaInCm2 * donorDesiredCoverageValue) / (caliber * hairPerFu))

    val fuImplantedToReachDesiredRecipientCoverageValue: Double
        get() {
            val startingCoverageValue = caliber * hairPerCm2
            val coverageValueDifference = recipientDesiredCoverageValue - startingCoverageValue
            return (areaInCm2 * coverageValueDifference) / (caliber * hairPerFu)
        }
}
