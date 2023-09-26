package committee.nova.harvestwalker.util;

import committee.nova.harvestwalker.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class HarvestUtils {
    public static void processHarvest(ServerLevel l, LivingEntity e, BlockPos cropPos, BlockState cropState, CropBlock crop) {
        final var resources = Block.getDrops(cropState, l, cropPos, l.getBlockEntity(cropPos));
        final BlockPos entityPos = e.blockPosition();
        l.setBlock(cropPos, Blocks.AIR.defaultBlockState(), 3);
        l.levelEvent(null, 2001, cropPos, Block.getId(cropState));
        final SoundType type = crop.getSoundType(cropState, l, cropPos, e);
        l.playSound(null, cropPos, type.getBreakSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
        for (final ItemStack r : resources) Block.popResource(l, entityPos, r);
        if (CommonConfig.autoReplant.get() && e instanceof Player p) {
            if (p.getInventory().clearOrCountMatchingItems(s -> s.getItem().equals(crop.getCloneItemStack(l, cropPos, cropState).getItem()),
                    1, p.inventoryMenu.getCraftSlots()) <= 0) return;
            l.setBlock(cropPos, crop.getStateForAge(0), 3);
            l.playSound(null, cropPos, type.getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
}
