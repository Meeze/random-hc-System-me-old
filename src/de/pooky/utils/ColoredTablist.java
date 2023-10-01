package de.pooky.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;


public class ColoredTablist implements Listener{

	static Scoreboard sb;
	
	public static void registerTeams() {
		sb = Bukkit.getScoreboardManager().getNewScoreboard();
		
		sb.registerNewTeam("00000Owner");
		sb.registerNewTeam("00001Admin");
		sb.registerNewTeam("00002Developer");
		sb.registerNewTeam("00003Builder");
		sb.registerNewTeam("00004Moderator");
		sb.registerNewTeam("00005Supporter");
		sb.registerNewTeam("00006Youtuber");
		sb.registerNewTeam("00007Premium");
		sb.registerNewTeam("00008Member");
		
		sb.getTeam("00000Owner").setPrefix("§c§lTeam §8|§4 ");
		sb.getTeam("00001Admin").setPrefix("§c§lTeam §8|§c ");
		sb.getTeam("00002Developer").setPrefix("§c§lTeam §8|§3 ");
		sb.getTeam("00003Builder").setPrefix("§c§lTeam §8|§b ");
		sb.getTeam("00004Moderator").setPrefix("§c§lTeam §8|§5 ");
		sb.getTeam("00005Supporter").setPrefix("§c§lTeam §8|§2 ");
		sb.getTeam("00006Youtuber").setPrefix("§d");
		sb.getTeam("00007Premium").setPrefix("§6");
		sb.getTeam("00008Member").setPrefix("§7");
	}
	
	public static void setPrefix(Player p) {
		String team ="";
		
		if(p.hasPermission("owner.tablist")) {
			team = "00000Owner";
		}else if(p.hasPermission("admin.tablist")) {
				team = "00001Gamemaster";
		}else if(p.hasPermission("developer.tablist")) {
			team = "00002Developer";
		}else if(p.hasPermission("builder.tablist")) {
			team = "00003Builder";
		}else if(p.hasPermission(".moderator.tablist")) {
			team = "00004Moderator";
		}else if(p.hasPermission("supporter.tablist")) {
			team = "00005Supporter";
		}else if(p.hasPermission("youtuber.tablist")) {
			team = "00006Youtuber";
		}else if(p.hasPermission("premium.tablist")) {
			team = "00007Premium";
		}else {
			team = "00008Member";
		}
		sb.getTeam(team).addPlayer(p);
		p.setDisplayName(sb.getTeam(team).getPrefix() + p.getName());
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.setScoreboard(sb);
		}
	}
	
	public static void setHeaderFooter(Player p, String header, String footer) {
		if(header == null) header = " ";
		if(footer == null) footer = " ";
		
		IChatBaseComponent tabHeader = ChatSerializer.a("{\"text\":\"" + header + "\"}");
		IChatBaseComponent tabFooter = ChatSerializer.a("{\"text\":\"" + footer + "\"}");
		
		PacketPlayOutPlayerListHeaderFooter tabPacket = new PacketPlayOutPlayerListHeaderFooter();
		
		try {
			Field headerField = tabPacket.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(tabPacket, tabHeader);
			
			Field footerField = tabPacket.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(tabPacket, tabFooter);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(tabPacket);
		}
	}
	
	public static void Heatlhbar() {
		Objective o = sb.registerNewObjective("health", "health");
		o.setDisplayName(ChatColor.RED + "❤");
		o.setDisplaySlot(DisplaySlot.BELOW_NAME);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		
		Objective o = sb.registerNewObjective("abcd", "abcd");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("§6§lBadlion.de");
		o.getScore(" ").setScore(3);
		o.getScore("§2Name : " + p.getName() + " §a§l").setScore(2);
		o.getScore("§eCoins : " + "COINS BLABLA").setScore(1);
		o.getScore(" ").setScore(0);
		p.setScoreboard(sb);
		
	}
	
}
