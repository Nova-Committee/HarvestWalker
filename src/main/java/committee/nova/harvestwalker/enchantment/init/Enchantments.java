package committee.nova.harvestwalker.enchantment.init;

import committee.nova.harvestwalker.HarvestWalker;
import committee.nova.harvestwalker.enchantment.impl.HarvestWalkerEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

public enum Enchantments implements Supplier<Enchantment> {
    HARVEST_WALKER(HarvestWalkerEnchantment::new);

    Enchantments(Supplier<Enchantment> sup) {
        this.obj = HarvestWalker.ENCHANTMENT.register(this.name().toLowerCase(Locale.ROOT), sup);
    }

    public static void init() {

    }

    private final RegistryObject<Enchantment> obj;

    @Override
    public Enchantment get() {
        return obj.get();
    }
}
