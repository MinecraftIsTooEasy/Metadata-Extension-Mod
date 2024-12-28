package btw.community.arminias.metadata.extension;

import net.minecraft.TileEntity;
import net.minecraft.TileEntityPiston;

public interface TileEntityPistonExtension {
    int getBlockExtraMetadata();
    TileEntity setExtraMetadata(int extraMeta);

    static TileEntityPistonExtension cast(TileEntity tileEntity) {
        return (TileEntityPistonExtension) tileEntity;
    }
}
