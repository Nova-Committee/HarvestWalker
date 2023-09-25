package committee.nova.harvestwalker.mixin;

import committee.nova.harvestwalker.enchantment.impl.HarvestWalkerEnchantment;
import committee.nova.harvestwalker.enchantment.init.Enchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    public MixinLivingEntity(EntityType<?> t, Level l) {
        super(t, l);
    }

    @Inject(method = "onChangedBlock", at = @At("HEAD"))
    private void inject$onChangedBlock(BlockPos pos, CallbackInfo ci) {
        final int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.HARVEST_WALKER.get(), (LivingEntity) (Object) this);
        if (i > 0) HarvestWalkerEnchantment.onEntityMoved((LivingEntity) (Object) this, this.level(), pos, i);
    }
}
