package org.serversmc.utils

import net.md_5.bungee.api.ChatColor

object ChatColor {
	
	fun translate(c: Char, s: String): String = ChatColor.translateAlternateColorCodes(c, s)
	
	var BLACK = ChatColor.BLACK.toString()
	var DARK_BLUE = ChatColor.DARK_BLUE.toString()
	var DARK_GREEN = ChatColor.DARK_GREEN.toString()
	var DARK_AQUA = ChatColor.DARK_AQUA.toString()
	var DARK_RED = ChatColor.DARK_RED.toString()
	var DARK_PURPLE = ChatColor.DARK_PURPLE.toString()
	var GOLD = ChatColor.GOLD.toString()
	var GRAY = ChatColor.GRAY.toString()
	var DARK_GRAY = ChatColor.DARK_BLUE.toString()
	var BLUE = ChatColor.BLUE.toString()
	var GREEN = ChatColor.GREEN.toString()
	var AQUA = ChatColor.AQUA.toString()
	var RED = ChatColor.RED.toString()
	var LIGHT_PURPLE = ChatColor.LIGHT_PURPLE.toString()
	var YELLOW = ChatColor.YELLOW.toString()
	var WHITE = ChatColor.WHITE.toString()
	var MAGIC = ChatColor.MAGIC.toString()
	var BOLD = ChatColor.BOLD.toString()
	var STRIKETHROUGH = ChatColor.STRIKETHROUGH.toString()
	var UNDERLINE = ChatColor.UNDERLINE.toString()
	var ITALIC = ChatColor.ITALIC.toString()
	var RESET = ChatColor.RESET.toString()
	
}
