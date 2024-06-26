package net.thelars.wish_mod.item;

import net.thelars.wish_mod.WishMod;
import net.thelars.wish_mod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WishMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WISH_TAB = CREATIVE_MODE_TABS.register("wish_tab",
            ()-> CreativeModeTab.builder().icon(
                    () -> new ItemStack

                            (ModItems.LAMP_SHARD.get()))
                    .title(Component.translatable("creativetab.wishmod_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.LAMP_SHARD.get());
                        pOutput.accept(ModItems.LAMP_ASSEMBLY.get());

                        pOutput.accept(Items.DIAMOND);

                        pOutput.accept(ModBlocks.STONE_WISH_ORE_BLOCK.get());


                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

