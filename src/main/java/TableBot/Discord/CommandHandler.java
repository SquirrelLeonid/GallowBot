package TableBot.Discord;

import TableBot.Games.*;
import TableBot.Games.Cards.CardgameModel;
import TableBot.Games.Gallows.GallowsModel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandHandler extends ListenerAdapter
{
    private String[] Message;

    private final String Prefix = "-";
    private final ActivityKeeper ActivityKeeper = new ActivityKeeper();
    private String LastUsername = "";

    public void onGuildMessageReceived (GuildMessageReceivedEvent event)
    {
        LastUsername = event.getAuthor().getName();
        Message = event.getMessage().getContentRaw().split("\\s+");

        switch (Message[0].toLowerCase())
        {
            case (Prefix + "help"):
                event.getChannel().sendMessage("I do nothing").queue();
                break;
            case (Prefix + "gallows"):
                handleGallowsCommands(event);
                break;
            case (Prefix + "twenty-one"):
                handleCardgameCommands(event);
                break;
            case (Prefix + "activities"):
                event.getChannel().sendMessage(ActivityKeeper.showActivities()).queue();
            default:
                break;
        }
    }

    private void handleGallowsCommands(GuildMessageReceivedEvent event)
    {
        switch (Message[1].toLowerCase())
        {
            case ("start"):
                if (!ActivityKeeper.userHasActivity(LastUsername))
                    ActivityKeeper.addActivity(LastUsername, new GameModels(new GallowsModel()));
            case ("letter"):
                //TODO send to gallows's game model Message[2].
                break;
            case ("help"):
                //TODO Write rules and commands for this game. Use message patterns
                break;
            case ("end"):
                if (ActivityKeeper.userHasActivity(LastUsername))
                    ActivityKeeper.deleteActivity(LastUsername);
                break;
            default:
                break;
        }
    }

    private void handleCardgameCommands(GuildMessageReceivedEvent event)
    {
        switch (Message[1].toLowerCase())
        {
            case ("start"):
                if (!ActivityKeeper.userHasActivity(LastUsername))
                    ActivityKeeper.addActivity(LastUsername, new GameModels(new CardgameModel()));
                break;
            case ("help"):
                //TODO Write rules and commands for this game.
                break;
            case ("next"):
                //TODO give that user next card from a remaining list.
                break;
            case ("end"):
                if (ActivityKeeper.userHasActivity(LastUsername))
                    ActivityKeeper.deleteActivity(LastUsername);
                break;
            default:
                break;
        }
    }
}
