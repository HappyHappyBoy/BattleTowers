package de.teamfci.paintbrush;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import de.teamfci.paintbrush.forms.Crater;

public class CustomBrush implements Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
//		ItemStack brush = new ItemStack(Material.STICK);
//		if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
//			Player p = e.getPlayer();
//			
//			if(p.getItemInHand().equals(brush)) {
//				Inventory inv = p.getInventory();
//				ItemStack i = inv.getItem(1);
//				paint(p, i);
//			}
//		}
	}
	
	public static void paint(Player player, ItemStack block){
	    Location location = player.getLocation();
	    location.add(0, 1, 0);
	    BlockIterator blocksToAdd = new BlockIterator(location, 1, 100);
	    Location blockToAdd = null;
	    while(blocksToAdd.hasNext()) {
	    	if(blocksToAdd.next().getType() != Material.AIR) {
	    		blockToAdd.getBlock().setType(block.getType());
	    		Crater.build(3, blockToAdd, block);
	    		break;
	    	} else {
		        blockToAdd = blocksToAdd.next().getLocation();
		        player.getWorld().playEffect(blockToAdd, Effect.FLAME, 1);
		        Crater.build(3, blockToAdd, block);
	    	}
	    }
	}
	
}
