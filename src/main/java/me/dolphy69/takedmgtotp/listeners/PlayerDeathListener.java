package me.dolphy69.takedmgtotp.listeners;

import me.dolphy69.takedmgtotp.GlobalValues;
import me.dolphy69.takedmgtotp.TakeDMGToTp;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.*;

public class PlayerDeathListener implements Listener {

    private boolean isAirOrFluid(Material m)
    {
        return (m == Material.AIR || m == Material.CAVE_AIR || m == Material.VOID_AIR || m == Material.WATER || m == Material.LAVA);
    }

    private boolean isOkToTP(Location location)
    {
        World world = location.getWorld();

        Material thisBlock = world.getBlockAt(location).getType();
        Location l = new Location(location.getWorld(), location.getBlockX(), location.getBlockY()-1, location.getBlockZ());
        Material downBlock = world.getBlockAt(l).getType();
        l = new Location(location.getWorld(), location.getBlockX(), location.getBlockY()+1, location.getBlockZ());
        Material upBlock = world.getBlockAt(l).getType();
        return (isAirOrFluid(thisBlock) && isAirOrFluid(upBlock) && !isAirOrFluid(downBlock));
    }

    private List<Integer> getTeleportableHeights(Location loc)
    {
        World world = loc.getWorld();
        List<Integer> result = new ArrayList<>();
        for(int i = world.getMinHeight(); i < world.getMaxHeight(); i++)
        {
            loc.setY(i);
            if(isOkToTP(loc)) result.add(i);
        }
        return result;
    }

    private int sum(List<Integer> list, int lastIndex)
    {
        if(lastIndex == -1) lastIndex = list.size() - 1;
        int s = 0;
        for(int i = 0; i <= lastIndex; i++)
        {
            s += list.get(i);
        }
        return s;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e)
    {
        if(!GlobalValues.isEnabled) return;
        if(e.getEntity() instanceof Player p)
        {
            Location loc = p.getLocation();
            Location newPos;
            int x,y = 0,z;
            List<Integer> heightList = new ArrayList<>();
            Random r = new Random();

            // Find teleportable places in given column.
            do {
                x = loc.getBlockX() + r.nextInt(-GlobalValues.range, GlobalValues.range + 1);
                z = loc.getBlockZ() + r.nextInt(-GlobalValues.range, GlobalValues.range + 1);
                newPos = new Location(loc.getWorld(), x, 0, z);
                heightList = getTeleportableHeights(newPos);
            }while (heightList.isEmpty());

            // Has more chance of spawning on higher places.
            int s = sum(heightList, -1);
            int rnd = r.nextInt(loc.getWorld().getMinHeight(), s);
            for(int i = 0; i < heightList.size(); i++)
            {
                int s2 = sum(heightList, i);
                if(rnd < s2)
                {
                    y = heightList.get(i);
                    break;
                }
            }

            // If you want equal chances use this
            // Collections.shuffle(heightList);
            // y = heightList.get(0);

            newPos = new Location(loc.getWorld(), x + 0.5, y, z + 0.5);

            p.teleport(newPos);
        }
    }

}
