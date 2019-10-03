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
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.urban.data.core.io.FileSystem;
import org.urban.data.core.query.json.JFilter;
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
    
    public List<String[]> filter(
            JsonReader reader,
            List<JQuery> select,
            List<JFilter> where
    ) throws java.io.IOException {

        ArrayList<String[]> result = new ArrayList<>();
       
        reader.beginArray();
        while (reader.hasNext()) {
            JsonObject doc = new JsonParser().parse(reader).getAsJsonObject();
            boolean accept = true;
            for (JFilter cond : where) {
                if (!cond.eval(doc)) {
                    accept = false;
                    break;
                }
            }
            if (accept) {
                String[] tuple = new String[select.size()];
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
                    }
                }
                result.add(tuple);
            }
        }
        reader.endArray();
        
        return result;
    }
    
    public List<String[]> query(
            String target,
            List<JQuery> select,
            List<JFilter> where
    ) throws java.io.IOException {
        
        try (JsonReader reader = new JsonReader(
            new InputStreamReader(FileSystem.openFile(_database)))
        ) {
            if (target.equals("/")) {
                return this.filter(reader, select, where);
            } else {
                reader.beginObject();
                if (reader.nextName().equals(target)) {
                    return this.filter(reader, select, where);
                } else {
                    reader.skipValue();
                }
                reader.endObject();
                return new ArrayList<>();
            }
        }
    }

    public List<String[]> query(String target, List<JQuery> select) throws java.io.IOException {

        return this.query(target, select, new ArrayList<>());
    }
            
    public static void main(String[] args) {
        
        if (args.length < 3) {
            System.out.println(COMMAND);
            System.exit(-1);
        }
        
        JsonQuery engine = new JsonQuery(new File(args[0]));
        String target = args[1];
        
        ArrayList<JQuery> select = new ArrayList<>();
        ArrayList<JFilter> where = new ArrayList<>();

        for (int iArg = 2; iArg < args.length; iArg++) {
            String arg = args[iArg];
            int pos = arg.indexOf("=");
            if (pos != -1) {
                String attr = arg.substring(0, pos).trim();
                String val = arg.substring(pos + 1).trim();
                where.add(new JFilter(new JQuery(attr), val));
            } else {
                select.add(new JQuery(arg));
            }
        }
        
        try {
            for (String[] tuple : engine.query(target, select, where)) {
                System.out.println(StringHelper.joinStrings(tuple, "\t"));
            }
        } catch (java.io.IOException ex) {
            LOGGER.log(Level.SEVERE, "EXEC", ex);
            System.exit(-1);
        }
    }
}
