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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.urban.data.core.query.json.JQuery;

/**
 *
 * @author heiko
 */
public class CSVFileWriter implements JsonDocumentHandler {

    private final CSVPrinter _out;
    private final List<String> _schema;
    
    public CSVFileWriter(CSVPrinter out, List<String> schema) throws java.io.IOException {
	
	_out = out;
	_schema = schema;
	
        _out.printRecord(schema);
    }
    
    @Override
    public void consume(JsonObject doc) throws java.io.IOException {

	ArrayList<String> values = new ArrayList<>();
	for (String column : _schema) {
	    JsonElement val = new JQuery(column).eval(doc);
	    if (val != null) {
                values.add(StringEscapeUtils.escapeJava(val.getAsString()).trim());
            } else {
                values.add("");
            }
	}
	
        _out.printRecord(values);
    }
}
