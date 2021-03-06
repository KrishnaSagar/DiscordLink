package com.karateman.discordlink.modules.gamechat.events;

import com.karateman.discordlink.DiscordLinkPlugin;
import com.karateman.discordlink.modules.util.RankUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class GamechatSpigotChatEvent implements Listener {

    private DiscordLinkPlugin plugin;

    public GamechatSpigotChatEvent(DiscordLinkPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String format = plugin.getConfig().getString("gamechat-discord-format");
        if(format.contains("%prefix%")) format = format.replace("%prefix%", plugin.getRankUtil().getRankPrefix(event.getPlayer()));
        if(format.contains("%username%")) format = format.replace("%username%", event.getPlayer().getName());
        if(format.contains("%message%")) format = format.replace("%message%", event.getMessage());

        plugin.getDiscordUtils().sendMessage(format, plugin.getGamechatModule().getChannel());
    }
}
