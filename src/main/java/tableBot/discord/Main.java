package tableBot.discord;

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
        JDA jda = new JDABuilder(AccountType.BOT).setToken("NjM2MTc4MTkzMDE5MzA1OTk1.XdFZNA.vYEqqK1C6Or0tdfL1Xvn9XaVHjo").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.listening("-help"));
        jda.addEventListener(new CommandHandler());
    }
}
