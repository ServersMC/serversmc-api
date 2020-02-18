package org.serversmc.utils

import org.bukkit.ChatColor
import org.bukkit.configuration.file.*
import org.serversmc.*
import java.io.*
import java.util.*
import kotlin.collections.ArrayList

/**
 *  Add config files by overriding initializeConfigList()
 *  Leave super call method at the end
 *
 */
interface ConfigAPI {
	
	class ConfigFile(private val fileName: String) {
		val file = File(PLUGIN.dataFolder, "$fileName.yml")
		lateinit var yamlConfiguration: YamlConfiguration
		var version: Int = 0
	}
	
	private val configList get() = ArrayList<ConfigFile>()
	val globalConfig get() = YamlConfiguration()
	
	fun getString(path: String): String = ChatColor.translateAlternateColorCodes('&', globalConfig.getString(path).toString())
	fun getInt(path: String): Int = globalConfig.getInt(path)
	fun getDouble(path: String): Double = globalConfig.getDouble(path)
	fun getBoolean(path: String): Boolean = globalConfig.getBoolean(path)
	fun getIntegerList(path: String): MutableList<Int> = globalConfig.getIntegerList(path)
	fun getStringList(path: String): MutableList<String> = globalConfig.getStringList(path)
	
	fun addFile(fileName: String) {
		configList.add(ConfigFile(fileName))
	}
	
	fun initializeConfigList()
	
	private fun combineSubConfigs() {
		// Combine config to one Main config
		configList.forEach { config ->
			val yaml = config.yamlConfiguration
			for (key in yaml.getKeys(true)) globalConfig.set(key, yaml.get(key))
		}
		// Remove version node
		globalConfig.addDefault("version", null)
	}
	
	fun reloadConfig() {
		initializeConfigList()
		combineSubConfigs()
	}
	
	fun init() {
		initializeConfigList()
		configList.forEach { subConfig ->
			// Initialize attributes
			val file = subConfig.file
			val yaml = subConfig.yamlConfiguration
			// Save configs if needed
			if (!file.exists()) {
				PLUGIN.saveResource(file.name, false)
				Console.info("Created ${file.name} config file!")
			}
			// Get config defaults
			yaml.load(file)
			val inputStream = PLUGIN.getResource(file.name) as InputStream
			val defaultConfig = YamlConfiguration.loadConfiguration(InputStreamReader(inputStream))
			globalConfig.addDefaults(defaultConfig)
			subConfig.version = yaml.getInt("version")
			/**
			 * yaml.getInt()            local version
			 * globalConfig.getInt()    default version
			 */
			// Update configs if needed
			if (yaml.getInt("version") != globalConfig.getInt("version")) {
				// Prompt console about update
				Console.info("The config file ${file.name} has changed since the last update!")
				// Create rename file
				val cal = Calendar.getInstance()
				val time = cal.time.toString().replace(":", "_")
				val rename = File(PLUGIN.dataFolder, "($time) ${file.name}")
				// Check if already exists (should never happen)
				if (rename.exists()) rename.delete()
				// Update config file
				file.renameTo(rename)
				PLUGIN.saveResource(file.name, false)
				// Reload updated config
				yaml.load(file)
				subConfig.version = yaml.getInt("version")
				// Prompt update message
				Console.warn("Config file has been backed up to ${rename.name}!")
			}
		}
		combineSubConfigs()
	}
	
}