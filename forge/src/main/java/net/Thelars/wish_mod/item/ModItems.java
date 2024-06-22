package net.Thelars.wish_mod.item;

import net.Thelars.wish_mod.wish_mod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, wish_mod.MOD_ID);
//this is where items are registered
    public static final RegistryObject<Item> LAMP_SHARD = ITEMS.register("lamp_shard",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LAMP_ASSEMBLY = ITEMS.register("lamp_assembly",
            ()-> new Item(new Item.Properties()));

    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}


