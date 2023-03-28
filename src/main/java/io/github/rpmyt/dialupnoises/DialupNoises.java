package io.github.rpmyt.dialupnoises;

import io.github.rpmyt.dialupnoises.command.NicknameCommand;
import io.github.rpmyt.dialupnoises.command.RTPCommand;
import io.github.rpmyt.dialupnoises.event.PlayerEvents;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DialupNoises extends JavaPlugin {
    public static DialupNoises INSTANCE;

    private void registerEvents() {
        PluginManager manager = this.getServer().getPluginManager();

        manager.registerEvents(new PlayerEvents(), this);
    }

    @SuppressWarnings("ConstantConditions")
    private void registerCommands() {
        this.getCommand("rtp").setExecutor(new RTPCommand());
        this.getCommand("nick").setExecutor(new NicknameCommand());
    }

    @Override
    public void onEnable() {
        INSTANCE = DialupNoises.getPlugin(DialupNoises.class);
        this.registerEvents();
        this.registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
