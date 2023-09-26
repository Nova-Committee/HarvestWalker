package committee.nova.harvestwalker;

import committee.nova.harvestwalker.config.CommonConfig;
import committee.nova.harvestwalker.enchantment.init.Enchantments;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(HarvestWalker.MODID)
public class HarvestWalker {
    public static final String MODID = "harvestwalker";
    public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

    public HarvestWalker() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.CFG);
        ENCHANTMENT.register(FMLJavaModLoadingContext.get().getModEventBus());
        Enchantments.init();
    }
}
