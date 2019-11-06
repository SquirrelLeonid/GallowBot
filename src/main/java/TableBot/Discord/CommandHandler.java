package TableBot.Discord;

import TableBot.Games.*;
import TableBot.Games.Cards.CardgameModel;
import TableBot.Games.Gallows.GallowsModel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.utils.AttachmentOption;


public class CommandHandler extends ListenerAdapter
{
    private String[] message;
    private String LastUsername = "";

    private final String Prefix = "-";
    private final ActivitiesKeeper ActivitiesKeeper = new ActivitiesKeeper();

    public void onGuildMessageReceived (GuildMessageReceivedEvent event)
    {
        LastUsername = event.getAuthor().getName();
        message = event.getMessage().getContentRaw().split("\\s+");
        switch (message[0].toLowerCase())
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
        switch (message[1].toLowerCase())
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
                if (! ActivitiesKeeper.userHasActivity(LastUsername))
                    event.getChannel().sendMessage("You aren't in game. To start use command: " +
                            "-gallows start").queue();
                else if (message.length < 3)
                    event.getChannel().sendMessage("To make a move you must write a letter").queue();
                else
                {
                    Activity activity = ActivitiesKeeper.getActivity(LastUsername);
                    activity.gallowsModel.makeMove(message[2].toLowerCase().charAt(0));
                    event.getChannel().sendFile(activity.gallowsModel.gameField.getImage(), "NextMove.jpg").queue();
                    if (activity.gallowsModel.isGameEnded())
                        ActivitiesKeeper.deleteActivity(LastUsername);
                }
                break;
            case ("help"):
                //TODO Write rules and commands for this game. Use message patterns
                event.getChannel().sendMessage("Work in process").queue();;
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
        switch (message[1].toLowerCase())
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
