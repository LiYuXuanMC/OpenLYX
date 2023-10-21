/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package al.logger.libs.org.apache.bcel.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

import al.logger.libs.org.apache.bcel.Const;
import al.logger.libs.org.apache.bcel.classfile.ClassParser;
import al.logger.libs.org.apache.bcel.classfile.ConstantClass;
import al.logger.libs.org.apache.bcel.classfile.ConstantPool;
import al.logger.libs.org.apache.bcel.classfile.ConstantUtf8;
import al.logger.libs.org.apache.bcel.classfile.JavaClass;
import al.logger.libs.org.apache.bcel.classfile.Utility;

/**
 * <p>
 * Drop in replacement for the standard class loader of the JVM. You can use it in conjunction with the JavaWrapper to
 * dynamically modify/create classes as they're requested.
 * </p>
 *
 * <p>
 * This class loader recognizes special requests in a distinct format, i.e., when the name of the requested class
 * contains with "$$BCEL$$" it calls the createClass() method with that name (everything bevor the $$BCEL$$ is
 * considered to be the package name. You can subclass the class loader and override that method. "Normal" classes class
 * can be modified by overriding the modifyClass() method which is called just before defineClass().
 * </p>
 *
 * <p>
 * There may be a number of packages where you have to use the default class loader (which may also be faster). You can
 * define the set of packages where to use the system class loader in the constructor. The default value contains
 * "java.", "sun.", "javax."
 * </p>
 *
 * @see JavaWrapper
 * @see ClassPath
 * @deprecated 6.0 Do not use - does not work
 */
@Deprecated
public class ClassLoader extends java.lang.ClassLoader {

    private static final String BCEL_TOKEN = "$$BCEL$$";

    public static final String[] DEFAULT_IGNORED_PACKAGES = {"java.", "javax.", "sun."};

    private final Hashtable<String, Class<?>> classes = new Hashtable<>();
    // Hashtable is synchronized thus thread-safe
    private final String[] ignoredPackages;
    private Repository repository = SyntheticRepository.getInstance();

    /**
     * Ignored packages are by default ( "java.", "sun.", "javax."), i.e. loaded by system class loader
     */
    public ClassLoader() {
        this(DEFAULT_IGNORED_PACKAGES);
    }

    /**
     * @param deferTo delegate class loader to use for ignored packages
     */
    public ClassLoader(final java.lang.ClassLoader deferTo) {
        super(deferTo);
        this.ignoredPackages = DEFAULT_IGNORED_PACKAGES;
        this.repository = new ClassLoaderRepository(deferTo);
    }

    /**
     * @param ignoredPackages classes contained in these packages will be loaded with the system class loader
     * @param deferTo delegate class loader to use for ignored packages
     */
    public ClassLoader(final java.lang.ClassLoader deferTo, final String[] ignoredPackages) {
        this(ignoredPackages);
        this.repository = new ClassLoaderRepository(deferTo);
    }

    /**
     * @param ignoredPackages classes contained in these packages will be loaded with the system class loader
     */
    public ClassLoader(final String[] ignoredPackages) {
        this.ignoredPackages = ignoredPackages;
    }

    /**
     * Override this method to create you own classes on the fly. The name contains the special token $$BCEL$$. Everything
     * before that token is considered to be a package name. You can encode your own arguments into the subsequent string.
     * You must ensure however not to use any "illegal" characters, i.e., characters that may not appear in a Java class
     * name too
     * <p>
     * The default implementation interprets the string as a encoded compressed Java class, unpacks and decodes it with the
     * Utility.decode() method, and parses the resulting byte array and returns the resulting JavaClass object.
     * </p>
     *
     * @param className compressed byte code with "$$BCEL$$" in it
     */
    protected JavaClass createClass(final String className) {
        final int index = className.indexOf(BCEL_TOKEN);
        final String realName = className.substring(index + BCEL_TOKEN.length());
        JavaClass clazz = null;
        try {
            final byte[] bytes = Utility.decode(realName, true);
            final ClassParser parser = new ClassParser(new ByteArrayInputStream(bytes), "foo");
            clazz = parser.parse();
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
        // Adapt the class name to the passed value
        final ConstantPool cp = clazz.getConstantPool();
        final ConstantClass cl = cp.getConstant(clazz.getClassNameIndex(), Const.CONSTANT_Class, ConstantClass.class);
        final ConstantUtf8 name = cp.getConstantUtf8(cl.getNameIndex());
        name.setBytes(Utility.packageToPath(className));
        return clazz;
    }

    @Override
    protected Class<?> loadClass(final String className, final boolean resolve) throws ClassNotFoundException {
        Class<?> cl = null;
        /*
         * First try: lookup hash table.
         */
        if ((cl = classes.get(className)) == null) {
            /*
             * Second try: Load system class using system class loader. You better don't mess around with them.
             */
            for (final String ignoredPackage : ignoredPackages) {
                if (className.startsWith(ignoredPackage)) {
                    cl = getParent().loadClass(className);
                    break;
                }
            }
            if (cl == null) {
                JavaClass clazz = null;
                /*
                 * Third try: Special request?
                 */
                if (className.contains(BCEL_TOKEN)) {
                    clazz = createClass(className);
                } else { // Fourth try: Load classes via repository
                    if ((clazz = repository.loadClass(className)) == null) {
                        throw new ClassNotFoundException(className);
                    }
                    clazz = modifyClass(clazz);
                }
                if (clazz != null) {
                    final byte[] bytes = clazz.getBytes();
                    cl = defineClass(className, bytes, 0, bytes.length);
                } else {
                    cl = Class.forName(className);
                }
            }
            if (resolve) {
                resolveClass(cl);
            }
        }
        classes.put(className, cl);
        return cl;
    }

    /**
     * Override this method if you want to alter a class before it gets actually loaded. Does nothing by default.
     */
    protected JavaClass modifyClass(final JavaClass clazz) {
        return clazz;
    }
}
