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
package org.urban.data.io.json;

import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Traverses a document tree and creates a schema column for each leaf element.
 * 
 * @author Heiko Mueller
 */
public class JsonSchemaGenerator implements JsonPrimitiveConsumer {

    private final HashSet<String> _schema;
    
    public JsonSchemaGenerator() {
	
	_schema = new HashSet<>();
    }
    
    public List<String> columns() {
        
        return new ArrayList<>(_schema);
    }
    
    @Override
    public void consume(String path, JsonPrimitive element) {

	if (!_schema.contains(path)) {
	    _schema.add(path);
	}
    }
}
