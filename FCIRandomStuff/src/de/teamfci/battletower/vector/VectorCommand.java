package de.teamfci.battletower.vector;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VectorCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender; 
			if(args.length == 0) {
				if(p.hasPermission("fci.vectorcommand")) {
					Location loc = RadialVector.getRadialVectorLocation(p.getLocation());
//					loc.getBlock().setType(Material.DIAMOND_BLOCK);
				} else {
					p.sendMessage("§cFehler: Du hast keine Permission dazu!");
				}
			}
		}
		
		return false;
	}
	
}
