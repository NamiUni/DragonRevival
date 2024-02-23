package com.github.namiuni.dragonrevival;

import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.List;
import java.util.Objects;

@DefaultQualifier(NonNull.class)
public final class DragonRevivalListener implements Listener {

    @EventHandler
    public void onWorldChange(final PlayerChangedWorldEvent event) {
        final Player player = event.getPlayer();
        if (player.getWorld().getEnvironment() != World.Environment.THE_END) {
            return;
        }

        // kill_dragonの進捗を取得している場合リターン
        final NamespacedKey killDragon = NamespacedKey.minecraft("end/kill_dragon");
        final Advancement advancement = Objects.requireNonNull(player.getServer().getAdvancement(killDragon));
        final AdvancementProgress progress = player.getAdvancementProgress(advancement);
        if (progress.isDone()) {
            return;
        }

        // ドラゴンが生きている場合リターン
        final DragonBattle dragonBattle = Objects.requireNonNull(player.getWorld().getEnderDragonBattle());
        if (dragonBattle.getEnderDragon() != null) {
            return;
        }

        dragonBattle.initiateRespawn(List.of());
    }
}
