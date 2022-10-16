package net.vanillaplus.updatenotifier;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UpdateNotifier extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
msgAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void msgAll(){
        for(Player p: Bukkit.getOnlinePlayers()){

            msgPlayer(p);

        }
    }

    public void msgPlayer(Player p){
//
//            if(!p.hasPermission("update.test")){
//                return;
//            }


        if(p.getStatistic(Statistic.PLAY_ONE_MINUTE)<this.getConfig().getInt("minimumPlayTime")) {
            return;
        }

        MiniMessage mm = MiniMessage.miniMessage();

        for(String s: this.getConfig().getStringList("messages")){
            p.sendMessage(mm.deserialize(s));
        }



//           // String msg =("&#02acfa&lVanilla&#E000E0&l+ &fAn update has recently been made to IRL trading rules. Please read them at &#02acfavanillaplus.net/rules");
//        String msg = ("       Check out the brand new &#FF9E0Dᴏ&#974DFFᴄ&#FF9E0Dᴛ&#974DFFᴏ&#FF9E0Dʙ&#974DFFᴇ&#FF9E0Dʀ&f ᴄʀᴀᴛᴇ, it's\n         available now for a limited time! (&#02acfa/store&f)");
//            //  msg = ChatColor.translateAlternateColorCodes('&', msg);
//
//            String space = "&m                                                                            ";
//
//            msg = chatColors(msg);
//            p.sendMessage(chatColors(space));
//            p.sendMessage("");
//            p.sendMessage(msg);
//            p.sendMessage("");
//            p.sendMessage(chatColors(space));


    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Bukkit.getScheduler().runTaskLater(this, () -> {
            msgPlayer(e.getPlayer());
        }, 20*3);

    }

}
