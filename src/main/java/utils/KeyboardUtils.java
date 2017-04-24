package utils;

import java.awt.event.KeyEvent;

public class KeyboardUtils {

    public static boolean isKeyEnter(Integer key) {
        return key == KeyEvent.VK_ENTER;
    }

    public static boolean isBackSpace(Integer key) {
        return key == KeyEvent.VK_BACK_SPACE;
    }

}
