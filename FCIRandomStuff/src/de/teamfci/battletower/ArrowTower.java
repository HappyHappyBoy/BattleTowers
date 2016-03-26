package de.teamfci.battletower;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.slikey.effectlib.effect.AnimatedBallEffect;
import de.slikey.effectlib.effect.SphereEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;
import de.teamfci.randomstuff.RandomStuff;

public class ArrowTower {
	static int j = 0;
	public static HashMap<String, BukkitRunnable> tower = new HashMap<String, BukkitRunnable>();
	public static RandomStuff pl;
	public ArrowTower(RandomStuff pl) {
		this.pl = pl;
	}
	public static boolean multitower = false;
	public static int size = 0;
	public static AnimatedBallEffect effect;
	public static AnimatedBallEffect effect2;
	public static SphereEffect effect3;
	public static ParticleEffect elementalSphere = ParticleEffect.SPELL_MOB;
	static long shootSpeed = getShootSpeed();
	public static void start() {
		startMagicCrystall();
		startFlameSphere();
		final Location l1 = getTowerLocation();
		l1.add(-0.5,-2,-0.5);
		final ArmorStand a = l1.getWorld().spawn(l1, ArmorStand.class);
		a.setGravity(false);
		tower.put("s", new BukkitRunnable() {
			Location l = l1;
			@Override
			public void run() {
				shootSpeed = getShootSpeed();
				float arrowSpeed = getArrowSpeed();
				for(Entity ent : a.getNearbyEntities(20,  20,  20)) {
					if(ent instanceof Monster || ent instanceof Ghast || ent instanceof Slime || ent instanceof Bat || ent instanceof Player || ent instanceof Minecart) {
						if(effect3.particle == ParticleEffect.FLAME) {
							if(ent.isDead()){
								return;
							}
							l = getTowerLocation();
							l.add(-0.5,2,-0.5);
							Location l2 = ent.getLocation();
							l2.add(0, 0.5, 0);
							
							double dX = l.getX() - l2.getX();
							double dY = l.getY() - l2.getY();
							double dZ = l.getZ() - l2.getZ();
							
							double yaw = Math.atan2(dZ, dX);
							double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
							
							double X = Math.sin(pitch) * Math.cos(yaw);
							double Y = Math.sin(pitch) * Math.sin(yaw);
							double Z = Math.cos(pitch);
							Vector vector = new Vector(X, Z, Y);
							Z = Z - 1;
							l.add(vector);
							Arrow a = l.getWorld().spawnArrow(l, vector, (float) arrowSpeed, (float) 0);
							a.setFireTicks(100000);
							a.setCustomName("Flame");
							a.setCustomNameVisible(true);
						}
						if(effect3.particle == ParticleEffect.SPELL_INSTANT) {
							if(ent.isDead()){
								return;
							}
							l = getTowerLocation();
							l.add(-0.5,2,-0.5);
							Location l2 = ent.getLocation();
							l2.add(0, 0.5, 0);
							
							double dX = l.getX() - l2.getX();
							double dY = l.getY() - l2.getY();
							double dZ = l.getZ() - l2.getZ();
							
							double yaw = Math.atan2(dZ, dX);
							double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
							
							double X = Math.sin(pitch) * Math.cos(yaw);
							double Y = Math.sin(pitch) * Math.sin(yaw);
							double Z = Math.cos(pitch);
							Vector vector = new Vector(X, Z, Y);
							Z = Z - 1;
							l.add(vector);
							Arrow a = l.getWorld().spawnArrow(l, vector, (float) arrowSpeed, (float) 0);
							a.setCustomName("Spell");
							a.setCustomNameVisible(true);
						}
						if(effect3.particle == ParticleEffect.SPELL_MOB) {
							if(ent.isDead()){
								return;
							}
							l = getTowerLocation();
							l.add(-0.5,2,-0.5);
							Location l2 = ent.getLocation();
							l2.add(0, 0.5, 0);
							
							double dX = l.getX() - l2.getX();
							double dY = l.getY() - l2.getY();
							double dZ = l.getZ() - l2.getZ();
							
							double yaw = Math.atan2(dZ, dX);
							double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
							
							double X = Math.sin(pitch) * Math.cos(yaw);
							double Y = Math.sin(pitch) * Math.sin(yaw);
							double Z = Math.cos(pitch);
							Vector vector = new Vector(X, Z, Y);
							Z = Z - 1;
							l.add(vector);
							l.setYaw((float) yaw);
							l.setPitch((float) pitch);
//							Item a = l.getWorld().dropItem(l, new ItemStack(Material.BLAZE_POWDER));
//							a.setCustomName("LOL");
//							a.setCustomNameVisible(true);
							FallingBlock a = l.getWorld().spawnFallingBlock(l, 30, (byte) 0);
							a.setVelocity(vector);
						}
						if(multitower == false) {
							break;
						}
					}
				}
				
			}
			
		});
		tower.get("s").runTaskTimer(pl, shootSpeed, shootSpeed);
	}
	
