package org.serversmc

import org.bukkit.plugin.java.*
import org.serversmc.utils.*

lateinit var PLUGIN: JavaPlugin

object ServersMC {
	
	fun init(plugin: JavaPlugin) {
		PLUGIN = plugin
	}
	
}