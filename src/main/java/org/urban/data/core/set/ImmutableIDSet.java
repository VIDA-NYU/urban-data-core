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
package org.urban.data.core.set;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Implements the IDSet interface.
 * 
 * This is an immutable IDSet using an integer array to represent the list of
 * identifier.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class ImmutableIDSet extends IDSetImpl implements IDSet {

    private final Integer[] _values;
    
    /**
     * Initialize the identifier set.
     * 
     * Will sort the given list if the sorted flag is false.
     * 
     * Raises an exception if the values in the list are not unique.
     * 
     * @param values 
     * @param sorted 
     */
    public ImmutableIDSet(Collection<Integer> values, boolean sorted) {
        
        Integer[] arr = new Integer[values.size()];
        _values = values.toArray(arr);
        if (!sorted) {
            Arrays.sort(_values);
        }
        if (_values.length > 1) {
            for (int iPos = 1; iPos < _values.length; iPos++) {
                if (_values[iPos - 1].compareTo(_values[iPos]) == 0) {
                    throw new IllegalArgumentException("Duplicate ID: " + _values[iPos]);
                }
            }
        }
    }
    
    public ImmutableIDSet(Collection<Integer> values) {

        this(values, false);
    }
    
    public ImmutableIDSet(Integer[] values, boolean sorted) {
        
        _values = values;
        if (!sorted) {
            Arrays.sort(_values);
        }
        if (_values.length > 1) {
            for (int iPos = 1; iPos < _values.length; iPos++) {
                if (_values[iPos - 1].compareTo(_values[iPos]) == 0) {
                    throw new IllegalArgumentException("Duplicate ID: " + _values[iPos]);
                }
            }
        }
    }

    public ImmutableIDSet(Integer[] values) {
        
        this(values, false);
    }

    public ImmutableIDSet(String values) {

        String[] tokens = values.split(",");
        _values = new Integer[tokens.length];
        _values[0] = Integer.parseInt(tokens[0]);
        for (int iPos = 1; iPos < _values.length; iPos++) {
            _values[iPos] = Integer.parseInt(tokens[iPos]);
            if (_values[iPos - 1].compareTo(_values[iPos]) >= 0) {
                throw new IllegalArgumentException("Not a unique ID list: " + values);
            }
        }
    }
    
    public ImmutableIDSet(int value) {
        
        _values = new Integer[]{value};
    }
    
    public ImmutableIDSet() {
        
        _values = new Integer[0];
    }
    
    @Override
    public boolean contains(Integer nodeId) {

        return (Arrays.binarySearch(_values, nodeId) >= 0);
    }

    @Override
    public ImmutableIDSet create(Collection<Integer> values) {

        return new ImmutableIDSet(values);
    }

    @Override
    public int first() {

        return _values[0];
    }

    @Override
    public boolean isEmpty() {

        return (_values.length == 0);
    }

    @Override
    public Iterator<Integer> iterator() {
        
        return Arrays.asList(_values).iterator();
    }

    @Override
    public int length() {

        return _values.length;
    }

    @Override
    public int[] toArray() {
        
        int[] result = new int[_values.length];
        for (int iValue = 0; iValue < _values.length; iValue++) {
            result[iValue] = _values[iValue];
        }
        return result;
    }
    
    @Override
    public List<Integer> toList() {

        return Arrays.asList(_values);
    }
}
