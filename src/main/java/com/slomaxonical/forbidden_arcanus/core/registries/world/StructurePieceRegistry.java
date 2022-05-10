package com.slomaxonical.forbidden_arcanus.core.registries.world;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.worldgen.structure.NipaPieces;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StructurePieceRegistry {
    public static final StructurePieceType NIPA = create("nipa", NipaPieces.Piece::new);

    static StructurePieceType create(String name, StructurePieceType.ManagerAware type) {
        return Registry.register(Registry.STRUCTURE_PIECE, new Identifier(ForbiddenArcanus.MOD_ID, name), type);
    }
    public static void register(){}
}
