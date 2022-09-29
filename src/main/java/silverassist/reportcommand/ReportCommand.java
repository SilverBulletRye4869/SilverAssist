package silverassist.reportcommand;

import com.sun.tools.javac.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import silverassist.reportcommand.command.Report;

import javax.security.auth.login.LoginException;

public final class ReportCommand extends JavaPlugin {
    public static JDA jda = null;
    private static String BOT_TOKEN = null;
    public Main plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        BOT_TOKEN = (String)this.getConfig().get("token");
        startBot();
        PluginCommand command = getCommand("text");
        if(command != null)command.setExecutor(new Report());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        jda.shutdownNow();
    }

    private static void startBot(){
        try {
            jda = JDABuilder.createDefault(BOT_TOKEN, GatewayIntent.GUILD_MESSAGES)
                    .setRawEventsEnabled(true)
                    .setActivity(Activity.playing("CONAN!"))
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
