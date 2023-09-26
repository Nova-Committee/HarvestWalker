package committee.nova.harvestwalker.enchantment.impl;

import committee.nova.harvestwalker.util.HarvestUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HarvestWalkerEnchantment extends Enchantment {
    public HarvestWalkerEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    public static void onEntityMoved(LivingEntity entity, Level level, BlockPos posIn, int enchantmentLevel) {
        if (!entity.onGround()) return;
        final int radius = Math.min(2, enchantmentLevel - 1);
        final Iterable<BlockPos> p = BlockPos.betweenClosed(posIn.offset(-radius, -1, -radius), posIn.offset(radius, 0, radius));
        for (final BlockPos pos : p) {
            final BlockState landState = level.getBlockState(pos);
            if (!landState.is(Blocks.FARMLAND)) continue;
            final BlockPos cropPos = pos.above();
            final BlockState cropState = level.getBlockState(cropPos);
            if (!(cropState.getBlock() instanceof CropBlock crop)) continue;
            if (!crop.isMaxAge(cropState)) continue;
            if (level instanceof ServerLevel l) HarvestUtils.processHarvest(l, entity, cropPos, cropState, crop);
        }
    }

    @Override
    public int getMinCost(int i) {
        return i * 6;
    }

    @Override
    public int getMaxCost(int i) {
        return this.getMinCost(i) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
