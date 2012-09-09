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
 * Contributor(s): Alexandre (Shura) Iline. (shurymury@gmail.com),
 *                 Alexander (KAM) Kouznetsov <mrkam@mail.ru>.
 *
 * The Original Software is the Jemmy library.
 * The Initial Developer of the Original Software is Alexandre Iline.
 * All Rights Reserved.
 *
 */

package org.jemmy.image;


import java.awt.image.BufferedImage;


/**
 * Common part of most ImageComparators.
 *
 * @author KAM
 * @deprecated Use classes from org.jemmy.image.pixel package instead.
 */
@Deprecated
public abstract class AbstractImageComparator implements ImageComparator {

    /**
     * Checks whether images have difference.
     * @param image1 First image to compare.
     * @param image2 Second image to compare.
     * @return true if images have no difference, false otherwise.
     */
    public abstract boolean noDifference(BufferedImage image1, BufferedImage image2);

    /**
     * {@inheritDoc}
     */
    public BufferedImage compare(BufferedImage image1, BufferedImage image2) {
        if (noDifference(image1, image2)) {
            return null;
        } else {
            return ImageTool.subtractImage(image1, image2);
        }
    }
}
