package TableBot.Discord;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.log4j.BasicConfigurator;

import javax.security.auth.login.LoginException;

public class Main
{
    public static void main (String[] args) throws LoginException
    {
        BasicConfigurator.configure();
        JDA jda = new JDABuilder(AccountType.BOT).setToken("").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.listening("-help"));
        jda.addEventListener(new CommandHandler());
    }
}
