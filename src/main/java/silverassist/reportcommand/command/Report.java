package silverassist.reportcommand.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import silverassist.reportcommand.ReportCommand;

import java.awt.*;


public class Report implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length != 2)return false;
        if(!(sender instanceof Player)){
            sender.sendMessage("プレイヤーのみ実行可能です");
            return false;
        }


        Player p = (Player)sender;
        EmbedBuilder eb = new EmbedBuilder();
        eb.setThumbnail("https://minotar.net/avatar/"+p.getUniqueId());
        eb.setColor(Color.YELLOW);
        eb.addField(args[0],args[1],false);
        eb.setFooter("sended by "+p.getName()+" ("+p.getUniqueId()+")");


        JDA jda = ReportCommand.jda;
        jda.getTextChannelById("867722617431654430").sendMessage(eb.build()).queue();
        //jda.getTextChannelById("867722617431654430").sendMessage("test").queue();

        return true;
    }
}
