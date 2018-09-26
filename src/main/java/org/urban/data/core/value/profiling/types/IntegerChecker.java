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

/**
 * Base implementation for type checker that checks for Integer values. In
 * addition ti the value counts maintained by the super class this checker also
 * keeps track of the minimal and maximal detected integer value. If none of
 * the tested values is an Integer the minimum and maximum values are zero.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public abstract class IntegerChecker extends DataTypeChecker {
    
    private int _maxValue = Integer.MIN_VALUE;
    private int _minValue = Integer.MAX_VALUE;
    
    public IntegerChecker() {
	
	super(new IntegerType());
    }
    
    /**
     * The maximum detected integer value. If no value was matched successfully
     * the result is zero.
     * 
     * @return 
     */
    public int getMaximumValue() {
	
	if (this.hasMatches()) {
	    return _maxValue;
	} else {
	    return 0;
	}
    }
    
    /**
     * The minimum detected integer value. If no value was matched successfully
     * the result is zero.
     * 
     * @return 
     */
    public int getMinimumValue() {
	
	if (this.hasMatches()) {
	    return _minValue;
	} else {
	    return 0;
	}
    }
    
    /**
     * Adjusts the minimum and maximum values according to the given matched
     * value.
     * @param value 
     */
    public void matchedValue(int value) {

	if (value > _maxValue) {
	    _maxValue = value;
	}
	if (value < _minValue) {
	    _minValue = value;
	}
    }
}
