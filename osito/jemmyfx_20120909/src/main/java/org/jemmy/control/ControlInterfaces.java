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

package org.jemmy.control;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jemmy.interfaces.ControlInterface;
import org.jemmy.interfaces.TypeControlInterface;

/**
 * To be applied on classes - ancestors of <code>Wrp</code>
 * class. 
 * @see Wrap
 * @author shura
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface ControlInterfaces {
    /**
     * List of interfaces supported by this wrap.
     * @see ControlInterface
     */
    Class<? extends ControlInterface>[] value();
    /**
     * List of encapsulated types for the <code>TypeControlInterface</code> 
     * interfaces listed in <code>value</code>.
     * Note that this list should be shorter that the <code>value</code> to not
     * provide anything for a <code>ControlInterface</code> which is not a  
     * <code>TypeControlInterface</code>
     * @see TypeControlInterface
     */
    Class[] encapsulates() default {};
    /**
     * This provides names for the dock methods which would be generated. If the array
     * does not have enough elements, the method would be named as <code>"as" + value[i].getName()</code>.
     */
    String[] name() default {};
}
