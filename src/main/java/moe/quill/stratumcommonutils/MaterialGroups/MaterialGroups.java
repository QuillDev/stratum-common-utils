package moe.quill.stratumcommonutils.MaterialGroups;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashSet;

@SuppressWarnings("unused")
public class MaterialGroups {

    //Updated for 1.17
    public static HashSet<Material> ORE_GROUP = new HashSet<>(Arrays.asList(
            //Normal Ores
            Material.COAL_ORE,
            Material.COPPER_ORE,
            Material.IRON_ORE,
            Material.GOLD_ORE,
            Material.DIAMOND_ORE,
            Material.EMERALD_ORE,
            Material.LAPIS_ORE,
            Material.REDSTONE_ORE,

            //Deep slate Normal Ores
            Material.DEEPSLATE_COAL_ORE,
            Material.DEEPSLATE_COPPER_ORE,
            Material.DEEPSLATE_IRON_ORE,
            Material.DEEPSLATE_GOLD_ORE,
            Material.DEEPSLATE_DIAMOND_ORE,
            Material.DEEPSLATE_EMERALD_ORE,
            Material.DEEPSLATE_LAPIS_ORE,
            Material.DEEPSLATE_REDSTONE_ORE,

            //Nether ores
            Material.NETHER_QUARTZ_ORE,
            Material.NETHER_GOLD_ORE,
            Material.ANCIENT_DEBRIS
    ));

    //Updated for 1.17
    //Mining group consisting or ores, stone, blackstone, etc
    public static HashSet<Material> MINING = new HashSet<>() {{

        //Add stone alternatives
        addAll(
                Arrays.asList(
                        //Over world stone alts
                        Material.STONE,
                        Material.COBBLESTONE,
                        Material.DIORITE,
                        Material.GRANITE,
                        Material.ANDESITE,
                        Material.DEEPSLATE,
                        Material.AMETHYST_CLUSTER //TODO: Look into this, idk how it works
                ));
        addAll(ORE_GROUP);
    }};

    //Updated for 1.17
    //All types of placeable ice blocks
    public static HashSet<Material> ICE = new HashSet<>(
            Arrays.asList(
                    Material.ICE,
                    Material.BLUE_ICE,
                    Material.PACKED_ICE,
                    Material.FROSTED_ICE
            )
    );

    //Updated for 1.17
    //All types of logs
    public static HashSet<Material> LOGS = new HashSet<>(Arrays.asList(
            Material.ACACIA_LOG,
            Material.BIRCH_LOG,
            Material.JUNGLE_LOG,
            Material.DARK_OAK_LOG,
            Material.OAK_LOG,
            Material.SPRUCE_LOG,
            Material.CRIMSON_STEM,
            Material.WARPED_STEM
    ));
};
