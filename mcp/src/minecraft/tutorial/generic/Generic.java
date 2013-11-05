package tutorial.generic;

// This Import list will grow longer with each additional tutorial.
// It's not pruned between full class postings, unlike other tutorial code.
import java.net.URISyntaxException;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
//import cpw.mods.fml.common.Mod.Init;       //Used in 1.5.2 and before
import cpw.mods.fml.common.Mod.Instance;
//import cpw.mods.fml.common.Mod.PostInit;   //Used in 1.5.2 and before
//import cpw.mods.fml.common.Mod.PreInit;    //Used in 1.5.2 and before
import cpw.mods.fml.common.Mod.EventHandler; //Added for 1.6.X
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="generic", name="Generic", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Generic {
        
        
        @Instance(value="generic")
        public static Generic instance;

        private static Item genericItem;
        
        public static SourcecraftWorldGenerator sourcecraftWorldGenerator;

        @SidedProxy(clientSide="tutorial.generic.client.ClientProxy",
                        serverSide="tutorial.generic.CommonProxy")
        public static CommonProxy proxy;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
                genericItem = new GenericItem(5000);
                sourcecraftWorldGenerator = new SourcecraftWorldGenerator();
        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) {
                LanguageRegistry.addName(genericItem, "Generic Item");
                GameRegistry.registerWorldGenerator(sourcecraftWorldGenerator);
        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}