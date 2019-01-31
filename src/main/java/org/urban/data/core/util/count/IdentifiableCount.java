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
package org.urban.data.core.util.count;

import org.urban.data.core.object.IdentifiableObjectImpl;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class IdentifiableCount extends IdentifiableObjectImpl implements Comparable<IdentifiableCount> {
    
    private int _count;
    
    public IdentifiableCount(int id, int count) {
        
        super(id);
        
        _count = count;
    }
    
    public IdentifiableCount(String[] valuePair) {
	
	this(Integer.parseInt(valuePair[0]), Integer.parseInt(valuePair[1]));
    }
    
    public IdentifiableCount(String pairString) {
	
	this(pairString.split(":"));
    }

    public void clear() {
    
        _count = 0;
    }
    
    @Override
    public int compareTo(IdentifiableCount c) {

        return Integer.compare(this.id(), c.id());
    }
    
    public int count() {
        
        return _count;
    }
    
    public int inc(int value) {
        
        return _count + value;
    }
    
    public String toPairString() {
	
	return this.id() + ":" + _count;
    }
}
