package org.serversmc.console

import org.bukkit.*
import org.bukkit.plugin.*

object Console {
	
	private lateinit var plugin: Plugin
	
	fun init(plugin: Plugin) {
		this.plugin = plugin
	}
	
	val consoleSender = Bukkit.getConsoleSender()
	private const val p = "[AutoRestart] "
	
	fun info(s: String) = Bukkit.getConsoleSender().sendMessage("${ChatColor.GREEN}$p$s")
	fun warn(s: String) = Bukkit.getConsoleSender().sendMessage("${ChatColor.YELLOW}$p$s")
	fun err(s: String) = Bukkit.getConsoleSender().sendMessage("${ChatColor.RED}$p$s")
	fun consoleSendMessage(s: String) = consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', s))
	
	fun catchError(e: Exception, loc: String) {
		err("There was an error in $loc")
		consoleSendMessage(e.toString())
		println(plugin.javaClass.name)
		e.stackTrace.forEach { if (it.toString().startsWith(plugin.javaClass.name)) consoleSendMessage("\t" + it.toString()) }
		err("End of error")
	}
	
}
