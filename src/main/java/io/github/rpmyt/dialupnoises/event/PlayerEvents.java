package io.github.rpmyt.dialupnoises.event;

import io.github.rpmyt.dialupnoises.util.PlayerStringStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("ConstantConditions")
public class PlayerEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (true) { // TODO: make join/leave messages toggleable
            Player player = event.getPlayer();
            event.joinMessage(Component.text(ChatColor.GREEN + "+ " + ChatColor.GRAY +
                    PlayerStringStorage.get(player, PlayerStringStorage.KEY_NICKNAME, ((TextComponent) player.customName()).content())));

            player.customName(player.displayName());
            player.setCustomNameVisible(false);

            player.displayName(Component.text(PlayerStringStorage.get(player, PlayerStringStorage.KEY_NICKNAME, "")));
            player.playerListName(Component.text(
                    "[" + PlayerStringStorage.get(player, PlayerStringStorage.KEY_TITLE, ((TextComponent) player.customName()).content()) + "] " +
                            PlayerStringStorage.get(player, PlayerStringStorage.KEY_NICKNAME, ((TextComponent) player.customName()).content()) +
                            " (" + ((TextComponent) player.customName()).content() + ")")
            );
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (true) { // TODO: make join/leave messages toggleable
            Player player = event.getPlayer();
            event.quitMessage(Component.text(ChatColor.RED + "- " + ChatColor.GRAY +
                    PlayerStringStorage.get(player, PlayerStringStorage.KEY_NICKNAME, ((TextComponent) player.customName()).content())));
        }
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onChat(AsyncPlayerChatEvent event) {
        if (true) { // TODO: allow configuration and toggling of title system
            Player player = event.getPlayer();

            event.setFormat(
                    ChatColor.GRAY + "%1$s the " +
                            PlayerStringStorage.get(player, PlayerStringStorage.KEY_TITLE, ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + "Mysterious") +
                            ChatColor.RESET + ":" + " %2$s"
            );
        }
    }
}
