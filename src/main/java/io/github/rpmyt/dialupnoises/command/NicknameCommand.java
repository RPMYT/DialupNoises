package io.github.rpmyt.dialupnoises.command;

import io.github.rpmyt.dialupnoises.util.PlayerStringStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NicknameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("dialupnoises.commands.nick")) {
                if (args.length >= 1) {
                    PlayerStringStorage.set(player, args[0], PlayerStringStorage.KEY_NICKNAME);
                    player.sendMessage(ChatColor.GREEN + "Set your nickname to '" + ChatColor.RESET +  args[0] + ChatColor.GREEN + "'!");

                    player.displayName(Component.text(args[0]));
                    player.playerListName(Component.text(args[0] + " (" + ((TextComponent) player.customName()).content() + ")"));
                } else {
                    player.sendMessage("Reset your nickname!");
                    PlayerStringStorage.set(player, "", PlayerStringStorage.KEY_NICKNAME);
                }
            }
        }

        return false;
    }
}
