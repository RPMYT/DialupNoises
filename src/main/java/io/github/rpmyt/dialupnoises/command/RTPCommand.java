package io.github.rpmyt.dialupnoises.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RTPCommand implements CommandExecutor {
    private Location getDestination(Location location, int min, int max) {
        Location original = new Location(location.getWorld(), location.x(), location.y(), location.z());
        Location destination = new Location(location.getWorld(), 0, 0, 0);

        //get a random normal vector and multiply by magnitude
        Random r = new Random();
        int magnitude = r.nextInt(min, max);
        double x = r.nextDouble() - 0.5;
        double z = r.nextDouble() - 0.5;
        double normed_x = x / Math.sqrt(x * x + z * z);
        double normed_z = z / Math.sqrt(x * x + z * z);
        destination.setX(normed_x * magnitude);
        destination.setZ(normed_z * magnitude);

        //scan down from 320 to find first non-air block
        World world = original.getWorld();
        int y = 320;

        //load the chunk
        Chunk chunk = world.getChunkAt((int) (normed_x / 16.0), (int) (normed_z / 16.0));
        chunk.load();
        
        while(world.getBlockAt((int) (normed_x * magnitude), y, (int) (normed_z * magnitude)).isEmpty()) {
            Bukkit.broadcastMessage("attempting to teleport to (" + normed_x * magnitude + ", " + y + ", " + normed_z * magnitude + ")");
            y--;
            if(y < -64) {
                chunk.unload();
                return original;
            }
        }

        destination.setY(y + 1);
        return destination;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("dialupnoises.commands.rtp")) {
                Location location = player.getLocation();
                Location there = this.getDestination(location, 100, 500);

                if (there == player.getLocation()) {
                    for (int tries = 0; tries < 3; tries++) {
                        there = this.getDestination(location, 100, 500);
                        if (there != player.getLocation()) {
                            player.teleport(there);
                            player.sendMessage(ChatColor.GREEN + "Success!");
                            tries = 3;
                            return true;
                        }
                    }

                    if (player.getLocation() != there) {
                        player.sendMessage(ChatColor.RED + "Unable to find a suitable location!");
                        return false;
                    }
                } else {
                    player.teleport(there);
                    player.sendMessage(ChatColor.GREEN + "Success!");
                    return true;
                }
            }
        }

        return false;
    }
}