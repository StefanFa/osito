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

package org.jemmy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 *
 * @author shura
 */
public class Version {
    /**
     *
     */
    public static final Version VERSION = new Version();

    private int major;
    private int minor;
    private int mini;
    private String build;

    /**
     *
     */
    public Version() {
        this(Version.class.getPackage().getName());
    }

    /**
     *
     * @param pkg
     */
    public Version(String pkg) {
        try {
            Properties props = new Properties();
            String fileName = pkg.replace(".", "/") + "/jemmy.properties";
            InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
            if(in == null) {
                throw new JemmyException("Can not get version - no " + fileName + " file");
            }
            props.load(in);
            major = Integer.parseInt(props.getProperty("version.major"));
            minor = Integer.parseInt(props.getProperty("version.minor"));
            mini = Integer.parseInt(props.getProperty("version.mini"));
            build = props.getProperty("build");
        } catch (IOException ex) {
            throw new JemmyException("Can not get version.", ex);
        }
    }

    /**
     *
     * @return
     */
    public int getMajor() {
        return major;
    }

    /**
     * 
     * @return
     */
    public int getMini() {
        return mini;
    }

    /**
     *
     * @return
     */
    public int getMinor() {
        return minor;
    }

    /**
     *
     * @return
     */
    public String getVersion() {
        return major + "." + minor + "." + mini;
    }

    /**
     *
     * @return
     */
    public String getBuild() {
        return build;
    }

    /**
     *
     * @param old
     * @return
     */
    public boolean newer(String old) {
        StringTokenizer tn = new StringTokenizer(old, ".");
        if(major >= Integer.parseInt(tn.nextToken())) {
            if(minor >= Integer.parseInt(tn.nextToken())) {
                if(mini >= Integer.parseInt(tn.nextToken())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("JemmyCore version: " + VERSION.getVersion() + "." + VERSION.build);
    }
}
