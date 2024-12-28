package btw.community.arminias.metadata.extension;

import net.minecraft.Packet53BlockChange;

public interface Packet53BlockChangeExtension {
    int getExtraMetadata();

    static Packet53BlockChangeExtension cast(Packet53BlockChange packet) {
        return (Packet53BlockChangeExtension) packet;
    }
}
