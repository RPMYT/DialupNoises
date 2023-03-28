package io.github.rpmyt.dialupnoises.util;

import io.github.rpmyt.dialupnoises.DialupNoises;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerStringStorage {
    public static final NamespacedKey KEY_TITLE = new NamespacedKey(DialupNoises.INSTANCE, "title");
    public static final NamespacedKey KEY_NICKNAME = new NamespacedKey(DialupNoises.INSTANCE, "nickname");

    public static String get(Player player, NamespacedKey key, String fallback) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        String value = pdc.get(key, PersistentDataType.STRING);

        return value != null && !value.isEmpty() ? value : fallback;
    }

    public static void set(Player player, String value, NamespacedKey key) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(key, PersistentDataType.STRING, value);
    }
}
