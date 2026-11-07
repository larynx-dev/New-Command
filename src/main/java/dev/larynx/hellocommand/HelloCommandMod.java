package dev.larynx.hellocommand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entrypoint of the Hello Command mod.
 *
 * <p>Fabric calls {@link #onInitialize()} once while the game is loading. We use it to hook into
 * Fabric API's {@link CommandRegistrationCallback}, which fires every time the server (dedicated or
 * integrated singleplayer) builds its command tree.
 */
public final class HelloCommandMod implements ModInitializer {

	/** The mod id, as declared in {@code fabric.mod.json}. */
	public static final String MOD_ID = "hellocommand";

	/** Logger named after the mod id so its messages are easy to spot in the game log. */
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register our command(s) whenever the server's command dispatcher is (re)built.
		CommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess, environment) -> HelloCommand.register(dispatcher));

		LOGGER.info("Hello Command loaded — try /hello in game!");
	}
}
