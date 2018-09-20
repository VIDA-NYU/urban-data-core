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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Map;

/**
 * Simple class that creates a copy of a given JsonObject. Will try to parse any
 * text value as JSON. Is used to discover JSON encoded in string fields.
 * 
 * @author Heiko Mueller
 */
public class EscapedJsonParser implements JsonDocumentHandler {

    private final JsonDocumentHandler _handler;
    
    public EscapedJsonParser(JsonDocumentHandler handler) {
	
	_handler = handler;
    }
    
    public JsonObject expand(JsonObject doc) {
	
	JsonObject result = new JsonObject();
	
	for (Map.Entry<String, JsonElement> entry : doc.entrySet()) {
	    result.add(entry.getKey(), this.expandElement(entry.getValue()));
	}
	
	return result;
    }
    
    private JsonElement expandElement(JsonElement element) {
	
	if (element.isJsonArray()) {
	    JsonArray result = new JsonArray();
	    JsonArray array = element.getAsJsonArray();
	    for (int iElement = 0; iElement < array.size(); iElement++) {
		result.add(this.expandElement(array.get(iElement)));
	    }
	    return result;
	} else if (element.isJsonObject()) {
	    return this.expand(element.getAsJsonObject());
	} else {
	    String value = element.getAsJsonPrimitive().getAsString();
	    if ((value.startsWith("{")) && (value.endsWith("}"))) {
		try {
		    return this.expand(new JsonParser().parse(value).getAsJsonObject());
		} catch (Exception ex) {
		}
	    }
	    /*
	     * Expanding arrays causes issues with arrays of strings that
	     * generate relations that are not in first normalform.
	     *
		} else if ((value.startsWith("[")) && (value.endsWith("]"))) {
		try {
		    return this.expandElement(new JsonParser().parse(value).getAsJsonArray());
		} catch (Exception ex) {
		}
		}
	     *
	     */
	    return element;
	}
    }

    @Override
    public void consume(JsonObject doc) throws java.io.IOException {

	_handler.consume(this.expand(doc));
    }
}
