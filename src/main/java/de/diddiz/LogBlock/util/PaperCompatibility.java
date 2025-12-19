package de.diddiz.LogBlock.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.block.data.type.Shelf;

public class PaperCompatibility {
    private static final Method method_Shelf_getSideChain;
    static {
        try {
            method_Shelf_getSideChain = Shelf.class.getMethod("getSideChain");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("method missing: Shelf.getSideChain", e);
        }
    }

    public static enum SideChain {
        LEFT,
        CENTER,
        RIGHT,
        UNCONNECTED;
    }

    public static SideChain getShelfSideChain(Shelf shelf) {
        try {
            return SideChain.valueOf(method_Shelf_getSideChain.invoke(shelf).toString());
        } catch (IllegalAccessException | InvocationTargetException | NullPointerException e) {
            throw new RuntimeException("could not invoke: Shelf.getSideChain", e);
        }
    }

}
