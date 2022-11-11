package eu.eclesia_network.bedwars.module.modules.teams;

import com.google.common.collect.Iterables;
import eu.eclesia_network.bedwars.BedWars;
import eu.eclesia_network.bedwars.module.Module;
import eu.eclesia_network.bedwars.module.ModuleType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Collection;

import static java.awt.Color.RED;
import static java.awt.Color.red;
import static org.bukkit.scoreboard.Team.Option.NAME_TAG_VISIBILITY;

public class Teams extends Module {
    public Teams(BedWars plugin) {
        super(plugin, ModuleType.TEAMS);
    }
    Scoreboard scoreboard = getPlugin().getServer().getScoreboardManager().getMainScoreboard();
    Team.Option tagVisibility = NAME_TAG_VISIBILITY;
    @Override
    public void onEnable() {

        Team Rouge = scoreboard.registerNewTeam("Rouge");
        Team Bleu = scoreboard.registerNewTeam("Bleu");
        Team Jaune = scoreboard.registerNewTeam("Jaune");
        Team Vert = scoreboard.registerNewTeam("Vert");

        Rouge.setColor(ChatColor.RED);
        Bleu.setColor(ChatColor.BLUE);
        Vert.setColor(ChatColor.GREEN);
        Jaune.setColor(ChatColor.YELLOW);

        Rouge.setPrefix(ChatColor.RED+" ");
        Bleu.setPrefix(ChatColor.BLUE+" ");
        Vert.setPrefix(ChatColor.GREEN+" ");
        Jaune.setPrefix(ChatColor.YELLOW+" ");

        Rouge.setAllowFriendlyFire(false);
        Bleu.setAllowFriendlyFire(false);
        Jaune.setAllowFriendlyFire(false);
        Vert.setAllowFriendlyFire(false);
    }

    @Override
    public void onDisable() {
        Collection<String> equipelist = new ArrayList<>();
        for(int i = 0; i < scoreboard.getTeams().size(); i++){
            if (!scoreboard.getTeams().iterator().hasNext()) continue;
            equipelist.add(scoreboard.getTeams().iterator().next().getName());
        }
        for(int i = 0; i < equipelist.size(); i++){ Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "team remove " + Iterables.get(equipelist, i)); }
    }

}
