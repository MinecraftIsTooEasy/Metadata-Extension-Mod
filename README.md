# Metadata-Extension-Addon
This addon adds additional metadata to all blocks, a whole 32 bits each. These can be accessed and set via the extension interfaces provided by this mod.
When the vanilla method `setBlock` is called, extra metadata is set to 0 as well. New methods for handling extra metadata are named intuitively.

## Example Usage
Extra metadata are 32 bits, a full integer.

To set a block's extra metadata, do for example: `WorldExtension.cast(world).setBlockExtraMetadata(...)`. Different notify options are also available, as with vanilla metadata.

To get a block's extra metadata, do for example: `WorldExtension.cast(world).getBlockExtraMetadata(...)`.

Ducktyping as in `((WorldExtension) world).getBlockExtraMetadata(...)` is also possible, but `cast` methods are provided for better readability.

## License
This project incorporates:
* A precompiled version of [Tiny Remapper](https://github.com/FabricMC/tiny-remapper) (LGPL-3.0)
