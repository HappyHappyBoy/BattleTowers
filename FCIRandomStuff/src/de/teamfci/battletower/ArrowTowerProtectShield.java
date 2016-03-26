package de.teamfci.battletower;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import de.slikey.effectlib.effect.HelixEffect;
import de.slikey.effectlib.effect.WarpEffect;
import de.slikey.effectlib.util.ParticleEffect;
import de.teamfci.battletower.vector.RadialVector;
import de.teamfci.randomstuff.RandomStuff;

public class ArrowTowerProtectShield {
	
	public static HashMap<String, BukkitRunnable> tower = new HashMap<String, BukkitRunnable>();
	public static RandomStuff pl;
	public ArrowTowerProtectShield(RandomStuff pl) {
		this.pl = pl;
	}
	static Location loc;
	public static void start() {
//		Location l = getTowerShieldLocation();
//		l.add(-0.5,0,-0.5);
//		RadialVector.InitFullRadialShieldMarkers(l);
		tower.put("s", new BukkitRunnable() {
			@Override
			public void run() {
				Check();
			}
			
		});
		tower.get("s").runTaskTimer(pl, 20L, 20L);
	}
	
	
	public static void Check() {
		Location loc = getTowerShieldLocation();
		loc.add(-0.5,0,-0.5);
		ArmorStand a = loc.getWorld().spawn(loc, ArmorStand.class);
		Location l1 = RadialVector.getRadialVectorLocation(loc);
		a.remove();
	}
	
	
	public static Location getTowerShieldLocation() {
		File file = new File("plugins//Fortress-Combat-System//BattleTowers//ArrowTower.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		String path = "BattleTower.TowerProtectShield.Location";
		double x = (double) cfg.getInt(path+".X");
		double y = (double) cfg.getInt(path+".Y");
		double z = (double) cfg.getInt(path+".Z");
		String w = cfg.getString(path+".World");
		World world = Bukkit.getWorld(w);
		Location loc = new Location(world, x, y, z);
		return loc;
	}
	
	public static void showTowerShield(Location l) {
		final HelixEffect helix = new HelixEffect(pl.em);
		helix.radius = 6;
		helix.particle = ParticleEffect.FIREWORKS_SPARK;
		final WarpEffect ball = new WarpEffect(pl.em);
		ball.radius = 6;
		ball.particle = ParticleEffect.FIREWORKS_SPARK;
		ball.start();
		helix.start();
		tower.put("helix", new BukkitRunnable() {

			@Override
			public void run() {
				helix.cancel();
				ball.cancel();
			}
			
		});
		tower.get("helix").runTaskLaterAsynchronously(pl, 60L);
	}
}