	public static void startMagicCrystall() {
		final Location l = getTowerLocation();
		l.add(-0.5,0,-0.5);
		spawnEffectAnimatedBall(l);
		spawnEffectPortal(l);
		tower.put("p", new BukkitRunnable() {
			@Override
			public void run() {
				spawnEffectAnimatedBall(l);
				spawnEffectPortal(l);
			}
			
		});
		tower.get("p").runTaskTimer(pl, 460, 460);
	}
	
	public static void startFlameSphere() {
		final Location l = getTowerLocation();
		l.add(-0.5,1,-0.5);
		spawnEffectSphere(l);
		tower.put("pa", new BukkitRunnable() {
			@Override
			public void run() {
				Random r = new Random();
				int k = r.nextInt(3);
				if(k == 0) {
					elementalSphere = ParticleEffect.FLAME;
				}
				if(k == 1) {
					elementalSphere = ParticleEffect.SPELL_INSTANT;
				}
				if(k == 2) {
					elementalSphere = ParticleEffect.SPELL_MOB;
				}
				spawnEffectSphere(l);
			}
			
		});
		tower.get("pa").runTaskTimer(pl, 500, 500);
	}
	
	public static void spawnEffectAnimatedBall(Location l) {
		effect = new AnimatedBallEffect(pl.em);
		DynamicLocation loc = new DynamicLocation(l);
		effect.setDynamicOrigin(loc);
		effect.particle = ParticleEffect.SPELL_WITCH;
		effect.start();
	}
	public static void spawnEffectPortal(Location l) {
		effect2 = new AnimatedBallEffect(pl.em);
		DynamicLocation loc = new DynamicLocation(l);
		effect2.setDynamicOrigin(loc);
		effect2.particle = ParticleEffect.PORTAL;
		effect2.start();
	}
	public static void spawnEffectSphere(Location l) {
		effect3 = new SphereEffect(pl.em);
		DynamicLocation loc = new DynamicLocation(l);
		effect3.setDynamicOrigin(loc);
		effect3.particle = elementalSphere;
		effect3.radius = size;
		effect3.color = Color.ORANGE;
		effect3.start();
	}
	public static void deSpawnEffect() {
		effect.cancel();
		effect2.cancel();
		effect3.cancel();
	}
	
	
	public static void stop() {
		tower.get("s").cancel();
		tower.get("p").cancel();
		tower.get("pa").cancel();
		tower.clear();
//		ArrowTowerProtectShield.tower.get("s").cancel();
//		ArrowTowerProtectShield.tower.get("helix").cancel();
//		ArrowTowerProtectShield.tower.clear();
		deSpawnEffect();
	}
	
	public static Location getTowerLocation() {
		File file = new File("plugins//Fortress-Combat-System//BattleTowers//ArrowTower.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		String path = "BattleTower.Tower.Location";
		double x = (double) cfg.getInt(path+".X");
		double y = (double) cfg.getInt(path+".Y");
		double z = (double) cfg.getInt(path+".Z");
		String w = cfg.getString(path+".World");
		World world = Bukkit.getWorld(w);
		Location loc = new Location(world, x, y, z);
		return loc;
	}
	
	public static Float getArrowSpeed() {
		File file = new File("plugins//Fortress-Combat-System//BattleTowers//ArrowTower.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		float speed = (float) cfg.getInt("BattleTower.Tower.Config.Speed");
		return speed;
	}
	
	public static Long getShootSpeed() {
		File file = new File("plugins//Fortress-Combat-System//BattleTowers//ArrowTower.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		long speed = (long) cfg.getInt("BattleTower.Tower.Config.Shoot Speed");
		return speed;
	}
	
}
