package eu.asangarin.tt.api.rewards;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

import eu.asangarin.aria.utils.LineConfig;
import eu.asangarin.tt.api.TechReward;
import eu.asangarin.tt.data.TechEntry;

public class CommandReward implements TechReward {
	private String command;
	private boolean console;

	@Override
	public void reward(HumanEntity p) {
	  // This method is called when the entry is unlocked.
		// Use this to grant the player the 'reward' itself!
		String cmd = command.replace("{player}", p.getName());
		if(console) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
		else Bukkit.dispatchCommand(p, cmd);
	}

	@Override
	public TechReward setup(TechEntry entry, LineConfig c) {
	  // Loading data from the LineConfig!
		command = c.getString("format");
		console = c.getBoolean("console", false);
		// You will almost always want to use "return this;" here
		return this;
	}
}
