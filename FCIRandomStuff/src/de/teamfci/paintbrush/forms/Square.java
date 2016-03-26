package de.teamfci.paintbrush.forms;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Square {
	
	public static void build(int size, Location loc, ItemStack block) {
		for(int i = 1; i < size; i++) {
			loc.getBlock().setType(block.getType());
			loc.add(i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(0,0,-i);
			loc.getBlock().setType(block.getType());
			loc.add(-i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(-i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(0,0,i);
			loc.getBlock().setType(block.getType());
			loc.add(0,0,i);
			loc.getBlock().setType(block.getType());
			loc.add(i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(i,0,0);
			loc.getBlock().setType(block.getType());
			loc.add(-i,i,-i);
			loc.getBlock().setType(Material.GOLD_BLOCK);

//			
//			loc.getBlock().setType(block.getType());
//			loc.add(i,0,0);
//			loc.getBlock().setType(block.getType());
//			loc.add(0,0,-i);
//			loc.getBlock().setType(block.getType());
//			loc.add(-i,0,0);
//			loc.getBlock().setType(block.getType());
//			loc.add(-i,0,0);
//			loc.getBlock().setType(block.getType());
//			loc.add(0,0,i);
//			loc.getBlock().setType(block.getType());
//			loc.add(0,0,i);
//			loc.getBlock().setType(block.getType());
//			loc.add(i,0,0);
//			loc.getBlock().setType(block.getType());
//			loc.add(i,0,0);
//			loc.getBlock().setType(block.getType());
//			loc.add(i,0,0);
//			loc.getBlock().setType(block.getType());
		}
	}
	
}
