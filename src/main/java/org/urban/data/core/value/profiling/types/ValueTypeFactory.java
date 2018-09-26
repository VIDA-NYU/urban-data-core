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

import org.urban.data.core.set.HashObjectSet;

/**
 * Generate data type labels for scalar values from their unique identifiers.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class ValueTypeFactory extends HashObjectSet<DataTypeLabel> {
   
    public ValueTypeFactory() {
        
        super();
        this.add(new DateType());
        this.add(new DecimalType());
        this.add(new IntegerType());
        this.add(new LongType());
        this.add(new TextType());
    }
}
