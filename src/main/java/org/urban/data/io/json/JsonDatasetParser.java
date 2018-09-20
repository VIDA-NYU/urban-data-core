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

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses a Socrata dataset. Expects the dataset to be an array of JSON objects.
 * The objects are passed to a {@link JsonDocumentHandler} for processing.
 * 
 * @author Heiko Mueller
 */
public class JsonDatasetParser {
    
    public int parse(InputStream in, JsonDocumentHandler handler) throws java.io.IOException {
	
	int count = 0;
	
	try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
	    reader.beginArray();
	    while (reader.hasNext()) {
		handler.consume(new JsonParser().parse(reader).getAsJsonObject());
		count++;
	    }
	    reader.endArray();
	} catch (com.google.gson.stream.MalformedJsonException malformedJSON) {
	    count = -1;
	    Logger.getLogger(JsonDatasetParser.class.getName()).log(Level.SEVERE, null, malformedJSON);
        }
	
	return count;
    }
}
