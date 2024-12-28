package btw.community.arminias.metadata.mixin;

import btw.community.arminias.metadata.HunkArray;
import btw.community.arminias.metadata.extension.ExtendedBlockStorageExtension;
import btw.community.arminias.metadata.extension.NBTTagCompoundExtension;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AnvilChunkLoader.class)
public class AnvilChunkLoaderMixin {

    @Group(name = "writeChunkToNBT", min = 1, max = 1)
    @Inject(method = "writeChunkToNBT", at = @At(value = "INVOKE", target = "Lnet/minecraft/NBTTagList;appendTag(Lnet/minecraft/NBTBase;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILSOFT, require = 0)
    //private void writeChunkToNBT(Chunk par1Chunk, World par2World, NBTTagCompound par3NBTTagCompound, CallbackInfo ci, ExtendedBlockStorage[] var4, NBTTagList var5, int var6, ExtendedBlockStorage[] var7, int var8, int var9, ExtendedBlockStorage var10, NBTTagCompound var11) {
    private void writeChunkToNBT(Chunk par1Chunk, World par2World, NBTTagCompound par3NBTTagCompound, CallbackInfo ci, ExtendedBlockStorage[] var4, NBTTagList var5, boolean var6, ExtendedBlockStorage[] var7, int var8, NBTTagCompound var11, int var9, ExtendedBlockStorage var10) {
        ((NBTTagCompoundExtension) var11).setLongArray("ExtraData", ((ExtendedBlockStorageExtension) var10).getExtraMetadataArray().data);
    }

    @Group(name = "writeChunkToNBT", min = 1, max = 1)
    @Inject(method = "writeChunkToNBT", at = @At(value = "INVOKE", target = "Lnet/minecraft/NBTTagList;appendTag(Lnet/minecraft/NBTBase;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILSOFT, require = 0)
    private void writeChunkToNBT(Chunk par1Chunk, World par2World, NBTTagCompound par3NBTTagCompound, CallbackInfo ci, ExtendedBlockStorage[] var4, NBTTagList var5, int var6, ExtendedBlockStorage[] var7, int var8, NBTTagCompound var11, int var9, ExtendedBlockStorage var10) {
        ((NBTTagCompoundExtension) var11).setLongArray("ExtraData", ((ExtendedBlockStorageExtension) var10).getExtraMetadataArray().data);
    }

    @Group(name = "readChunkFromNBT", min = 1, max = 1)
    @Inject(method = "readChunkFromNBT", at = @At(value = "INVOKE", target = "Lnet/minecraft/ExtendedBlockStorage;setBlocklightArray(Lnet/minecraft/NibbleArray;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILSOFT, require = 0)
    //private void readChunkFromNBT(World par1World, NBTTagCompound par2NBTTagCompound, CallbackInfoReturnable<Chunk> cir, int var3, int var4, Chunk var5, NBTTagList var6, int var7, ExtendedBlockStorage[] var8, int var9, int var10, NBTTagCompound var11, int var12, ExtendedBlockStorage var13) {
    private void readChunkFromNBT(World par1World, NBTTagCompound par2NBTTagCompound, CallbackInfoReturnable<Chunk> cir, int var3, int var4, Chunk var5, NBTTagList var6, byte var7, ExtendedBlockStorage[] var8, boolean var9, int var10, NBTTagCompound var11, byte var12, ExtendedBlockStorage var13) {
        ((ExtendedBlockStorageExtension) var13).setBlockExtraMetadataArray(new HunkArray(((NBTTagCompoundExtension) var11).getLongArray("ExtraData"), 4));
    }

    @Group(name = "readChunkFromNBT", min = 1, max = 1)
    @Inject(method = "readChunkFromNBT", at = @At(value = "INVOKE", target = "Lnet/minecraft/ExtendedBlockStorage;setBlocklightArray(Lnet/minecraft/NibbleArray;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILSOFT, require = 0)
    private void readChunkFromNBT(World par1World, NBTTagCompound par2NBTTagCompound, CallbackInfoReturnable<Chunk> cir, int var3, int var4, Chunk var5, NBTTagList var6, int var7, ExtendedBlockStorage[] var8, int var9, int var10, NBTTagCompound var11, int var12, ExtendedBlockStorage var13) {
        ((ExtendedBlockStorageExtension) var13).setBlockExtraMetadataArray(new HunkArray(((NBTTagCompoundExtension) var11).getLongArray("ExtraData"), 4));
    }
}
