package ben10dollar.emeraldgear;

import ben10dollar.emeraldgear.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class EmeraldGear implements ModInitializer {

    public static final String MOD_ID = "emeraldgear";

    @Override
    public void onInitialize() { ModItems.registerItems(); }


}
