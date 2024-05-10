package ru.thecatrix.elytraenddisable;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.text.Text.literal;

public class ElytraEndDisable implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("elytraenddisable");

    @Override
    public void onInitialize() {
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            if (destination.getRegistryKey().getValue().toString().equals("minecraft:the_end")) {
				player.sendMessage(literal("§cВНИМАНИЕ! Полёт на элитрах отключён в Энде."));
			}
        });

        EntityElytraEvents.ALLOW.register(entity -> {
            if (entity.getEntityWorld().getRegistryKey().getValue().toString().equals("minecraft:the_end")) {
                if (entity instanceof ServerPlayerEntity player) {
                    player.sendMessage(literal("§cПолёт на элитрах отключён в Энде."), true);
                }
                return false;
            } else {
                return true;
            }
        });

        LOGGER.info("ElytraEndDisable initialized");
    }
}