package me.illusionz;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class commandStructure implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player ply = (Player) sender;

            if (args.length == 0) {
                long cooldownTime = randomfeed.plugin.checkCooldown(ply);

                if (cooldownTime > -1) {
                    ply.sendMessage("You have to wait " + (cooldownTime / 1000) + " seconds to do this again.");
                    return false;
                }

                randomfeed.plugin.addCooldownToUser(ply, 20000L); // 20s
                Random rand = new Random();
                int range = 32 - 1 + 1;
                int randNum = rand.nextInt(range) + 1;

                ItemStack food = new ItemStack(Material.COOKED_BEEF, randNum);
                World w = ply.getWorld();
                w.dropItem(ply.getLocation(), food);
            }
        }
        return false;
    }
}
