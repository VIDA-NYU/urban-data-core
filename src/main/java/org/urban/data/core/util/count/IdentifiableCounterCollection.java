/*
 * Copyright 2018 Heiko Mueller <heiko.mueller@nyu.edu>.
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

import org.urban.data.core.object.IdentifiableObject;

/**
 * Identifiable collection of counter sets.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class IdentifiableCounterCollection extends IdentifiableCounterSet implements IdentifiableObject {

    private final int _id;
    
    public IdentifiableCounterCollection(int id) {
	
	_id = id;
    }
    
    @Override
    public int id() {
	
	return _id;
    }
    
    public String toIntString() {
	
	String result = "";
	
	for (IdentifiableCount el : this) {
	    String val = el.id() + ":" + el.count();
	    if (result.equals("")) {
		result = val;
	    } else {
		result += "," + val;
	    }
	}
	return result;
    }
}
