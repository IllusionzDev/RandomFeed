package me.illusionz;

import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public final class randomfeed extends JavaPlugin {
    public static randomfeed plugin;
    private static Map<UUID, Long> cooldowns = Maps.newHashMap();

    @Override
    public void onEnable() {
        plugin = this;
        System.out.println("Plugin Started!");
        getCommand("feed").setExecutor(new commandStructure());
    }

    public long checkCooldown(Player ply) {
        UUID id = ply.getUniqueId();

        if (cooldowns.containsKey(id)) {
            long checkTime = cooldowns.get(id) - System.currentTimeMillis();

            if (checkTime > 0)
                return checkTime;
        }

        return -1L;
    }

    public void addCooldownToUser(Player ply, long time) {
        cooldowns.put(ply.getUniqueId(), System.currentTimeMillis() + time);
    }
}
