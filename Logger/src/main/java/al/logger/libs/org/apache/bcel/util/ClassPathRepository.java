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

import java.util.HashMap;
import java.util.Map;

import al.logger.libs.org.apache.bcel.classfile.JavaClass;

/**
 * This repository is used in situations where a Class is created outside the realm of a ClassLoader. Classes are loaded
 * from the file systems using the paths specified in the given class path. By default, this is the value returned by
 * ClassPath.getClassPath().
 *
 * @see al.logger.libs.org.apache.bcel.Repository
 */
public class ClassPathRepository extends AbstractClassPathRepository {

    private final Map<String, JavaClass> loadedClasses = new HashMap<>(); // CLASSNAME X JAVACLASS

    public ClassPathRepository(final ClassPath classPath) {
        super(classPath);
    }

    /**
     * Clears all entries from cache.
     */
    @Override
    public void clear() {
        loadedClasses.clear();
    }

    /**
     * Finds an already defined (cached) JavaClass object by name.
     */
    @Override
    public JavaClass findClass(final String className) {
        return loadedClasses.get(className);
    }

    /**
     * Removes class from repository.
     */
    @Override
    public void removeClass(final JavaClass javaClass) {
        loadedClasses.remove(javaClass.getClassName());
    }

    /**
     * Stores a new JavaClass instance into this Repository.
     */
    @Override
    public void storeClass(final JavaClass javaClass) {
        loadedClasses.put(javaClass.getClassName(), javaClass);
        javaClass.setRepository(this);
    }
}
