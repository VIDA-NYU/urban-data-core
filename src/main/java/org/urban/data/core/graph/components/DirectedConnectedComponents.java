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
package org.urban.data.core.graph.components;

import org.urban.data.core.set.HashObjectSet;
import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;
import org.urban.data.core.graph.DynamicGraph;
import org.urban.data.core.graph.Kosaraju;

/**
 * Default connected component generator. Use  for nodes that are unstructured
 * integers.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class DirectedConnectedComponents implements ConnectedComponentGenerator {

    private final DynamicGraph _graph;
    
    public DirectedConnectedComponents(DynamicGraph graph) {
	
	_graph = graph;
    }

    @Override
    public synchronized IdentifiableObjectSet<IdentifiableIDSet> componentsOfSizeOrGreater(int size) {

	HashObjectSet result = new HashObjectSet();
	
        IdentifiableObjectSet<IdentifiableIDSet> components = this.getComponents();
	for (IdentifiableIDSet comp : components) {
            if (comp.length() >= size) {
                result.add(comp);
            }
	}
	
        return result;
    }
    
    @Override
    public synchronized void edge(int sourceId, int targetId) {	
        
        _graph.add(sourceId, targetId);
    }

    @Override
    public synchronized IdentifiableObjectSet<IdentifiableIDSet> getComponents() {

	
        return new Kosaraju().stronglyConnectedComponents(_graph);
    }

    @Override
    public boolean isDirected() {

        return true;
    }
}
