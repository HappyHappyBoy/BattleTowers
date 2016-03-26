package de.teamfci.zauberstab;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import de.slikey.effectlib.util.ParticleEffect;
import de.teamfci.battletower.ArrowTower;
import de.teamfci.battletower.ArrowTowerProtectShield;

public class MagicWand implements Listener {
	int i = 0;
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		ItemStack wand = new ItemStack(Material.BLAZE_ROD);
		ItemStack color = new ItemStack(Material.NETHER_STAR);
		Player p = e.getPlayer();
		if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
			if(p.getItemInHand().equals(color)) {
				if(i == 13) {
					i = 0;
					p.sendMessage("resetted");
				}
				if(i == 0) {
					ArrowTower.size = i;
					p.sendMessage("Witch Magic");
				}
				if(i == 1) {
					ArrowTower.size = i;
					p.sendMessage("Smoke Normal");
				}
				if(i == 2) {
					ArrowTower.size = i;
					p.sendMessage("Block Crack");
				}
				if(i == 3) {
					ArrowTower.size = i;
					p.sendMessage("Crit");
				}
				if(i == 4) {
					ArrowTower.size = i;
					p.sendMessage("Magic Crit");
				}
				if(i == 5) {
					ArrowTower.size = i;
					p.sendMessage("Happy Villager");
				}
				if(i == 6) {
					ArrowTower.size = i;
					p.sendMessage("Angry Villager");
				}
				if(i == 7) {
					ArrowTower.size = i;
					p.sendMessage("Cloud");
				}
				if(i == 8) {
					ArrowTower.size = i;
					p.sendMessage("Portal");
				}
				if(i == 9) {
					ArrowTower.size = i;
					p.sendMessage("Firework Spark");
				}
				if(i == 10) {
					ArrowTower.size = i;
					p.sendMessage("Flame");
				}
				if(i == 11) {
					ArrowTower.size = i;
					p.sendMessage("Enchantment Table");
				}
				if(i == 12) {
					ArrowTower.size = i;
					p.sendMessage("Town Aura");
				}
				i = i + 1;
			}
		}
		if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
			if(p.getItemInHand().equals(wand)) {
				if(p.isSneaking()) {
					mageTp(p);
				} else {
					ArrowTower.stop();
				}
			}
		}
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
			if(p.getItemInHand().equals(wand)) {
//				/*
//				 * Grad konvertieren -> Radian
//				 */
//				double pitch = ((p.getLocation().getPitch() + 90) * Math.PI) / 180;
//				double yaw  = ((p.getLocation().getYaw() + 90)  * Math.PI) / 180;
//				
//				
//				/*
//				 * Location für den Vectore errechnen
//				 */
//				double x = Math.sin(pitch) * Math.cos(yaw);
//				double y = Math.sin(pitch) * Math.sin(yaw);
//				double z = Math.cos(pitch);
//				
//				Vector v = new Vector(x, z, y);
//				
//				pitch = ((90 - p.getLocation().getPitch()) * Math.PI) / 180;
//				yaw  = ((p.getLocation().getYaw() + 90 + 180) * Math.PI) / 180;
				if(ArrowTower.tower.isEmpty()) {
					ArrowTower.start();
//					ArrowTowerProtectShield.start();
				}
//				ArrowTowerProtectShield.start();
			}
		}
	}
	
	public static void go(Player player){
	    Location location = player.getEyeLocation();
	    BlockIterator blocksToAdd = new BlockIterator(location, 0, 10);
	    Location blockToAdd;
	    while(blocksToAdd.hasNext()) {
	        blockToAdd = blocksToAdd.next().getLocation();
	        player.getWorld().playEffect(blockToAdd, Effect.MOBSPAWNER_FLAMES, 20);
	    }
	}
	
	public static void magicMonsterRemover(Player player){
	    Location location = player.getLocation();
	    location.add(0, 1, 0);
	    BlockIterator blocksToAdd = new BlockIterator(location, 0, 10);
	    Location blockToAdd = null;
	    while(blocksToAdd.hasNext()) {
	    	if(blocksToAdd.next().getType() != Material.AIR) {
		    	Location loc = blockToAdd;
		    	loc.setYaw(player.getLocation().getYaw());
		    	loc.setPitch(player.getLocation().getPitch());
	    		player.sendMessage("§cEs sind Blöcke im Weg!");
	    		checkMonster(blockToAdd, player);
	    		break;
	    	} else {
		        blockToAdd = blocksToAdd.next().getLocation();
		        player.getWorld().playEffect(blockToAdd, Effect.MOBSPAWNER_FLAMES, 1);
		        checkMonster(blockToAdd, player);
	    	}
	    }
	    if(!blocksToAdd.hasNext()) {
	    	Location loc = blockToAdd;
	    	loc.setYaw(player.getLocation().getYaw());
	    	loc.setPitch(player.getLocation().getPitch());
	    	checkMonster(blockToAdd, player);
	    	player.teleport(loc);
	    }
	}
	
	public static void mageTp(Player player){
	    Location location = player.getLocation();
	    location.add(0, 1, 0);
	    BlockIterator blocksToAdd = new BlockIterator(location, 0, 50);
	    Location blockToAdd = null;
	    boolean bl = false;
	    while(blocksToAdd.hasNext()) {
	    	if(blocksToAdd.next().getType() != Material.AIR) {
		    	Location loc = blockToAdd;
		    	loc.setYaw(player.getLocation().getYaw());
		    	loc.setPitch(player.getLocation().getPitch());
	    		player.sendMessage("§cEs sind Blöcke im Weg!");
	    		loc.getBlock().setType(Material.DIAMOND_BLOCK);
	    		bl = true;
	    		break;
	    	} else {
		        blockToAdd = blocksToAdd.next().getLocation();
		        player.getWorld().playEffect(blockToAdd, Effect.SPELL, 1);
		        burnMobs(blockToAdd, player);
	    	}
	    }
	    if(!blocksToAdd.hasNext() && bl == false) {
	    	Location loc = blockToAdd;
	    	loc.setYaw(player.getLocation().getYaw());
	    	loc.setPitch(player.getLocation().getPitch());
	    	player.teleport(loc);
	    }
	}
	
	public static void checkMonster(Location loc, Player p) {
		ArmorStand armor = loc.getWorld().spawn(loc, ArmorStand.class);
		armor.setGravity(false);
		armor.setVisible(true);
		armor.setCustomNameVisible(true);
		armor.setMaxHealth(100000.0);
		armor.setHealth(armor.getMaxHealth());
		for(Entity ent : armor.getNearbyEntities(1, 1, 1)) {
			if(ent instanceof Monster) {
//				ent.teleport(p.getLocation());
				ent.remove();
				if(ent instanceof Monster) {
					ent.setCustomName("Hitted by §cMagicWand §f§l| §b"+p.getName());
					for(Entity e : ent.getNearbyEntities(1, 1, 1)) {
						e.setCustomName("Hitted by §cMagicWand §f§l| §b"+p.getName());
//						ent.teleport(p.getLocation());;
						ent.remove();
					}
				}
			}
		}
		armor.remove();
	}
	
	public static void burnMobs(Location loc, Player p) {
		for(Entity ent : p.getNearbyEntities(1, 1, 1)) {
			if(ent instanceof Monster) {
				ent.setFireTicks(1000);
			}
		}
	}
	
}
