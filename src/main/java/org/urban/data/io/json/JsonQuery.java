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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.urban.data.io.FileSystem;
import org.urban.data.core.query.json.JQuery;
import org.urban.data.core.util.StringHelper;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class JsonQuery {
    
    private final static String COMMAND =
            "Usage:\n" +
            "  <database-file>\n" +
            "  <target-path>\n" +
            "  <select-element-1>\n" +
            "...";
    
    private final static Logger LOGGER = Logger.getLogger(JsonQuery.class.getName());
    
    private final File _database;
    
    public JsonQuery(File database) {
        
        _database = database;
    }
    
    public List<String[]> query(String target, List<JQuery> select) throws java.io.IOException {
        
        ArrayList<String[]> result = new ArrayList<>();
        
        try (JsonReader reader = new JsonReader(
            new InputStreamReader(FileSystem.openFile(_database)))
        ) {
            reader.beginObject();
            if (reader.nextName().equals(target)) {
                reader.beginArray();
                while (reader.hasNext()) {
                    JsonObject doc = new JsonParser().parse(reader).getAsJsonObject();
                    String[] tuple = new String[select.size()];
                    for (int iCol = 0; iCol < select.size(); iCol++) {
                        tuple[iCol] = select.get(iCol).eval(doc).getAsString();
                    }
                    result.add(tuple);
                }
                reader.endArray();
            } else {
                reader.skipValue();
            }
            reader.endObject();
        }
        
        return result;
    }

    public static void main(String[] args) {
        
        if (args.length < 3) {
            System.out.println(COMMAND);
            System.exit(-1);
        }
        
        JsonQuery engine = new JsonQuery(new File(args[0]));
        String target = args[1];
        ArrayList<JQuery> select = new ArrayList<>();
        for (int iArg = 2; iArg < args.length; iArg++) {
            select.add(new JQuery(args[iArg]));
        }
        
        try {
            for (String[] tuple : engine.query(target, select)) {
                System.out.println(StringHelper.joinStrings(tuple, "\t"));
            }
        } catch (java.io.IOException ex) {
            LOGGER.log(Level.SEVERE, "EXEC", ex);
            System.exit(-1);
        }
    }
}
