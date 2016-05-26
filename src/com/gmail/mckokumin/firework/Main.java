package com.gmail.mckokumin.firework;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor{

	public void onEnable(){
		this.getCommand("firework").setExecutor(this);
	}

	public void onDisable(){

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		switch(cmd.getName()){
		case "firework":
			if (!(sender instanceof Player)){
				sender.sendMessage("ゲーム内から実行してください");
			}
			if (args.length == 0){
				sender.sendMessage("使い方: /firework <flicker> <trail> <type> <color> <fadeColor>");
				sender.sendMessage(ChatColor.YELLOW + "/firework helpで具体的な使い方が見れます");
				return true;
			}
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("help")){
					sender.sendMessage(ChatColor.YELLOW + "flicker ... ちらつくか ... true/false");
					sender.sendMessage(ChatColor.YELLOW + "trail ... 残像を残すか ... true/false");
					sender.sendMessage(ChatColor.YELLOW + "type ... 花火の形 ... BALL, BALL_LARGE"
							+ ", BURST, CREEPER, STAR");
					sender.sendMessage(ChatColor.YELLOW + "color ... 花火の色 ... "
							+ "WHITE, SILVER, GRAY. BLACK, RED, MAROON, YELLOW, OLIVE, LIME, GREEN"
							+ ", AQUA, TEAL, BLUE, NAVY, FUCHSIA, PURPLE, ORANGE");
					sender.sendMessage(ChatColor.YELLOW + "color ... 消えるときの色 ... 上のと同じ");
					return true;
				}
			}
			if (args.length == 5){
				try {
					boolean flicker = Boolean.valueOf(args[0]);
					boolean trail = Boolean.valueOf(args[1]);
					Type type = getFireworkType(args[2]);
					Color color = getColor(args[3]);
					Color fadecolor = getColor(args[4]);

					Player p = (Player)sender;
					Location l = p.getLocation();
					Firework fw = (Firework) p.getWorld().spawn(l, Firework.class);
					FireworkMeta fm = fw.getFireworkMeta();
					fm.addEffect(FireworkEffect.builder()
							.flicker(flicker)
							.trail(trail)
							.with(type)
							.withColor(color)
							.withFade(fadecolor)
							.build());
					fm.setPower(1);
					fw.setFireworkMeta(fm);
					sender.sendMessage(ChatColor.GREEN + "花火を打ち上げました！");
					sender.sendMessage(ChatColor.GREEN + "Flicker: " + flicker);
					sender.sendMessage(ChatColor.GREEN + "Trail: " + trail);
					sender.sendMessage(ChatColor.GREEN + "Type: " + type);
					sender.sendMessage(ChatColor.GREEN + "Color: " + args[3]);
					sender.sendMessage(ChatColor.GREEN + "FadeColor: " + args[4]);
				} catch (IllegalArgumentException e){
				sender.sendMessage(ChatColor.RED + "使い方が間違っています");
				sender.sendMessage(ChatColor.RED + "/firework help で使い方を確認してください");
				}
			}
			break;
		default:
			break;
		}
		return true;
	}

	public static Color getColor(String colorName){
		if (colorName.equalsIgnoreCase("aqua"))
			return Color.AQUA;
		if (colorName.equalsIgnoreCase("black"))
			return Color.BLACK;
		if (colorName.equalsIgnoreCase("blue"))
			return Color.BLUE;
		if (colorName.equalsIgnoreCase("fuchsia"))
			return Color.FUCHSIA;
		if (colorName.equalsIgnoreCase("gray"))
			return Color.GRAY;
		if (colorName.equalsIgnoreCase("green"))
			return Color.GREEN;
		if (colorName.equalsIgnoreCase("lime"))
			return Color.LIME;
		if (colorName.equalsIgnoreCase("maroon"))
			return Color.MAROON;
		if (colorName.equalsIgnoreCase("navy"))
			return Color.NAVY;
		if (colorName.equalsIgnoreCase("olive"))
			return Color.OLIVE;
		if (colorName.equalsIgnoreCase("orange"))
			return Color.ORANGE;
		if (colorName.equalsIgnoreCase("purple"))
			return Color.PURPLE;
		if (colorName.equalsIgnoreCase("red"))
			return Color.RED;
		if (colorName.equalsIgnoreCase("silver"))
			return Color.SILVER;
		if (colorName.equalsIgnoreCase("teal"))
			return Color.TEAL;
		if (colorName.equalsIgnoreCase("white"))
			return Color.WHITE;
		if (colorName.equalsIgnoreCase("yellow"))
			return Color.YELLOW;
		return null;
	}

	public static Type getFireworkType(String typeName){
		if (typeName.equalsIgnoreCase("ball"))
			return Type.BALL;
		if (typeName.equalsIgnoreCase("ball_large"))
			return Type.BALL_LARGE;
		if (typeName.equalsIgnoreCase("burst"))
			return Type.BURST;
		if (typeName.equalsIgnoreCase("creeper"))
			return Type.CREEPER;
		if (typeName.equalsIgnoreCase("star"))
			return Type.STAR;
		return null;
	}

}
