/*
 * Copyright 2013 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.world.block.shapes;

import org.terasology.physics.shapes.CollisionShape;
import com.google.common.collect.Maps;
import org.terasology.assets.AssetData;
import org.terasology.math.Side;
import org.terasology.math.geom.Vector3f;
import org.terasology.utilities.collection.EnumBooleanMap;
import org.terasology.world.block.BlockPart;
import org.terasology.world.block.BlockSection;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

/**
 */
public class BlockShapeData implements AssetData {
    private String displayName = "";
    public HashMap<String, BlockMeshPart> meshParts = new HashMap<>();
    private EnumMap<Side, List<String>> meshBySide = new EnumMap<>(Side.class);
    private EnumBooleanMap<Side> fullSide = new EnumBooleanMap<>(Side.class);
    private CollisionShape collisionShape;
    private Vector3f collisionOffset = new Vector3f();
    private boolean yawSymmetric;
    private boolean pitchSymmetric;
    private boolean rollSymmetric;

    public String getDisplayName() {
        if (displayName == null) {
            return "";
        }
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getMeshParts(Side side) {
        return meshBySide.get(side);
    }

    /**
     * Adds the given mesh to the lists of faces to render at the proper sides
     *
     * @param mesh
     */
    public void setMeshPart(BlockMeshPart mesh, String name, byte key) {
        List<Side> sides = BlockSection.fromKey(key);
        for (Side side : sides) {
            meshBySide.computeIfAbsent(side, (v) -> new ArrayList<>()).add(name);
        }
        meshParts.put(name, mesh);
    }

    public boolean isBlockingSide(Side side) {
        return fullSide.get(side);
    }

    /**
     * Sets whether the given side blocks the view of adjacent tiles (that is, it fills the side)
     *
     * @param side
     * @param blocking
     */
    public void setBlockingSide(Side side, boolean blocking) {
        fullSide.put(side, blocking);
    }

    public Vector3f getCollisionOffset() {
        return collisionOffset;
    }

    public void setCollisionOffset(Vector3f offset) {
        collisionOffset.set(offset);
    }

    public CollisionShape getCollisionShape() {
        return collisionShape;
    }

    public void setCollisionShape(CollisionShape shape) {
        collisionShape = shape;
    }

    public void setCollisionSymmetric(boolean collisionSymmetric) {
        yawSymmetric = collisionSymmetric;
        pitchSymmetric = collisionSymmetric;
        rollSymmetric = collisionSymmetric;
    }

    public boolean isRollSymmetric() {
        return rollSymmetric;
    }

    public boolean isPitchSymmetric() {
        return pitchSymmetric;
    }

    public boolean isYawSymmetric() {
        return yawSymmetric;
    }

    public void setYawSymmetric(boolean yawSymmetric) {
        this.yawSymmetric = yawSymmetric;
    }

    public void setPitchSymmetric(boolean pitchSymmetric) {
        this.pitchSymmetric = pitchSymmetric;
    }

    public void setRollSymmetric(boolean rollSymmetric) {
        this.rollSymmetric = rollSymmetric;
    }
}
