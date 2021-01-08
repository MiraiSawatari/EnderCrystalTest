package tech.miraidev.EnderCrystal;

import net.minecraft.server.v1_12_R1.EntityEnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import tech.miraidev.EnderCrystal.commands.Ender;

import java.util.HashMap;

public class Main extends JavaPlugin {
    public static HashMap<String, Entity> crystialList= new HashMap();
    @Override
    public void onEnable(){
        getCommand("ender").setExecutor(new Ender());
    }
}
