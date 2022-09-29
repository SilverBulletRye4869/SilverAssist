package silverassist.silverassist;

import com.sun.tools.javac.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import silverassist.silverassist.command.Report;

import javax.security.auth.login.LoginException;

public final class SilverAssist extends JavaPlugin {
    public static JDA jda = null;
    private static String BOT_TOKEN = null;
    public static String ch_report = null;
    public Main plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        BOT_TOKEN = (String)this.getConfig().get("token");
        ch_report = (String)this.getConfig().get("ch_report");
        startBot();

        //レポートコマンド
        PluginCommand command = getCommand("report");
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
                    .setActivity(Activity.playing("仕事中！！"))
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
