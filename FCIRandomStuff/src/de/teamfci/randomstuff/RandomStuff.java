package de.teamfci.randomstuff;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.slikey.effectlib.EffectManager;
import de.teamfci.battletower.ArrowTower;
import de.teamfci.battletower.ArrowTowerProtectShield;
import de.teamfci.battletower.CommandBattleTower;
import de.teamfci.battletower.vector.RadialVector;
import de.teamfci.lagshield.LagShield;
import de.teamfci.paintbrush.CustomBrush;
import de.teamfci.zauberstab.MagicWand;

public class RandomStuff extends JavaPlugin {
	
	public static EffectManager em;
	
	public void onEnable() {
		em = new EffectManager(this);
		ArrowTower.pl = this;
		RadialVector.pl = this;
		ArrowTowerProtectShield.pl = this;
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new MagicWand(), this);
		pm.registerEvents(new CustomBrush(), this);
		pm.registerEvents(new LagShield(), this);
		this.getCommand("btower").setExecutor(new CommandBattleTower());
	}
	
}
