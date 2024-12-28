package btw.community.arminias.metadata.mixin;

import btw.community.arminias.metadata.HunkArray;
import btw.community.arminias.metadata.extension.ExtendedBlockStorageExtension;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;

@Mixin(Packet51MapChunk.class)
public class Packet51MapChunkMixin {
    @Shadow private static byte[] temp;

    @Unique
    private static int copyArrayLong2Byte(long[] data, int dataPos, byte[] dest, int destPos, int length) {
        ByteBuffer.wrap(dest, destPos, length * 8).asLongBuffer().put(data);
        return destPos + length * 8;
    }

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void initInject(CallbackInfo ci) {
        temp = new byte[196864 * 2];
    }

    @ModifyConstant(method = "readPacketData", constant = @Constant(intValue = 12288))
    private int readPacketDataInject(int constant) {
        return constant + 2048 * 8;
    }

    /**
     * @author Arminias
     */
    @Overwrite
    public static Packet51MapChunkData getMapChunkData(Chunk chunk, boolean bl, int i) {
        int var3 = 0;
        ExtendedBlockStorage[] var4 = chunk.getBlockStorageArray();
        int var5 = 0;
        Packet51MapChunkData var6 = new Packet51MapChunkData();
        byte[] var7 = temp;
        if (bl) {
            chunk.sendUpdates = true;
        }

        for (int var8 = 0; var8 < var4.length; ++var8) {
            if (var4[var8] != null && (!bl || !var4[var8].isEmpty()) && (i & 1 << var8) != 0) {
                var6.chunkExistFlag |= 1 << var8;
                if (var4[var8].getBlockMSBArray() != null) {
                    var6.chunkHasAddSectionFlag |= 1 << var8;
                    ++var5;
                }
            }
        }

        for (int var10 = 0; var10 < var4.length; ++var10) {
            if (var4[var10] != null && (!bl || !var4[var10].isEmpty()) && (i & 1 << var10) != 0) {
                byte[] var9 = var4[var10].getBlockLSBArray();
                System.arraycopy(var9, 0, var7, var3, var9.length);
                var3 += var9.length;
            }
        }

        for (int var11 = 0; var11 < var4.length; ++var11) {
            if (var4[var11] != null && (!bl || !var4[var11].isEmpty()) && (i & 1 << var11) != 0) {
                NibbleArray var16 = var4[var11].getMetadataArray();
                System.arraycopy(var16.data, 0, var7, var3, var16.data.length);
                var3 += var16.data.length;
            }
        }

        for (int var12 = 0; var12 < var4.length; ++var12) {
            if (var4[var12] != null && (!bl || !var4[var12].isEmpty()) && (i & 1 << var12) != 0) {
                NibbleArray var17 = var4[var12].getBlocklightArray();
                System.arraycopy(var17.data, 0, var7, var3, var17.data.length);
                var3 += var17.data.length;
            }
        }

        if (!chunk.worldObj.provider.hasNoSky) {
            for (int var13 = 0; var13 < var4.length; ++var13) {
                if (var4[var13] != null && (!bl || !var4[var13].isEmpty()) && (i & 1 << var13) != 0) {
                    NibbleArray var18 = var4[var13].getSkylightArray();
                    System.arraycopy(var18.data, 0, var7, var3, var18.data.length);
                    var3 += var18.data.length;
                }
            }
        }

        if (var5 > 0) {
            for (int var14 = 0; var14 < var4.length; ++var14) {
                if (var4[var14] != null && (!bl || !var4[var14].isEmpty()) && var4[var14].getBlockMSBArray() != null && (i & 1 << var14) != 0) {
                    NibbleArray var19 = var4[var14].getBlockMSBArray();
                    System.arraycopy(var19.data, 0, var7, var3, var19.data.length);
                    var3 += var19.data.length;
                }
            }
        }

        //EDIT
        HunkArray var10_2;
        for (int var8 = 0; var8 < var4.length; ++var8)
        {
            if (var4[var8] != null && (!bl || !var4[var8].isEmpty()) && (i & 1 << var8) != 0)
            {
                var10_2 = ((ExtendedBlockStorageExtension) var4[var8]).getExtraMetadataArray();
                var3 = copyArrayLong2Byte(var10_2.data, 0, var7, var3, var10_2.data.length);
            }
        }

        if (bl) {
            byte[] var15 = chunk.getBiomeArray();
            System.arraycopy(var15, 0, var7, var3, var15.length);
            var3 += var15.length;
        }

        var6.compressedData = new byte[var3];
        System.arraycopy(var7, 0, var6.compressedData, 0, var3);
        return var6;
    }

}
