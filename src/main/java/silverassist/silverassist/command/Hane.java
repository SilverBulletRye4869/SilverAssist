package silverassist.silverassist.command;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import silverassist.silverassist.firebase.Firebase;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static silverassist.silverassist.firebase.Firebase.db;

public class Hane implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("test001");
        if(args.length != 3)return false;
        sender.sendMessage("test002");
        Player p = Bukkit.getPlayer(args[1]);
        if(p == null) return false;
        sender.sendMessage("test003");
        switch (args[0]){
            case "ban":
                sender.sendMessage("test004");/*
                Bukkit.getBanList(BanList.Type.NAME).addBan(args[1], args[2], null, null);
                Map<String,Object> Data  = new HashMap<>();
                Data.put("cond",true);
                Data.put("date", new Date());
                Data.put("reason", args[2]);
                Data.put("runner", sender);
                Data.put("term", "無期限");
                Data.put("uuid", p.getUniqueId());
*/
                // asynchronously retrieve all users
                ApiFuture<QuerySnapshot> query = db.collection("punisherlist").get();

                QuerySnapshot querySnapshot = null;
                try {
                    querySnapshot = query.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if(querySnapshot==null) return false;
                List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
                for (QueryDocumentSnapshot document : documents) {
                    System.out.println("User: " + document.getId());
                    System.out.println("count: " + document.getLong("count"));
                }


                //Player p = Bukkit.getPlayer(args[1]);
                return true;
            case "pardon":
                return true;
        }
        sender.sendMessage("test005");
        return false;
    }
}
