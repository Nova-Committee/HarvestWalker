package committee.nova.harvestwalker.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec CFG;
    public static final ForgeConfigSpec.BooleanValue autoReplant;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("general");
        autoReplant = builder.comment("Whether the crop should be auto replanted after harvested by the enchantment")
                .define("autoReplant", true);
        builder.pop();
        CFG = builder.build();
    }
}
