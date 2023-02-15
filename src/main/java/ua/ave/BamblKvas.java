package ua.ave;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ua.ave.commands.*;
import ua.ave.config.Config;
import ua.ave.data.Constants;
import ua.ave.data.TeamScoreBoard;
import ua.ave.eventHandlers.ArmorEffects;
import ua.ave.eventHandlers.Listeners;
import ua.ave.eventHandlers.ShardLightningEvent;
import ua.ave.eventHandlers.ShardSpawnEvent;
import ua.ave.heroes.*;

import static ua.ave.data.Constants.*;

public final class BamblKvas extends JavaPlugin {

    Listener[] heroes = new Listener[]{
            new FacelessVoid(),
            new Lycan(),
            new PhantomAssassin(),
            new Pudge(),
            new Venomancer(),
            new WraithKing(),
            new Zeus(),
            new Techies(),
            new ShadowFiend(),
            new Edgar(),
            new Abaddon(),
            new UkrDragon(),
            new Tidehunter(),
    };

    @Override
    public void onEnable() {
        plugin = this;
        teamScoreBoard = new TeamScoreBoard();
        shardSpawnEvent = new ShardSpawnEvent();
        shardLightningEvent = new ShardLightningEvent();
        Constants.config = Config.reloadConfig();
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        for (Listener hero : heroes) {
            getServer().getPluginManager().registerEvents(hero, this);
        }
        teamScoreBoard.runTaskTimer(this, 0L, 1L);
        new ArmorEffects().runTaskTimer(this, 0L, 1L);
        shardLightningEvent.runTaskTimer(this, 0L, 1L);
        getCommand("team").setExecutor(new TeamCommand());
        getCommand("clearteam").setExecutor(new ClearTeamCommand());
        getCommand("startgame").setExecutor(new StartGameCommand());
        getCommand("stopgame").setExecutor(new StopGameCommand());
        getCommand("pick").setExecutor(new PickCommand());
        getCommand("reloadconfig").setExecutor(new ReloadConfigCommand());
        getCommand("pause").setExecutor(new PauseCommand());
        getCommand("unpause").setExecutor(new UnpauseCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
