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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.urban.data.core.object.IdentifiableObject;

/**
 * Least-recently used cache for identifiable objects.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class LeastRecentlyUsedCache <T extends IdentifiableObject> {
    
    private class CacheEntry <T extends IdentifiableObject> {

	private final T _element;
	private final int _index;
	private Date _lastUsed;
	
	public CacheEntry(T element, int index) {
	    
	    _element = element;
	    _index = index;
	    _lastUsed = new Date();
	}
	
	public T element() {
	    
	    return _element;
	}
	
	public int index() {
	    
	    return _index;
	}
	
	public Date lastUsed() {
	    
	    return _lastUsed;
	}
	
	public void touch() {
	    
	    _lastUsed = new Date();
	}
    }
    
    private final ArrayList<CacheEntry<T>> _cacheElements;
    private final HashMap<Integer, Integer> _cacheIndex;
    private final int _cacheSize;
    
    public LeastRecentlyUsedCache(int cacheSize) {
	
	_cacheElements = new ArrayList<>();
	_cacheIndex = new HashMap<>();
	_cacheSize = cacheSize;
    }
    
    public void add(T element) {

	if (_cacheElements.size() >= _cacheSize) {
	    CacheEntry<T> lruEntry = _cacheElements.get(0);
	    for (int iEntry = 1; iEntry < _cacheElements.size(); iEntry++) {
		CacheEntry<T> entry = _cacheElements.get(iEntry);
		if (entry.lastUsed().before(lruEntry.lastUsed())) {
		    lruEntry = entry;
		}
	    }
	    CacheEntry<T> el = new CacheEntry<>(element, lruEntry.index());
	    _cacheElements.set(el.index(), el);
	    _cacheIndex.remove(lruEntry.element().id());
	    _cacheIndex.put(element.id(), el.index());
	} else {
	    CacheEntry<T> el = new CacheEntry<>(element, _cacheElements.size());
	    _cacheElements.add(el);
	    _cacheIndex.put(element.id(), el.index());
	}
    }
    
    public boolean contains(int id) {
	
	return _cacheIndex.containsKey(id);
    }
    
    public T get(int id) {
	
	CacheEntry<T> entry = _cacheElements.get(_cacheIndex.get(id));
	entry.touch();
	return entry.element();
    }
}
