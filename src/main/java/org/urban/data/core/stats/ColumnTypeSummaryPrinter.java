/*
 * Copyright 2019 New York University.
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
package org.urban.data.core.stats;

import java.io.BufferedReader;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.urban.data.core.io.FileSystem;
import org.urban.data.core.profiling.datatype.DefaultDataTypeAnnotator;
import org.urban.data.core.util.count.Counter;

/**
 * Print a summary of data types for values in a given column. Reads distinct
 * column values from a text file.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class ColumnTypeSummaryPrinter {
    
    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.out.println("Usage: <input-file>");
            System.exit(-1);
        }
        
        File inputFile = new File(args[0]);
        
        DefaultDataTypeAnnotator types = new DefaultDataTypeAnnotator();
        
        HashMap<String, Counter> counts = new HashMap<>();
        
        try (BufferedReader in = FileSystem.openReader(inputFile)) {
            String line;
            while ((line = in.readLine()) != null) {
                String type = types.getType(line).name();
                if (!counts.containsKey(type)) {
                    counts.put(type, new Counter(1));
                } else {
                    counts.get(type).inc();
                }
            }
        } catch (java.io.IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, "RUN", ex);
        }
        
        for (String type : counts.keySet()) {
            System.out.println(type + "\t" + counts.get(type).value());
        }
    }
}
