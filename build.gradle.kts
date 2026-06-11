import gg.meza.stonecraft.mod

plugins {
    id("gg.meza.stonecraft")
}

base {
    archivesName.set("HardcoreLanFix-${mod.loader}")
} // fix name

modSettings {
    var mod_license = findProperty("mod.license") ?: ""
    var mod_authors = findProperty("mod.authors") ?: ""

    var fabric_mc_version_range = findProperty("fabric_mc_version_range") ?: mod.minecraftVersion
    var neoforge_mc_version_range = findProperty("neoforge_mc_version_range") ?: "[${mod.minecraftVersion}]"

    variableReplacements = mapOf(
        "license" to mod_license,
        "authors" to mod_authors,

        "fabric_mc_version_range" to fabric_mc_version_range,
        "neoforge_mc_version_range" to neoforge_mc_version_range,

    )
}

// Example of overriding publishing settings
publishMods {
    modrinth {
        if (mod.isFabric) requires("fabric-api")
    }

    curseforge {
        clientRequired = false // Set as needed
        serverRequired = true // Set as needed
        if (mod.isFabric) requires("fabric-api")
    }
}
