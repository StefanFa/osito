/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2007-2009 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the license at LICENSE.html or
 * http://www.sun.com/cddl.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this License Header
 * Notice in each file.
 *
 * If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s): Alexandre (Shura) Iline. (shurymury@gmail.com)
 *
 * The Original Software is the Jemmy library.
 * The Initial Developer of the Original Software is Alexandre Iline.
 * All Rights Reserved.
 *
 */

package org.jemmy.interfaces;

import org.jemmy.dock.Shortcut;
import org.jemmy.env.Timeout;


/**
 * Defines how to simulate keyboard operations.
 */
public interface Keyboard extends ControlInterface {

    /**
     *
     */
    public static final Timeout PUSH = new Timeout("keyboard.push", 100);

    /**
     *
     * @param key
     * @param modifiers
     */
    @Shortcut
    public void pressKey(KeyboardButton key, Modifier... modifiers);
    /**
     *
     * @param key
     */
    @Shortcut
    public void pressKey(KeyboardButton key);

    /**
     *
     * @param key
     * @param modifiers
     */
    @Shortcut
    public void releaseKey(KeyboardButton key, Modifier... modifiers);
    /**
     *
     * @param key
     */
    @Shortcut
    public void releaseKey(KeyboardButton key);

    /**
     *
     * @param key
     * @param modifiers
     * @param pushTime
     */
    @Shortcut
    public void pushKey(Timeout pushTime, KeyboardButton key, Modifier... modifiers);
    /**
     *
     * @param key
     * @param modifiers
     */
    @Shortcut
    public void pushKey(KeyboardButton key, Modifier... modifiers);
    /**
     *
     * @param key
     */
    @Shortcut
    public void pushKey(KeyboardButton key);

    /**
     *
     * @param keyChar
     * @param pushTime
     */
    @Shortcut
    public void typeChar(char keyChar, Timeout pushTime);
    /**
     *
     * @param keyChar
     */
    @Shortcut
    public void typeChar(char keyChar);

    /**
     * Detaches the implementation so that all actions of it will be ran detached.
     * @see org.jemmy.action.ActionExecutor#executeDetached(org.jemmy.env.Environment, org.jemmy.action.Action, java.lang.Object[])
     * @return
     */
    public Keyboard detached();

    /**
     * Keyboard button interface (i. e. Q, W, ENTER, LEFT, F1, etc.)
     * created to left the possibility for extention as enums can't be extended
     */
    public static interface KeyboardButton extends Button {

    }

    /**
     * Keyboard modifier interface (i. e. SHIFT_DOWN_MASK)
     * created to left the possibility for extention as enums can't be extended
     */
    public static interface KeyboardModifier extends Modifier {

    }

    /**
     * Keyboard modifiers enum (i. e. SHIFT_DOWN_MASK)
     * to be used in Keyboard interface methods
     */
    public static enum KeyboardModifiers implements KeyboardModifier {
        /**
         * 
         */
        SHIFT_DOWN_MASK,
        /**
         *
         */
        CTRL_DOWN_MASK,
        /**
         * 
         */
        ALT_DOWN_MASK,
        /**
         *
         */
        META_DOWN_MASK
    }

    /**
     * Keyboard buttons enum (i. e. Q, W, ENTER, LEFT, F1, etc.)
     * to be used in Keyboard interface methods
     */
    public static enum KeyboardButtons implements KeyboardButton {

