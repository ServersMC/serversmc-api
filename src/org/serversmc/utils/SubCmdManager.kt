package org.serversmc.utils

import org.serversmc.interfaces.*

@Suppress("warnings")
object SubCmdManager {
	
	private val subCmds = HashMap<ICommand, ArrayList<ICommand>>()
	
	fun getSubCommands(cmd: ICommand) = subCmds[cmd]?: ArrayList<ICommand>()
	
	fun addCommand(parent: ICommand, child: ICommand) {
		subCmds[parent]?.apply {
			add(child)
		} ?: subCmds.set(parent, ArrayList<ICommand>().apply {
			add(child)
		})
	}
	
}