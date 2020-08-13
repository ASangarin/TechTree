package eu.asangarin.tt.api.requirements;

import org.bukkit.entity.HumanEntity;

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
	public boolean met(TechEntry e, HumanEntity p) {
		return PlayerData.get(p.getUniqueId()).hasEntry(tree + "." + entry);
	}

	@Override
	public String display(TechEntry e, HumanEntity p, boolean isUnlocked) {	
		StringBuilder format = new StringBuilder(TTPlugin.plugin.findEntry(tree + "." + entry, e).getName());
		if(!tree.equalsIgnoreCase(e.getTechTreeId()))
			format.append(" (" + TechDatabase.get().getTree(tree).getDisplayName() + ")");
		return FormatManager.get().getFormat("entry").getFormat(isUnlocked)
				.replace("{entry}", format.toString());
	}

	@Override
	public void fulfill(TechEntry e, HumanEntity p) {}

	@Override
	public TechRequirement setup(TechEntry e, LineConfig c) {
		tree = c.getString("tree", e.getTechTreeId());
		entry = c.getString("path");
		return this;
	}
}
