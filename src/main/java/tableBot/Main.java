package tableBot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.log4j.BasicConfigurator;
import tableBot.handlers.CommandHandler;
import tableBot.handlers.ReactionsHandler;

import javax.security.auth.login.LoginException;

public class Main
{
    public static void main (String[] args) throws LoginException
    {
        BasicConfigurator.configure();
        JDA jda = new JDABuilder(AccountType.BOT).setToken("NjM2MTc4MTkzMDE5MzA1OTk1.XfZHBw.TgQpz0_i11eCo0gv-jF6QD8omJM").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.listening("-help"));
        jda.addEventListener(new CommandHandler());
        jda.addEventListener(new ReactionsHandler());
    }
}
