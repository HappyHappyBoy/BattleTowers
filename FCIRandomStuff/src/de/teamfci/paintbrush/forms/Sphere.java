package de.teamfci.paintbrush.forms;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Sphere {
	
	public static void build(int size, Location loc, ItemStack block) {
		for(int i = 0; i < size; i++) {
			loc.getBlock().setType(block.getType());
			loc.add(0,i,0);
			loc.getBlock().setType(block.getType());
			loc.add(0,-i,0);
			loc.add(0,0,i);
			loc.getBlock().setType(block.getType());
			loc.add(0,0,-i);
			loc.add(0,-i,0);
			loc.getBlock().setType(block.getType());
			loc.add(0,i,0);
			loc.add(i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(-i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(-i,0,0);
			loc.add(-i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(i,0,0);
			loc.add(0,0,-i);
			loc.getBlock().setType(block.getType());
			loc.add(0, 0, i);
		}
	}
	
}
