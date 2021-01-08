package tech.miraidev.EnderCrystal.commands;

import net.minecraft.server.v1_12_R1.EntityEnderCrystal;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import tech.miraidev.EnderCrystal.Main;

public class Ender implements CommandExecutor {
    int x;
    int y;
    int z;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length < 1){
            commandSender.sendMessage("§c引数が足りません! /ender [create/remove] name x y z");
            return true;
        }
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("§cプレイヤーのみ実行できます");
            return true;
        }
        if(args[0].equalsIgnoreCase("create")) {
            if(args.length < 5){
                commandSender.sendMessage("§c引数が足りません! /ender create name x y z");
                return true;
            }
            if(Main.crystialList.containsKey(args[1])){
                commandSender.sendMessage("§cその名前のクリスタルはもう存在します。");
                return true;
            }
            Player p = (Player) commandSender;
            Location loc = p.getLocation();
            World world = p.getWorld();
            Entity e = world.spawnEntity(loc, EntityType.ENDER_CRYSTAL);
            EnderCrystal ec = (EnderCrystal) e;
            try {
                x = Integer.parseInt(args[2]);
                y = Integer.parseInt(args[3]);
                z = Integer.parseInt(args[4]);
            } catch (Exception ex) {
                commandSender.sendMessage("§c座標は数値で入力してください。");
                return true;
            }
            Location target = new Location(world, x, y, z);
            ec.setBeamTarget(target);
            ec.setShowingBottom(false);
            Main.crystialList.put(args[1], e);
            commandSender.sendMessage("§aクリスタルを作成しました。");
        }
        if(args[0].equalsIgnoreCase("remove")) {
            if(args.length < 2){
                commandSender.sendMessage("§c引数が足りません! /ender remove name");
                return true;
            }
            if(!(Main.crystialList.containsKey(args[1]))){
                commandSender.sendMessage("§cその名前のクリスタルは存在しません。");
                return true;
            }
            Main.crystialList.get(args[1]).remove();
            commandSender.sendMessage("§aクリスタルを削除しました。");
        }
        if(args[0].equalsIgnoreCase("list")) {
            commandSender.sendMessage("§a- クリスタル一覧 -");
            for(String key : Main.crystialList.keySet()){
                commandSender.sendMessage("§a- §e"+key);
            }
        }
        return true;
    }
}
