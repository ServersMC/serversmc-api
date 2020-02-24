package org.serversmc.interfaces

import org.bukkit.*
import org.bukkit.command.*
import org.bukkit.entity.*
import org.bukkit.event.*
import org.bukkit.permissions.*
import org.serversmc.*
import org.serversmc.utils.*
import org.serversmc.utils.ChatColor.RED
import org.serversmc.utils.Console.catchError

@Suppress("warnings")
interface ICommand : CommandExecutor, TabCompleter, Listener {
	
	val subCommands: ArrayList<ICommand>
		get() = SubCmdManager.getSubCommands(this)
	
	fun register() {
		// Register Permission
		Bukkit.getPluginManager().permissions.add(getPermission())
		// Register Listener
		if (hasListener()) Bukkit.getPluginManager().registerEvents(this, PLUGIN)
		// Check if this is a sub command
		if (getSubCmd() == null) {
			PLUGIN.getCommand(getLabel())?.apply {
				setExecutor(this@ICommand)
				tabCompleter = this@ICommand
				usage = this@ICommand.getUsage()
				description = this@ICommand.getDescription()
			}
		}
		else {
			SubCmdManager.addCommand(getSubCmd()!!, this)
		}
	}
	
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender.hasPermission(getPermission())) {
			try {
				execute(sender, args.toMutableList())
			} catch (e: Exception) {
				catchError(e, "ICommand.onCommand(CommandSender, Command, String, Array<out String>): Boolean")
			}
		}
		else {
			sender.sendMessage("${RED}You don't have permission to use this command!")
		}
		return true
	}
	
	override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
		if (sender is Player) return try {
			tabComplete(sender, args.toMutableList())
		} catch (e: Exception) {
			catchError(e, "ICommand.onTabComplete()")
			null
		}
		return null
	}
	
	fun getPermission() = Permission(getPermString(), getPermDefault())
	
	fun execute(sender: CommandSender, args: MutableList<out String>)
	fun tabComplete(player: Player, args: MutableList<out String>): MutableList<String>?
	fun getLabel(): String
	fun getPermString(): String
	fun getPermDefault(): PermissionDefault
	fun getUsage(): String
	fun getDescription(): String
	fun hasListener(): Boolean
	fun getSubCmd(): ICommand?
	
}
