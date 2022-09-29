package silverassist.silverassist.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import silverassist.silverassist.Function;
import silverassist.silverassist.SilverAssist;

import java.awt.*;
import java.util.Objects;

import static silverassist.silverassist.SilverAssist.ch_report;


public class Report implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        if(args.length != 2)return false;
        if(!(sender instanceof Player p)){
            sender.sendMessage(Function.prefix+"§c§lこのコマンドは、プレイヤーのみ実行可能です。");
            return true;
        }

        //メッセージ作成
        EmbedBuilder eb = new EmbedBuilder();
        eb.setThumbnail("https://minotar.net/avatar/"+p.getUniqueId());
        eb.setColor(Color.YELLOW);
        eb.addField(args[0],args[1],false);
        eb.setFooter("sent by "+p.getName()+" ("+p.getUniqueId()+")");

        //Discord送信
        JDA jda = SilverAssist.jda;
        Objects.requireNonNull(jda.getTextChannelById(ch_report)).sendMessage(eb.build()).queue();


        p.sendMessage(Function.prefix+"§a§l報告ありがとうございます。");
        p.sendMessage(Function.prefix+"§a§l(件名:§d§l"+args[0]+"§a§l)");

        return true;
    }
}
