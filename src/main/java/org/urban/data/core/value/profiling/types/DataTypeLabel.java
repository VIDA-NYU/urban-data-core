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
package org.urban.data.core.value.profiling.types;

import org.urban.data.core.object.Entity;

/**
 * Sub-class of labels that are used to classify data types of values and
 * columns in a dataset.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public abstract class DataTypeLabel extends Entity {
    
    public DataTypeLabel(int identifier, String name) {
        
        super(identifier, name);
    }
    
    /**
     * True, if the data type label is DATE.
     * 
     * @return 
     */
    public abstract boolean isDate();
    
    /**
     * True, if the label is one of the numeric types.
     * 
     * @return 
     */
    public abstract boolean isNumeric();
    
    /**
     * true, if label is TEXT.
     * 
     * @return 
     */
    public abstract boolean isText();
}
