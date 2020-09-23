package eu.asangarin.tt.api.requirements;

import org.bukkit.entity.Player;

import eu.asangarin.aria.utils.LineConfig;
import eu.asangarin.tt.TTPlugin;
import eu.asangarin.tt.TechDatabase;
import eu.asangarin.tt.api.FormatManager;
import eu.asangarin.tt.api.TechRequirement;
import eu.asangarin.tt.data.TechEntry;
import eu.asangarin.tt.player.PlayerData;

public class EntryRequirement implements TechRequirement {
	private String tree;
	private String entry;
	
	@Override
	public boolean met(TechEntry e, Player p) {
		// Checks if the player has unlocked the specific entry.
		// If yes, this requirement is met!
		return PlayerData.get(p).hasEntry(tree + "." + entry);
	}

	@Override
	public String display(TechEntry e, Player p, boolean isUnlocked) {	
		// Uses the format manager to properly display this requirement.
		// The string returned is the String that will replace the placeholder in the lore.
		StringBuilder format = new StringBuilder(TTPlugin.plugin.findEntry(tree + "." + entry, e).getName());
		if(!tree.equalsIgnoreCase(e.getTechTreeId()))
			format.append(" (" + TechDatabase.get().getTree(tree).getDisplayName() + ")");
		return FormatManager.get().getFormat("entry").getFormat(isUnlocked)
				.replace("{entry}", format.toString());
	}

	@Override
	public void fulfill(TechEntry e, Player p) {
		// This method gets called when the player
		// Unlocks an entry with this requirement.
		// Generally used to take required items / money,
		// etc. from the player, hence the name 'fulfill'
	}

	@Override
	public boolean setup(TechEntry e, LineConfig c) {
		// Loads data from the LineConfig
		tree = c.getString("tree", e.getTechTreeId());
		entry = c.getString("path");
		// If you 'return false;' the reward
		// will not be added to the tech entry
		return true;
	}
}
