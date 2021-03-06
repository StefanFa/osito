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
package org.jemmy.fx;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Window;
import org.jemmy.JemmyException;
import org.jemmy.Rectangle;
import org.jemmy.action.GetAction;
import org.jemmy.control.ControlInterfaces;
import org.jemmy.control.ControlType;
import org.jemmy.control.Wrap;
import org.jemmy.dock.DefaultParent;
import org.jemmy.dock.DefaultWrapper;
import org.jemmy.dock.ObjectLookup;
import org.jemmy.env.Environment;
import org.jemmy.interfaces.Parent;
import org.jemmy.interfaces.TypeControlInterface;
import org.jemmy.lookup.AbstractParent;
import org.jemmy.lookup.Any;
import org.jemmy.lookup.HierarchyLookup;
import org.jemmy.lookup.Lookup;
import org.jemmy.lookup.LookupCriteria;
import org.jemmy.resources.StringComparePolicy;

/**
 *
 * @author shura
 */
@ControlType(Scene.class)
@ControlInterfaces(value = Parent.class, encapsulates = Node.class)
public class SceneWrap<T extends Scene> extends Wrap<Scene> {

    @DefaultWrapper
    public static <TP extends Scene> Wrap<? extends TP> wrap(Environment env, Class<TP> type, TP control) {
        Wrap<? extends TP> res = Root.ROOT.getSceneWrapper().wrap(type, control);
        res.setEnvironment(env);
        return res;
    }

    @ObjectLookup("title and comparison policy")
    public static <B extends Scene> LookupCriteria<B> titleLookup(Class<B> tp, String title, StringComparePolicy policy) {
        return new ByTitleSceneLookup<B>(title, policy);
    }

    @ObjectLookup("")
    public static <B extends Scene> LookupCriteria<B> anyLookup(Class<B> tp) {
        return new Any<B>();
    }

    @DefaultParent("all scenes")
    public static <CONTROL extends Scene> Parent<? super Scene> getRoot(Class<CONTROL> controlType) {
        return Root.ROOT;
    }
    private Parent parent = null;

    public SceneWrap(Environment env, Scene node) {
        super(env, node);
    }

    @Override
    public Rectangle getScreenBounds() {
        return getScreenBounds(getEnvironment(), getControl());
    }

    public static Rectangle getScreenBounds(final Environment env, final Scene scene) {
        GetAction<Rectangle> bounds = new GetAction<Rectangle>() {

            @Override
            public void run(Object... parameters) {
                Rectangle sceneBounds = getSceneBounds(env, scene);
                Window window = scene.getWindow();
                if (Double.isNaN(window.getX())) {
                    throw new JemmyException("Unable to obtain containers's window coordinates in " + this.getClass().getName());
                }
                sceneBounds.translate((int) window.getX(), (int) window.getY());
                setResult(sceneBounds);
            }
        };
        env.getExecutor().execute(env, true, bounds);
        return bounds.getResult();
    }

    /**
     * Returns bounds of the Scene relative to Stage containing it
     */
    private static Rectangle getSceneBounds(Environment env, final Scene scene) {
        return new Rectangle(scene.getX(), scene.getY(),
                scene.getWidth(), scene.getHeight());
    }

    /**
     * Returns bounds of the Stage relative to screen
     */
    private static Rectangle getScreenBounds(Environment env, final Window window) {
        return new Rectangle(window.getX(), window.getY(),
                window.getWidth(), window.getHeight());
    }

    @Override
    public <TYPE, INTERFACE extends TypeControlInterface<TYPE>> boolean is(Class<INTERFACE> interfaceClass, Class<TYPE> type) {
        if (Parent.class.isAssignableFrom(interfaceClass) && Node.class.equals(type)) {
            return true;
        }
        return super.is(interfaceClass, type);
    }

    @Override
    public <TYPE, INTERFACE extends TypeControlInterface<TYPE>> INTERFACE as(Class<INTERFACE> interfaceClass, Class<TYPE> type) {
        if (Parent.class.isAssignableFrom(interfaceClass) && Node.class.equals(type)) {
            if (parent == null) {
                parent = new NodeParentImpl(new SceneNodeHierarchy(getControl(), getEnvironment()), getEnvironment());
            }
            return (INTERFACE) parent;
        }
        return super.as(interfaceClass, type);
    }
}
