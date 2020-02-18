package org.serversmc.utils

import org.bukkit.*
import org.bukkit.plugin.*
import org.serversmc.*

object Console {
	
	val consoleSender = Bukkit.getConsoleSender()
	private const val p = "[AutoRestart] "
	
	fun info(s: String) = Bukkit.getConsoleSender().sendMessage("${ChatColor.GREEN}$p$s")
	fun warn(s: String) = Bukkit.getConsoleSender().sendMessage("${ChatColor.YELLOW}$p$s")
	fun err(s: String) = Bukkit.getConsoleSender().sendMessage("${ChatColor.RED}$p$s")
	fun consoleSendMessage(s: String) = consoleSender.sendMessage(ChatColor.translate('&', s))
	
	fun catchError(e: Exception, loc: String) {
		err("There was an error in $loc")
		consoleSendMessage(e.toString())
		println(PLUGIN.javaClass.name)
		e.stackTrace.forEach { if (it.toString().startsWith(PLUGIN.javaClass.name)) consoleSendMessage("\t" + it.toString()) }
		err("End of error")
	}
	
}
