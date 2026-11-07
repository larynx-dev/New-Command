package dev.larynx.hellocommand;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

/**
 * Defines the {@code /hello} command.
 *
 * <p>Minecraft uses Mojang's <a href="https://github.com/Mojang/brigadier">Brigadier</a> library for
 * commands: a command is a tree of nodes, where {@link Commands#literal(String)} creates a node that
 * matches a fixed word and {@code executes(...)} attaches the code to run when the input ends there.
 */
public final class HelloCommand {

	/** The word players type after the slash. */
	public static final String NAME = "hello";

	/** The message sent back to whoever ran the command. */
	public static final String MESSAGE = "Hello World!";

	private HelloCommand() {
		// Utility class — no instances.
	}

	/**
	 * Adds {@code /hello} to the given dispatcher.
	 *
	 * <p>No {@code .requires(...)} permission check is attached, so every player can use it,
	 * even on servers without cheats enabled.
	 *
	 * @param dispatcher the server's command dispatcher
	 */
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal(NAME).executes(HelloCommand::execute));
	}

	/**
	 * Runs when someone executes {@code /hello}.
	 *
	 * @param context holds the command source (the player, console or command block that ran it)
	 * @return {@link Command#SINGLE_SUCCESS} so Brigadier knows the command succeeded
	 */
	private static int execute(CommandContext<CommandSourceStack> context) {
		Component message = Component.literal(MESSAGE).withStyle(ChatFormatting.GOLD);

		// sendSuccess takes a Supplier so the message is only built if it will actually be shown.
		// The second argument (false) means "don't broadcast this to server operators".
		context.getSource().sendSuccess(() -> message, false);

		return Command.SINGLE_SUCCESS;
	}
}
