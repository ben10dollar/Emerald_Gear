package ben10dollar.emeraldgear.registry;

import ben10dollar.emeraldgear.EmeraldGear;
import ben10dollar.emeraldgear.materials.ModArmorMaterials;
import ben10dollar.emeraldgear.materials.ModToolMaterials;
import ben10dollar.emeraldgear.item.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final EmeraldSword EMERALD_SWORD = new EmeraldSword(ModToolMaterials.EMERALD);
    public static final EmeraldPickaxe EMERALD_PICK = new EmeraldPickaxe(ModToolMaterials.EMERALD);
    public static final EmeraldAxe EMERALD_AXE = new EmeraldAxe(ModToolMaterials.EMERALD);
    public static final EmeraldShovel EMERALD_SHOVEL = new EmeraldShovel(ModToolMaterials.EMERALD);

    public static final EmeraldBow EMERALD_BOW = new EmeraldBow(ModToolMaterials.EMERALD, new Item.Settings().group(ItemGroup.COMBAT));

    public static final ArmorItem EMERALD_HELMET = new ArmorItem(ModArmorMaterials.EMERALD, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final ArmorItem EMERALD_CHESTPLATE = new ArmorItem(ModArmorMaterials.EMERALD, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final ArmorItem EMERALD_LEGGINGS = new ArmorItem(ModArmorMaterials.EMERALD, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final ArmorItem EMERALD_BOOTS = new ArmorItem(ModArmorMaterials.EMERALD, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(EmeraldGear.MOD_ID, "emerald_sword"), EMERALD_SWORD);
        Registry.register(Registry.ITEM, new Identifier(EmeraldGear.MOD_ID, "emerald_pick"), EMERALD_PICK);
        Registry.register(Registry.ITEM, new Identifier(EmeraldGear.MOD_ID, "emerald_axe"), EMERALD_AXE);
        Registry.register(Registry.ITEM, new Identifier(EmeraldGear.MOD_ID, "emerald_shovel"), EMERALD_SHOVEL);

        Registry.register(Registry.ITEM, new Identifier(EmeraldGear.MOD_ID, "emerald_bow"), EMERALD_BOW);

        Registry.register(Registry.ITEM, new Identifier(EmeraldGear.MOD_ID, "emerald_helmet"), EMERALD_HELMET);
        Registry.register(Registry.ITEM, new Identifier(EmeraldGear.MOD_ID, "emerald_chest"), EMERALD_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(EmeraldGear.MOD_ID, "emerald_legs"), EMERALD_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(EmeraldGear.MOD_ID, "emerald_boots"), EMERALD_BOOTS);
    }


}
