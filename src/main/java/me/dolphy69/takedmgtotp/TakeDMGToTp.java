package me.dolphy69.takedmgtotp;

import me.dolphy69.takedmgtotp.commands.DmgtotpCommand;
import me.dolphy69.takedmgtotp.commands.RangeCommand;
import me.dolphy69.takedmgtotp.listeners.PlayerDeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TakeDMGToTp extends JavaPlugin {

    private Object getFromCFG(String path, Object defaultValue)
    {
        if(!getConfig().contains(path))
        {
            getConfig().set(path, defaultValue);
            return defaultValue;
        }
        return getConfig().get(path);
    }

    @Override
    public void onEnable() {
        GlobalValues.range = (int) getFromCFG(GlobalValues.rangePath, GlobalValues.range);

        getCommand("dmgtotp").setExecutor(new DmgtotpCommand());
        getCommand("tprange").setExecutor(new RangeCommand());
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }

    @Override
    public void onDisable() {
        getConfig().set(GlobalValues.rangePath, GlobalValues.range);
        saveConfig();
    }
}
