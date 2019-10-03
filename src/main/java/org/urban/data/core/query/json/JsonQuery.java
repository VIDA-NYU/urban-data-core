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
package org.urban.data.core.query.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.urban.data.core.io.FileSystem;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class JsonQuery {
    
    private final File _database;
    private final String _targetPath;
    
    public JsonQuery(File database, String targetPath) {
        
        _database = database;
        _targetPath = targetPath;
    }

    public JsonQuery(File database) {
        
        this(database, "/");
    }
    
    public List<ResultTuple> executeQuery(
            SelectClause select,
            boolean noNullValues
    ) throws java.io.IOException {

        ArrayList<String[]> result = new ArrayList<>();
               
        try (JsonReader reader = new JsonReader(
            new InputStreamReader(FileSystem.openFile(_database)))
        ) {
            if (_targetPath.equals("/")) {
                return this.filter(reader, select, noNullValues);
            } else {
                reader.beginObject();
                if (reader.nextName().equals(_targetPath)) {
                    return this.filter(reader, select, noNullValues);
                } else {
                    reader.skipValue();
                }
                reader.endObject();
                return new ArrayList<>();
            }
        }
    }

    public List<ResultTuple> executeQuery(SelectClause select) throws java.io.IOException {
        
        return this.executeQuery(select, false);
    }
    
    private List<ResultTuple> filter(
            JsonReader reader,
            SelectClause select,
            boolean noNullValues
    ) throws java.io.IOException {

        ArrayList<ResultTuple> result = new ArrayList<>();
       
        reader.beginArray();
        while (reader.hasNext()) {
            JsonObject doc = new JsonParser().parse(reader).getAsJsonObject();
            String[] tuple = new String[select.size()];
            boolean hasNull = false;
            for (int iCol = 0; iCol < select.size(); iCol++) {
                JsonElement el = select.get(iCol).eval(doc);
                if (el != null) {
                    if (el.isJsonPrimitive()) {
                        tuple[iCol] = el.getAsString();
                    } else {
                        tuple[iCol] = el.toString();
                    }
                } else {
                    tuple[iCol] = "";
                    hasNull = true;
                }
            }
            if ((!hasNull) || (!noNullValues)) {
                result.add(new ResultTuple(tuple, select.schema()));
            }
        }
        reader.endArray();
        
        return result;
    }
}
