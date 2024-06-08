package software.ethan.listener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.jetbrains.annotations.NotNull;
import software.ethan.MrBot;

public class DiscordEventListener extends ListenerAdapter{
    public MrBot bot;
    public DiscordEventListener(MrBot bot){
        this.bot = bot;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        registerCommands(bot.getShardManager());
    }

    private void registerCommands(ShardManager jda) {
        Guild g = jda.getGuildById("1177246310533955604");
        if (g!=null) {
            CommandListUpdateAction commands = g.updateCommands();
            commands.addCommands(Commands.slash("hello", "Have the bot say hello to you in an ephemeral message!")).queue();
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("hello")) {
            event.reply("Hello " + event.getUser().getAsMention() + "!")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
