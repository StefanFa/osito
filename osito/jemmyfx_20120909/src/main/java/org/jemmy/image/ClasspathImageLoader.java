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

package org.jemmy.image;


import org.jemmy.env.Environment;


/**
 * ImageLoader implementation which is able to load images through
 * a given classloader.
 * @author mrkam, shura
 */
public class ClasspathImageLoader implements ImageLoader {

    private String packagePrefix = "";
    private ClassLoader classLoader = AWTImageFactory.class.getClassLoader();

    public static final String OUTPUT = AWTImage.class.getName() + ".OUTPUT";

    /**
     * Get the value of classLoader which is used to load images. By default
     * equal to class loader which loaded the ClasspathImageFactory class.
     *
     * @return the value of classLoader
     */
    public ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * {@inheritDoc}
     */
    public Image load(String ID) {
        String fullId = ((packagePrefix != null) ? packagePrefix : "") + ID;
        Environment.getEnvironment().getOutput(ClasspathImageLoader.OUTPUT).println("Image loaded from " + fullId + " by " + classLoader);
        return new AWTImage(PNGDecoder.decode(classLoader, fullId));
    }

    /**
     * Set the value of classLoader
     *
     * @param classLoader new value of classLoader
     */
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * TODO: Add JavaDoc
     * @param rootPackage
     */
    public void setRootPackage(Package rootPackage) {
        if (rootPackage != null) {
            this.packagePrefix = rootPackage.getName().replace('.', '/') + "/";
        } else {
            this.packagePrefix = null;
        }
    }

}
