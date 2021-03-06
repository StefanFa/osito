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
package org.jemmy.fx.control;


import java.util.List;
import org.jemmy.control.Wrap;
import org.jemmy.control.Wrapper;
import org.jemmy.lookup.AbstractParent;
import org.jemmy.lookup.ControlList;
import org.jemmy.lookup.Lookup;
import org.jemmy.lookup.LookupCriteria;
import org.jemmy.lookup.PlainLookup;


public abstract class AbstractItemsParent<ITEM extends Object> extends AbstractParent<ITEM> {

    protected ItemsList itemsListCreator;
    protected Wrap wrap;
    protected Wrapper wrapper;
    protected Class<ITEM> itemClass;

    /**
     *
     * @param scene
     * @param listViewWrap
     * @param itemClass
     */
    public AbstractItemsParent(Wrap wrap, Wrapper wrapper, Class<ITEM> itemClass) {
        this.wrap = wrap;
        this.wrapper = wrapper;
        this.itemClass = itemClass;
        itemsListCreator = new ItemsList();
    }

    @Override
    public <ST extends ITEM> Lookup<ST> lookup(Class<ST> controlClass, LookupCriteria<ST> criteria) {
        return new PlainLookup<ST>(wrap.getEnvironment(),
                                   itemsListCreator, wrapper, controlClass, criteria);
    }

    @Override
    public Lookup<ITEM> lookup(LookupCriteria<ITEM> criteria) {
        return this.lookup(itemClass, criteria);
    }

    @Override
    public Class<ITEM> getType() {
        return itemClass;
    }

    protected class ItemsList implements ControlList {

        @Override
        @SuppressWarnings("unchecked")
        public List<ITEM> getControls() {
            return AbstractItemsParent.this.getControls();
        }
    }

    protected abstract List<ITEM> getControls();
}