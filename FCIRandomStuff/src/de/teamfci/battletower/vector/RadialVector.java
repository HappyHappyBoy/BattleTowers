package de.teamfci.battletower.vector;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import de.teamfci.battletower.ArrowTowerProtectShield;
import de.teamfci.randomstuff.RandomStuff;
public class RadialVector {
	public static HashMap<String, BukkitRunnable> tower = new HashMap<String, BukkitRunnable>();
	public static RandomStuff pl;
	public RadialVector(RandomStuff pl) {
		this.pl = pl;
	}
	static int o = 0;
	
	public static void InitFullRadialShieldMarkers(Location l) {
		for(int i = 0; i < 360; i++) {
			float yaw = i;
			
			l.setYaw(yaw);
			l.setPitch(0);
			registerForceField(l);
		}
	}
	public static Location getRadialVectorLocation(final Location l) {
		for(int i = 0; i < 360; i++) {
			float yaw = i;
			
			l.setYaw(yaw);
			l.setPitch(0);
			forceField(l);
		}
//		tower.put("radial", new BukkitRunnable() {
//
//			@Override
//			public void run() {
//				o++;
//				if(o == 360) {
//					l.setPitch(0);
//					ArmorStand a = l.getWorld().spawn(l, ArmorStand.class);
//					float yaw = o;
//					
//					l.setYaw(yaw);
//					l.setPitch(0);
//					yaw = yaw + 1;
//					a.teleport(l);
//					forceField(l);
//					
//					a.setCustomName("test");
//					a.setCustomNameVisible(true);
//					a.remove();
//					o = 0;
//				} else {
//					l.setPitch(0);
//					ArmorStand a = l.getWorld().spawn(l, ArmorStand.class);
//					float yaw = o;
//					
//					l.setYaw(yaw);
//					l.setPitch(0);
//					yaw = yaw + 1;
//					a.teleport(l);
//					forceField(l);
//					
//					a.setCustomName("test");
//					a.setCustomNameVisible(true);
//					a.remove();
//				}
//			}
//			
//		});
//		tower.get("radial").runTaskTimer(pl, 0, 0);
		return l;
	}
	
	public static void forceField(Location l) {
		Location blockToAdd;
		BlockIterator blocksToAdd = new BlockIterator(l, 0, 8);
		int a = 0;
		while(blocksToAdd.hasNext()) {
			a = a + 1;
			blockToAdd = blocksToAdd.next().getLocation();
			if(a == 8) {
				blockToAdd.add(-0.5,-2,-0.5);
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.getWorld().playEffect(blockToAdd, Effect.MAGIC_CRIT, 20);
				}
				
				ArmorStand ar = blockToAdd.getWorld().spawn(blockToAdd, ArmorStand.class);
				ar.setCustomName("§c---");
				ar.setCustomNameVisible(true);
				ar.remove();
				for(Entity ent : ar.getNearbyEntities(2, 4, 2)) {
					ent.setVelocity(l.getDirection().multiply(1D).setY(1D));
					ArrowTowerProtectShield.showTowerShield(blockToAdd);
					blockToAdd.add(0,-1,0).getBlock().setType(Material.LAPIS_BLOCK);
				}
			}
		}
	}
	
	public static void registerForceField(Location l) {
		Location blockToAdd;
		BlockIterator blocksToAdd = new BlockIterator(l, 0, 8);
		int a = 0;
		while(blocksToAdd.hasNext()) {
			a = a + 1;
			blockToAdd = blocksToAdd.next().getLocation();
			if(a == 8) {
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.getWorld().playEffect(l, Effect.COLOURED_DUST, 20);
				}
				File file = new File("plugins//Fortress-Combat-System//BattleTowers//ArrowTowerProtectShield.yml");
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				int line = cfg.getInt("ShieldMarkers");
				line++;
				cfg.set("ShieldMarkers", line);
				cfg.set("ShieldMarker."+line+".Location.World", blockToAdd.getWorld().getName());
				cfg.set("ShieldMarker."+line+".Location.X", blockToAdd.getX());
				cfg.set("ShieldMarker."+line+".Location.Y", blockToAdd.getY());
				cfg.set("ShieldMarker."+line+".Location.Z", blockToAdd.getZ());
				cfg.set("ShieldMarker."+line+".Location.Yaw", blockToAdd.getYaw());
				cfg.set("ShieldMarker."+line+".Location.Pitch", blockToAdd.getPitch());
				
				try {
					cfg.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
