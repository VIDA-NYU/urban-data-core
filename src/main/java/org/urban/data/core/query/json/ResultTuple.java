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
package org.urban.data.core.query.json;

import java.util.HashMap;
import org.urban.data.core.util.StringHelper;

/**
 * Tuple in the result set of a Json query. Each tuple is a list of values that
 * can either be accessed by their index position of the column key.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class ResultTuple {
    
    private final HashMap<String, Integer> _columns;
    private final String[] _values;
    
    public ResultTuple(String[] values, HashMap<String, Integer> columns) {
        
        _values = values;
        _columns = columns;
    }
    
    /**
     * Access result cell value by index.
     * 
     * @param index
     * @return 
     */
    public String get(int index) {
        
        return _values[index];
    }
    
    /**
     * Access result cell by column name.
     * 
     * @param column
     * @return 
     */
    public String get(String column) {
        
        return _values[_columns.get(column)];
    }
    
    /**
     * Join all tuple values into a single string with the given delimiter.
     * 
     * @param delimiter
     * @return 
     */
    public String join(String delimiter) {
       
        return StringHelper.joinStrings(_values, delimiter);
    }
    
    public int size() {
        
        return _values.length;
    }
}
