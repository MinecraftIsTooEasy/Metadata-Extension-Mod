package btw.community.arminias.metadata.mixin;

import net.minecraft.src.Packet56MapChunks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Packet56MapChunks.class)
public class Packet56MapChunksMixin {
    @ModifyConstant(method = "readPacketData", constant = @Constant(intValue = 196864))
    private static int readPacketDataInject(int constant) {
        return constant * 2;
    }

    @ModifyConstant(method = "readPacketData", constant = @Constant(intValue = 8192))
    private static int readPacketDataInject2(int constant) {
        return constant + 8 * 2048;
    }
}
