package eu.eclesia_network.bedwars.module.modules.world;

import com.google.common.collect.Iterables;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import eu.eclesia_network.bedwars.BedWars;

import eu.eclesia_network.bedwars.module.Module;
import eu.eclesia_network.bedwars.module.ModuleType;
import eu.eclesia_network.bedwars.module.modules.inventory.inventorys.*;
import org.bukkit.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class WorldManager extends Module {

    private BedWars plugin;
    World world;
    Collection<String> worlds = Arrays.asList("arena1", "arena2", "arena3");


    public WorldManager(BedWars plugin){ super(plugin, ModuleType.WORLD); this.plugin = plugin; }

    @Override
    public void onEnable() {
        for(int i=0; i<worlds.size(); i++){ createWorld(Iterables.get(worlds, i)); }
        /*new BricksGenerator(plugin).create();
        new IronGenerator(plugin).create();
        new GoldGenerator(plugin).create();
        new EmeraldGenerator(plugin).create();*/
    }

    @Override
    public void onDisable() {
        for(World name : plugin.getServer().getWorlds()){
            if(name.getName().equalsIgnoreCase("lobby")) continue;
            if(name.getName().equalsIgnoreCase("dev")) continue;
            deleteWorld(name.getName());
        }
    }

    public void createWorld(String name){
        WorldCreator creator = new WorldCreator(name);
        creator.type(WorldType.FLAT);
        creator.generateStructures(false);
        creator.generator(new CustomChunkGenerator());
        world = creator.createWorld();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvimport "+name+" normal");
        paste(new File(plugin.getDataFolder(),"./schematics/bedwars.schem"), new Location(world, 0, 0, 0));
        new MiddleChest().performU(world);
        new BlocsInventory().performU(world);
        new ArmeInventory().performU(world);
        new FoodInventory().performU(world);
        new StuffInventory().performU(world);
    }

    public void deleteWorld(String name){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvdelete "+name);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvconfirm");
    }

    public void paste(File file, Location location){
        try {

            ClipboardFormat format = ClipboardFormats.findByFile(file);
            ClipboardReader reader = format.getReader(new FileInputStream(file));
            Clipboard clipboard = reader.read();
            int width = clipboard.getDimensions().getBlockX();
            int height = clipboard.getDimensions().getBlockY();
            int lenght = clipboard.getDimensions().getBlockZ();
            location.subtract(width/2.00, height/2.00, lenght/2.00);
            clipboard.setOrigin(clipboard.getRegion().getMinimumPoint());
            try(EditSession editSession = WorldEdit.getInstance().newEditSession(new BukkitWorld(location.getWorld()))) {
                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
                        .copyEntities(false)
                        .ignoreAirBlocks(false)
                        .build();
                Operations.complete(operation);
                Operations.complete(editSession.commit());
            }

        }catch (IOException | WorldEditException e){
            e.printStackTrace();
        }
    }


}
