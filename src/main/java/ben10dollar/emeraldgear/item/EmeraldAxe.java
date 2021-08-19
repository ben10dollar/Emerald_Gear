package ben10dollar.emeraldgear.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class EmeraldAxe extends AxeItem {

    public EmeraldAxe(ToolMaterial material) {
        super(material, 5, -3f, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
