package pl.otekplay.loveotek.utils;


import net.minecraft.server.v1_7_R4.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_7_R4.Scoreboard;
import net.minecraft.server.v1_7_R4.ScoreboardTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.main.Vanish;

import java.util.UUID;

public class TagUtil {
    private final static Scoreboard scoreboard = new Scoreboard();

    public static void createBoard(Player p) {
        try {
            ScoreboardTeam team = null;
            if (scoreboard.getPlayerTeam(p.getName()) == null) {
                team = scoreboard.createTeam(p.getName());
            }
            scoreboard.addPlayerToTeam(p.getName(), team.getName());
            team.setPrefix("");
            team.setDisplayName("");
            team.setSuffix("");
            PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(team, 0);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.equals(p)) {
                    continue;
                }
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
            for (Player pp : Bukkit.getOnlinePlayers()) {
                if (pp.equals(p)) {
                    continue;
                }
                ScoreboardTeam t = scoreboard.getTeam(pp.getName());
                PacketPlayOutScoreboardTeam packetShow = new PacketPlayOutScoreboardTeam(t, 0);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packetShow);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void updateBoard(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        updateOthersFor(p, online);
                        updateOthersFor(online, p);
                    }
                }
        );
    }

    private static void updateOthersFor(Player send, Player p) {
        ScoreboardTeam team = scoreboard.getPlayerTeam(p.getName());
        User get = Users.get(p.getUniqueId());
        User s = Users.get(send.getUniqueId());
        team.setPrefix(getValidPrefix(get, s));
        team.setSuffix(getValidSuffix(get, s));
        PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(team, 2);
        ((CraftPlayer) send).getHandle().playerConnection.sendPacket(packet);
    }


    private static String getValidPrefix(User get, User send) {
        if (get.getRank().can(UserRank.MODERATOR)) {
            return get.getRank().getPrefix() + ChatColor.RED;
        }
        ChatColor color = ChatColor.DARK_RED;
        if (get.hasGuild() && send.hasGuild()) {
            if (get.getGuild().equals(send.getGuild())) {
                color = ChatColor.GREEN;
            } else if (send.getGuild().isAllied(get.getGuild().getTag())) {
                color = ChatColor.YELLOW;
            }
        }
        String tag = "";
        if (get.hasGuild()) {
            tag = ChatColor.GRAY + "[" + color + get.getGuild().getTag() + ChatColor.GRAY + "] ";
        }
        return tag + ChatColor.WHITE;
    }

    private static String getValidSuffix(User get, User send) {
        if (get.hasPermissions(UserRank.PREMIUM)) {
            return Vanish.has(get.getUniqueID()) ? "[⚠] " + get.getRank().getIcon() : get.getRank().getIcon();
        }
        if (!get.hasGuild()) {
            return "";
        }
        Guild guild = get.getGuild();
        GuildRank rank = guild.getGuildRank(get.getUniqueID());
        switch (rank) {
            case LEADER: {
                return ChatColor.RED + "[♔]";
            }
            case OFFICER: {
                return ChatColor.RED + "[★]";
            }
            default:
                return "";
        }
    }

    public static void removeBoard(Player p) {
        ScoreboardTeam team = scoreboard.getPlayerTeam(p.getName());
        scoreboard.removePlayerFromTeam(p.getName(), team);
        PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(team, 1);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (pp.equals(p)) {
                continue;
            }
            ((CraftPlayer) pp).getHandle().playerConnection.sendPacket(packet);
            ScoreboardTeam t = scoreboard.getTeam(pp.getName());
            PacketPlayOutScoreboardTeam packetHide = new PacketPlayOutScoreboardTeam(t, 1);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packetHide);
        }
        scoreboard.removeTeam(team);

    }


}