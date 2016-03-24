package com.glacialrush.plugin;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;

public class EmeraldTweaks extends JavaPlugin implements Listener
{
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent e)
	{
		Material itemType = e.getRecipe().getResult().getType();
		Boolean b = false;
		
		if(itemType == Material.EMERALD_BLOCK)
		{
			for(ItemStack i : e.getInventory().getMatrix())
			{
				if(i == null)
				{
					continue;
				}
				
				if(i.hasItemMeta())
				{
					if(ChatColor.stripColor(i.getItemMeta().getDisplayName()).equals("Effect Gem"))
					{
						b = true;
						break;
					}
				}
			}
			
			if(b)
			{
				e.getInventory().setResult(new ItemStack(Material.AIR));
				
				for(HumanEntity he : e.getViewers())
				{
					if(he instanceof Player)
					{
						((Player) he).sendMessage(ChatColor.RED + "You cannot use Effect Gems!");
					}
				}
				
				e.setCancelled(true);
			}
		}
	}
}
