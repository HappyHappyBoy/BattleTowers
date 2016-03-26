package de.teamfci.lagshield;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LagShield implements Listener {
	
	@EventHandler
	public void onShoot(EntityDamageByEntityEvent e) {
		Entity ent = e.getEntity();
		Entity dmg = e.getDamager();
		if(ent instanceof ArmorStand && dmg instanceof Arrow || ent instanceof Minecart && dmg instanceof Arrow) {
			dmg.remove();
			e.setCancelled(true);
		}
	}
	
}
