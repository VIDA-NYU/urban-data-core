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

import java.util.Iterator;
import java.util.List;
import org.urban.data.core.object.IdentifiableObjectImpl;

/**
 * Implementation for identifiable identifier set that contains a immutable set
 * of identifiers.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class ImmutableIdentifiableIDSet extends IdentifiableObjectImpl implements IdentifiableIDSet {
    
    private final IDSet _values;
    
    public ImmutableIdentifiableIDSet(int id) {
    
        super(id);
        
        _values = new ImmutableIDSet();
    }
    
    public ImmutableIdentifiableIDSet(int id, IDSet values) {
        
        super(id);
        
        _values = values;
    }
    
    public ImmutableIdentifiableIDSet(IDSet values) {
        
        this(-1, values);
    }

    @Override
    public boolean contains(Integer element) {

        return _values.contains(element);
    }

    @Override
    public boolean contains(ObjectSet<Integer> values) {

        return _values.contains(values);
    }

    @Override
    public ImmutableIDSet difference(IDSet list) {

        return _values.difference(list);
    }

    @Override
    public int first() {

        return _values.first();
    }

    @Override
    public ImmutableIDSet intersect(IDSet list) {

        return _values.intersect(list);
    }

    @Override
    public boolean isEmpty() {

        return _values.isEmpty();
    }

    @Override
    public boolean isTrueSubsetOf(ObjectSet<Integer> list) {

        return _values.isTrueSubsetOf(list);
    }

    @Override
    public boolean isTrueSubsetOf(IDSet list) {

        return _values.isTrueSubsetOf(list);
    }

    @Override
    public Iterator<Integer> iterator() {

        return _values.iterator();
    }

    @Override
    public int length() {

        return _values.length();
    }

    @Override
    public int maxId() {

        return _values.maxId();
    }

    @Override
    public int minId() {

        return _values.minId();
    }

    @Override
    public int overlap(ObjectSet<Integer> list) {

        return _values.overlap(list);
    }

    @Override
    public boolean overlaps(ObjectSet<Integer> list) {

        return _values.overlaps(list);
    }

    @Override
    public boolean sameSetAs(ObjectSet<Integer> list) {

        return _values.sameSetAs(list);
    }

    @Override
    public int[] toArray() {

        return _values.toArray();
    }

    @Override
    public String toIntString() {

        return _values.toIntString();
    }

    @Override
    public List<Integer> toList() {

        return _values.toList();
    }

    @Override
    public List<Integer> toSortedList() {

        return _values.toSortedList();
    }

    @Override
    public ImmutableIDSet union(IDSet list) {

        return _values.union(list);
    }

    @Override
    public ImmutableIDSet union(int id) {

        return _values.union(id);
    }
}
