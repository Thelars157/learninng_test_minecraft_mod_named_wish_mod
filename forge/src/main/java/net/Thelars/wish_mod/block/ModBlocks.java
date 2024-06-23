package net.Thelars.wish_mod.block;

import net.Thelars.wish_mod.wish_mod;
import net.Thelars.wish_mod.block.custom.stone_wish_oreblock;
import net.Thelars.wish_mod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, wish_mod.MOD_ID);

    public static final RegistryObject<Block> STONE_WISH_ORE = registerBlock("STONE_WISH_ORE",
        () -> new stone_wish_oreblock(BlockBehaviour.Properties.copy(Blocks.DIRT)
                .strength(0.5f)
                .sound(SoundType.AMETHYST)
                .noOcclusion()
                .requiresCorrectToolForDrops())); 

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
