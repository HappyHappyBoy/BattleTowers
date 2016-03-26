package de.teamfci.battletower;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandBattleTower implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				if(p.hasPermission("fci.battletowers.battletowers")) {
					p.sendMessage("§a/btower set arrow");
					p.sendMessage("§a/btower set arrowprotect");
				} else {
					p.sendMessage("§cFehler: Du hast nicht die Permission dazu!");
				}
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("set")) {
					if(args[1].equalsIgnoreCase("arrow")) {
						if(p.hasPermission("fci.battletowers.set.arrow")) {
							File file = new File("plugins//Fortress-Combat-System//BattleTowers//ArrowTower.yml");
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							Location loc = p.getLocation();
							cfg.set("BattleTower.Tower.Location.X", loc.getX());
							cfg.set("BattleTower.Tower.Location.Y", loc.getY());
							cfg.set("BattleTower.Tower.Location.Z", loc.getZ());
							cfg.set("BattleTower.Tower.Location.World", loc.getWorld().getName());
							long l = 10;
							float f = 3;
							cfg.set("BattleTower.Tower.Config.Speed", f);
							cfg.set("BattleTower.Tower.Config.Shoot Speed", l);
							try {
								cfg.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							p.sendMessage("§cBattleTower§f:§aArrow §ewurde gesetzt!");
						} else {
							p.sendMessage("§cFehler: Du hast nicht die Permission dazu!");
						}
					}
					if(args[1].equalsIgnoreCase("arrowprotect")) {
						if(p.hasPermission("fci.battletowers.set.arrowprotect")) {
							File file = new File("plugins//Fortress-Combat-System//BattleTowers//ArrowTower.yml");
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							Location loc = p.getLocation();
							cfg.set("BattleTower.TowerProtectShield.Location.X", loc.getX());
							cfg.set("BattleTower.TowerProtectShield.Location.Y", loc.getY());
							cfg.set("BattleTower.TowerProtectShield.Location.Z", loc.getZ());
							cfg.set("BattleTower.TowerProtectShield.Location.World", loc.getWorld().getName());
							try {
								cfg.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							p.sendMessage("§cBattleTower§f:§aArrow §ewurde gesetzt!");
						} else {
							p.sendMessage("§cFehler: Du hast nicht die Permission dazu!");
						}
					}
				}
			}
		}
		
		return false;
	}
	
}
