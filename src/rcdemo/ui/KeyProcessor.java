/*
 * Copyright (C) 2016 ezander
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package rcdemo.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ezander
 */
public class KeyProcessor implements KeyListener {

    static class KeyEventMap {

        Map<Character, KeyEventFunction> charToFunction = new HashMap<>();
        Map<Integer, KeyEventFunction> keycodeToFunction = new HashMap<>();

        void add(char c, KeyEventFunction func) {
            if (func != null) {
                charToFunction.put(c, func);
            }
        }

        void add(int i, KeyEventFunction func) {
            if (func != null) {
                keycodeToFunction.put(i, func);
            }
        }

        void processEvent(KeyEvent e) {
            KeyEventFunction function = null;
            if (keycodeToFunction.containsKey(e.getKeyCode())) {
                function = keycodeToFunction.get(e.getKeyCode());
            } else if (charToFunction.containsKey(e.getKeyChar())) {
                function = charToFunction.get(e.getKeyChar());
            }
            if (function != null) {
                function.process(e);
            }
        }
    }
    KeyProcessor.KeyEventMap typedKeyFunctions = new KeyProcessor.KeyEventMap();
    KeyProcessor.KeyEventMap pressedKeyFunctions = new KeyProcessor.KeyEventMap();
    KeyProcessor.KeyEventMap releasedKeyFunctions = new KeyProcessor.KeyEventMap();

    public KeyProcessor add(char c, KeyEventFunction typedFunc) {
        typedKeyFunctions.add(c, typedFunc);
        return this;
    }

//    public KeyProcessor add(char c, KeyEventFunction pressedFunc, KeyEventFunction releasedFunc) {
//        return add(c, null, pressedFunc, releasedFunc);
//    }
//
//    public KeyProcessor add(char c, KeyEventFunction typedFunc, KeyEventFunction pressedFunc, KeyEventFunction releasedFunc) {
//        typedKeyFunctions.add(c, typedFunc);
//        pressedKeyFunctions.add(c, pressedFunc);
//        releasedKeyFunctions.add(c, releasedFunc);
//        return this;
//    }

//    public KeyProcessor add(int i, KeyEventFunction typedFunc) {
//        return add(i, typedFunc, null, null);
//    }

    public KeyProcessor add(int i, KeyEventFunction pressedFunc, KeyEventFunction releasedFunc) {
        pressedKeyFunctions.add(i, pressedFunc);
        releasedKeyFunctions.add(i, releasedFunc);
        return this;
    }

//    public KeyProcessor add(int i, KeyEventFunction typedFunc, KeyEventFunction pressedFunc, KeyEventFunction releasedFunc) {
//        typedKeyFunctions.add(i, typedFunc);
//        pressedKeyFunctions.add(i, pressedFunc);
//        releasedKeyFunctions.add(i, releasedFunc);
//        return this;
//    }

    @Override
    public void keyTyped(KeyEvent e) {
        typedKeyFunctions.processEvent(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeyFunctions.processEvent(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        releasedKeyFunctions.processEvent(e);
    }
    
}
