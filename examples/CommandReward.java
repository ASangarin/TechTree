package eu.asangarin.tt.api.rewards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import eu.asangarin.aria.utils.LineConfig;
import eu.asangarin.tt.api.TechReward;
import eu.asangarin.tt.data.TechEntry;

public class CommandReward implements TechReward {
	private String command;
	private boolean console;

	@Override
	public void reward(Player p) {
		// This method is called when the entry is unlocked.
		// Use this to grant the player the 'reward' itself!
		String cmd = command.replace("{player}", p.getName());
		if(console) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
		else Bukkit.dispatchCommand(p, cmd);
	}

	@Override
	public boolean setup(TechEntry entry, LineConfig c) {
		// Loading data from the LineConfig!
		if(!c.contains("format")) return false;
		command = c.getString("format");
		console = c.getBoolean("console", false);
		// If you 'return false;' the reward
		// will not be added to the tech entry
		return true;
	}
}
