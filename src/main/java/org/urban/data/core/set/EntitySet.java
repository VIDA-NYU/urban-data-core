/*
 * Copyright 2019 Heiko Mueller <heiko.mueller@nyu.edu>.
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

import java.io.File;
import org.urban.data.core.io.EntityConsumer;
import org.urban.data.core.io.EntitySetReader;
import org.urban.data.core.object.Entity;
import org.urban.data.core.object.filter.ObjectFilter;

/**
 * Set of identifiable entities.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class EntitySet extends HashObjectSet<Entity> {
   
    private class EntityBuffer implements EntityConsumer {

	private final HashObjectSet<Entity> _buffer;
	
	public EntityBuffer(HashObjectSet<Entity> buffer) {
	    
	    _buffer = buffer;
	}
	
	@Override
	public void close() {
	    
	}

	@Override
	public void consume(Entity entity) {
	    
	    _buffer.add(entity);
	}

	@Override
	public void open() {
	
	}
    }
    
    public EntitySet(File file) throws java.io.IOException {
	
	new EntitySetReader().read(file, new EntityBuffer(this));
    }
    
    public EntitySet(File file, ObjectFilter<Integer> filter) throws java.io.IOException {
	
	new EntitySetReader().read(file, filter, new EntityBuffer(this));
    }
}
