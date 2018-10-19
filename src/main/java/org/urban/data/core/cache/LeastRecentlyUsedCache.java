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
package org.urban.data.core.cache;

import java.util.LinkedList;
import org.urban.data.core.object.IdentifiableObject;
import org.urban.data.core.set.HashIDSet;

/**
 * Least-recently used cache for identifiable objects.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class LeastRecentlyUsedCache <T extends IdentifiableObject> {
    
    private final LinkedList<T> _cacheElements;
    private final HashIDSet _cacheIndex;
    private final int _cacheSize;
    
    public LeastRecentlyUsedCache(int cacheSize) {
	
	_cacheSize = cacheSize;
	
	_cacheElements = new LinkedList<>();
	_cacheIndex = new HashIDSet();
    }
    
    public void add(T element) {

	_cacheElements.addFirst(element);
	if (_cacheElements.size() > _cacheSize) {
	    T el = _cacheElements.removeLast();
	    _cacheIndex.remove(el.id());
	}
	_cacheIndex.add(element.id());
    }
    
    public boolean contains(T element) {
	
	return this.contains(element.id());
    }
    
    public boolean contains(int id) {
	
	return _cacheIndex.contains(id);
    }
    
    public T get(int id) {
	
	for (T element : _cacheElements) {
	    if (element.id() == id) {
		return element;
	    }
	}
	return null;
    }
}