        /**
         *
         */
        ESCAPE,
        /**
         * 
         */
        F1,
        /**
         *
         */
        F2,
        /**
         * 
         */
        F3,
        /**
         *
         */
        F4,
        /**
         * 
         */
        F5,
        /**
         *
         */
        F6,
        /**
         * 
         */
        F7,
        /**
         *
         */
        F8,
        /**
         * 
         */
        F9,
        /**
         *
         */
        F10,
        /**
         * 
         */
        F11,
        /**
         *
         */
        F12,
        /**
         * 
         */
        F13,
        /**
         *
         */
        PRINTSCREEN,
        /**
         * 
         */
        SCROLL_LOCK,
        /**
         *
         */
        PAUSE,
        /**
         * 
         */
        BACK_QUOTE,
        /**
         *
         */
        D1,
        /**
         * 
         */
        D2,
        /**
         *
         */
        D3,
        /**
         * 
         */
        D4,
        /**
         *
         */
        D5,
        /**
         * 
         */
        D6,
        /**
         *
         */
        D7,
        /**
         * 
         */
        D8,
        /**
         *
         */
        D9,
        /**
         * 
         */
        D0,
        /**
         *
         */
        MINUS,
        /**
         * 
         */
        EQUALS,
        /**
         *
         */
        BACK_SLASH,
        /**
         * 
         */
        BACK_SPACE,
        /**
         *
         */
        INSERT,
        /**
         * 
         */
        HOME,
        /**
         *
         */
        PAGE_UP,
        /**
         * 
         */
        NUM_LOCK,
        /**
         *
         */
        DIVIDE,
        /**
         * 
         */
        MULTIPLY,
        /**
         *
         */
        SUBTRACT,
        /**
         * 
         */
        TAB,
        /**
         *
         */
        Q,
        /**
         * 
         */
        W,
        /**
         *
         */
        E,
        /**
         * 
         */
        R,
        /**
         *
         */
        T,
        /**
         * 
         */
        Y,
        /**
         *
         */
        U,
        /**
         * 
         */
        I,
        /**
         *
         */
        O,
        /**
         * 
         */
        P,
        /**
         *
         */
        OPEN_BRACKET,
        /**
         * 
         */
        CLOSE_BRACKET,
        /**
         *
         */
        DELETE,
        /**
         * 
         */
        END,
        /**
         *
         */
        PAGE_DOWN,
        /**
         * 
         */
        NUMPAD7,
        /**
         *
         */
        NUMPAD8,
        /**
         * 
         */
        NUMPAD9,
        /**
         *
         */
        ADD,
        /**
         * 
         */
        CAPS_LOCK,
        /**
         *
         */
        A,
        /**
         * 
         */
        S,
        /**
         *
         */
        D,
        /**
         * 
         */
        F,
        /**
         *
         */
        G,
        /**
         * 
         */
        H,
        /**
         *
         */
        J,
        /**
         * 
         */
        K,
        /**
         *
         */
        L,
        /**
         * 
         */
        SEMICOLON,
        /**
         *
         */
        QUOTE,
        /**
         * 
         */
        ENTER,
        /**
         *
         */
        NUMPAD4,
        /**
         * 
         */
        NUMPAD5,
        /**
         *
         */
        NUMPAD6,
        /**
         *
         */
        SHIFT,
        /**
         * 
         */
        Z,
        /**
         *
         */
        X,
        /**
         * 
         */
        C,
        /**
         *
         */
        V,
        /**
         * 
         */
        B,
        /**
         *
         */
        N,
        /**
         * 
         */
        M,
        /**
         *
         */
        COMMA,
        /**
         * 
         */
        PERIOD,
        /**
         *
         */
        SLASH,
        /**
         * 
         */
        UP,
        /**
         *
         */
        NUMPAD1,
        /**
         * 
         */
        NUMPAD2,
        /**
         *
         */
        NUMPAD3,
        /**
         * 
         */
        CONTROL,
        /**
         *
         */
        WINDOWS,
        /**
         * 
         */
        ALT,
        /**
         * Represents meta key on some platforms (eg Command key on MacOS X)
         */
        META,
        /**
         *
         */
        SPACE,
        /**
         * 
         */
        CONTEXT_MENU,
        /**
         *
         */
        LEFT,
        /**
         * 
         */
        DOWN,
        /**
         *
         */
        RIGHT,
        /**
         * 
         */
        NUMPAD0,
        /**
         *
         */
        DECIMAL
    }

}
