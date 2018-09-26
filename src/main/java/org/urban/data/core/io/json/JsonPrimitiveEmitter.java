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
package org.urban.data.core.io.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;

/**
 *
 * @author heiko
 */
public class JsonPrimitiveEmitter implements JsonDocumentHandler {

    private final JsonPrimitiveConsumer _consumer;
    private final String _pathDelimiter;
    
    public JsonPrimitiveEmitter(String pathDelimiter, JsonPrimitiveConsumer consumer) {
	
	_pathDelimiter = pathDelimiter;
	_consumer = consumer;
	
    }
    
    public JsonPrimitiveEmitter(JsonPrimitiveConsumer consumer) {
	
	this("/", consumer);
    }
    
    /**
     * Check if the element is a primitive and emit it to the consumer. For
     * all other element types continue recursive traversal.
     * 
     * @param path
     * @param label
     * @param element 
     */
    private void emit(String path, String label, JsonElement element) {

	String elementPath = path + _pathDelimiter + label;
	
	if (element.isJsonObject()) {
	    this.traverse(elementPath, element.getAsJsonObject());
	} else if (element.isJsonArray()) {
	    JsonArray array = element.getAsJsonArray();
	    for (int iElement = 0; iElement < array.size(); iElement++) {
		JsonElement entry = array.get(iElement);
		if (entry.isJsonObject()) {
		    this.traverse(elementPath, entry.getAsJsonObject());
		} else if (entry.isJsonArray()) {
		    this.emit(elementPath, Integer.toString(iElement), entry);
		} else {
		    _consumer.consume(elementPath.substring(1), element.getAsJsonPrimitive());
		}
	    }
	} else {
	    _consumer.consume(elementPath.substring(1), element.getAsJsonPrimitive());
	}
    }

    @Override
    public void consume(JsonObject doc) {

	this.traverse("", doc);
    }
    
    /**
     * Traverse the object recursively and emit JSON primitives to the
     * consumer.
     * 
     * @param path
     * @param object 
     */
    private void traverse(String path, JsonObject object) {
	
	for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
	    if (entry.getValue().isJsonArray()) {
		JsonArray array = entry.getValue().getAsJsonArray();
		for (int iElement = 0; iElement < array.size(); iElement++) {
		    JsonElement element = array.get(iElement);
		    if (element.isJsonArray()) {
			this.emit(path + _pathDelimiter + entry.getKey(), Integer.toString(iElement), element);
		    } else {
			this.emit(path + _pathDelimiter + entry.getKey(), Integer.toString(iElement), element);
		    }
		}
	    } else {
		this.emit(path, entry.getKey(), entry.getValue());
	    }
	}
    }
}
