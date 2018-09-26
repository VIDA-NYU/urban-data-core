/*
 * Copyright 2018 New York University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.urban.data.core.io;

import java.io.BufferedReader;
import java.io.File;
import org.urban.data.core.object.Entity;
import org.urban.data.core.object.filter.AnyObjectFilter;
import org.urban.data.core.object.filter.ObjectFilter;
import org.urban.data.core.set.HashObjectSet;
import org.urban.data.core.set.IdentifiableObjectSet;

/**
 * Read a named object file as a stream. Assumes that every line contains at
 * least two tokens: object Id and name. The default file format is
 * tab-delimited
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class NamedObjectSetReader {
    
    public final static String DEFAULT_DELIMITER = "\t";
    
    private final String _delimiter;
    
    public NamedObjectSetReader(String delimiter) {
        
        _delimiter = delimiter;
    }
    
    public NamedObjectSetReader() {
        
        this(DEFAULT_DELIMITER);
    }
    
    public IdentifiableObjectSet<Entity> read(File file, ObjectFilter filter) throws java.io.IOException {
        
        HashObjectSet<Entity> result;
        result = new HashObjectSet<>();
        
        try (BufferedReader in = FileSystem.openReader(file)) {
	    String line;
	    while ((line = in.readLine()) != null) {
		String[] tokens = line.split(_delimiter);
                int objId = Integer.parseInt(tokens[0]);
                if (filter.contains(objId)) {
                    String name = tokens[1];
                    result.add(new Entity(objId, name));
                }
            }
        }
        
        return result;
    }

    public IdentifiableObjectSet<Entity> read(File file) throws java.io.IOException {
        
        return this.read(file, new AnyObjectFilter());
    }
 }
