package ben10dollar.emeraldgear.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class EmeraldShovel extends ShovelItem {

    public EmeraldShovel(ToolMaterial material) {
        super(material, 1.5f, -3f, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
