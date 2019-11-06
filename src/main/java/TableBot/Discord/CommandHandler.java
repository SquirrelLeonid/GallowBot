package TableBot.Discord;

import TableBot.Games.*;
import TableBot.Games.Cards.CardgameModel;
import TableBot.Games.Gallows.GallowsModel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.utils.AttachmentOption;


public class CommandHandler extends ListenerAdapter
{
    private String[] Message;
    private String LastUsername = "";

    private final String Prefix = "-";
    private final ActivitiesKeeper ActivitiesKeeper = new ActivitiesKeeper();

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
                event.getChannel().sendMessage(ActivitiesKeeper.showActivities()).queue();
                break;
            default:
                break;
        }
    }

    private void handleGallowsCommands(GuildMessageReceivedEvent event)
    {
        switch (Message[1].toLowerCase())
        {
            case ("start"):
                if (! ActivitiesKeeper.userHasActivity(LastUsername))
                {
                    //Creation is correct
                    Activity activity = new Activity(new GallowsModel());
                    ActivitiesKeeper.addActivity(LastUsername, activity);
                    event.getChannel().sendFile(activity.gallowsModel.gameField.getImage(), "Start.jpg").queue();
                }
                else
                    event.getChannel().sendMessage("You have an unfinished game." +
                            " Finish it or complete it with a command '-<Name of game> stop'").queue();
                break;
            case ("letter"):
                //TODO send to gallows's game model Message[2].
                break;
            case ("help"):
                //TODO Write rules and commands for this game. Use message patterns
                break;
            case ("stop"):
                if (ActivitiesKeeper.userHasActivity(LastUsername))
                {
                    ActivitiesKeeper.deleteActivity(LastUsername);
                    event.getChannel().sendMessage("Your game is stopped."
                            + " To start new game use command -<Name of game> start").queue();
                }
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
                if (! ActivitiesKeeper.userHasActivity(LastUsername))
                    ActivitiesKeeper.addActivity(LastUsername, new Activity(new CardgameModel()));
                break;
            case ("help"):
                //TODO Write rules and commands for this game.
                break;
            case ("next"):
                //TODO give that user next card from a remaining list.
                break;
            case ("stop"):
                if (ActivitiesKeeper.userHasActivity(LastUsername))
                    ActivitiesKeeper.deleteActivity(LastUsername);
                break;
            default:
                break;
        }
    }
}
