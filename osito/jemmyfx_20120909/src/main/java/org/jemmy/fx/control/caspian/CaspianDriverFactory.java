/*
 * Copyright (c) 2009, 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.jemmy.fx.control.caspian;

import com.sun.javafx.scene.control.skin.ScrollBarSkin;
import com.sun.javafx.scene.control.skin.SliderSkin;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import org.jemmy.action.Action;
import org.jemmy.control.Wrap;
import org.jemmy.fx.ByStyleClass;
import org.jemmy.fx.NodeWrap;
import org.jemmy.fx.control.ComboBoxWrap;
import org.jemmy.fx.control.MenuBarWrap;
import org.jemmy.fx.control.ThemeDriverFactory;
import org.jemmy.fx.control.TreeNodeWrap;
import org.jemmy.interfaces.Focus;
import org.jemmy.interfaces.Keyboard.KeyboardButtons;
import org.jemmy.interfaces.Parent;
import org.jemmy.interfaces.Scroll;
import org.jemmy.interfaces.Scroller;
import org.jemmy.interfaces.Shifter;
import org.jemmy.timing.State;

/**
 * Defines control behaviour for Caspian theme.
 * 
 * Ported from the project JemmyFX, original class 
 * is   org.jemmy.fx.control.caspian.CaspianDriverFactory
 * 
 * @author shura
 */
public class CaspianDriverFactory extends ThemeDriverFactory {

    private float dragDelta;

    /**
     *
     * @param dragDelta
     */
    public CaspianDriverFactory(float dragDelta) {
        this.dragDelta = dragDelta;
    }

    /**
     *
     */
    public CaspianDriverFactory() {
        this(1);
    }

    /**
     *
     * @return
     */
    public float getDragDelta() {
        return dragDelta;
    }

    /**
     *
     * @param dragDelta
     */
    public void setDragDelta(float dragDelta) {
        this.dragDelta = dragDelta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scroller caret(Wrap<? extends Control> wrap, Scroll scroll) {
        KnobTrackScrollerImpl res = createScrollerImpl(wrap, scroll);
        res.setDragDelta(dragDelta);
        return res;
    }

    @Override
    public Shifter track(final Wrap<? extends Control> wrap, final Scroll scroll) {
        KnobTrackScrollerImpl sls = createScrollerImpl(wrap, scroll);
        return sls.getTrack();
    }

    private KnobTrackScrollerImpl createScrollerImpl(Wrap<? extends Control> wrap, Scroll scroll) {

        if (wrap.getControl() instanceof ScrollBar) {
            return new ScrollBarScroller((Wrap<? extends ScrollBar>) wrap, scroll, ScrollBarSkin.class);
        }
        if (wrap.getControl() instanceof Slider) {
            return new SliderScroller((Wrap<? extends Slider>) wrap, scroll, SliderSkin.class);
        }
        return null;
    }

    @Override
    public <T> org.jemmy.fx.control.caspian.TreeItem treeItem(Wrap<T> wrap) {
        if(wrap instanceof TreeNodeWrap) {
            TreeNodeWrap itemWrap = (TreeNodeWrap) wrap;
            return new org.jemmy.fx.control.caspian.TreeItem(itemWrap);
        }
        return null;
    }

    @Override
    public Focus menuBarFocuser(final MenuBarWrap<? extends MenuBar> menuBarWrap) {
        if (isMacOS()) {
            return new NodeFocus(menuBarWrap) {
                @Override
                protected void activate() {
                    // temporary solution due to bug in MacOS implementation of MenuBar
                    Action requestFocus = new Action() {
                        @Override
                        public void run(Object... parameters) throws Exception {
                            menuBarWrap.getControl().requestFocus();
                        }
                    };
                    menuBarWrap.getEnvironment().getExecutor().execute(menuBarWrap.getEnvironment(), true, requestFocus);
                }
            };
        } else {
            return new NodeFocus(menuBarWrap) {
                @Override
                protected void activate() {
                    // pressKey()/releaseKey() are used to prevent an attempt to get focus in pushKey()
                    menuBarWrap.keyboard().pressKey(KeyboardButtons.F10);
                    menuBarWrap.getEnvironment().getTimeout(menuBarWrap.keyboard().PUSH.getName());
                    menuBarWrap.keyboard().releaseKey(KeyboardButtons.F10);
                }
            };
        }
    }

    @Override
    public Focus comboBoxFocuser(final ComboBoxWrap<? extends ComboBox> comboBoxWrap) {
        return new NodeFocus(comboBoxWrap) {
            @Override
            protected void activate() {
                comboBoxWrap.as(Parent.class, Node.class).lookup(new ByStyleClass<Node>("arrow-button")).wrap().mouse().click(comboBoxWrap.isShowing() ? 1 : 2);
            }
        };
    }

    abstract class NodeFocus implements Focus {
        NodeWrap nodeWrap;
        public NodeFocus(NodeWrap nodeWrap) {
            this.nodeWrap = nodeWrap;
        }
        public void focus() {
            if (!nodeWrap.isFocused()) {
                activate();
            }
            nodeWrap.waitState(new State<Boolean>() {
                public Boolean reached() {
                    return nodeWrap.isFocused();
                }
            }, true);
        }
        abstract protected void activate();
    }

    private static boolean isMacOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("mac") >= 0) {
            return true;
        }
        return false;
    }
}
