package org.serversmc

import org.bukkit.plugin.java.*

lateinit var PLUGIN: JavaPlugin

object ServersMC {
	
	fun init(plugin: JavaPlugin) {
		PLUGIN = plugin
	}
	
}