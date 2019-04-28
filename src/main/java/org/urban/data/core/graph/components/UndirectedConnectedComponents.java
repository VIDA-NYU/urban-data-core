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

import java.util.HashMap;
import org.urban.data.core.set.HashIDSet;
import org.urban.data.core.set.HashObjectSet;
import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;
import org.urban.data.core.set.ImmutableIDSet;
import org.urban.data.core.set.ImmutableIdentifiableIDSet;
import org.urban.data.core.set.MutableIDSet;
import org.urban.data.core.util.count.Counter;

/**
 * Default connected component generator. Use  for nodes that are unstructured
 * integers.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class UndirectedConnectedComponents implements ConnectedComponentGenerator {

    private final Counter _componentCounter;
    private final HashMap<Integer, MutableIDSet> _components;
    private final HashMap<Integer, Integer> _componentMap;
    
    public UndirectedConnectedComponents(IDSet nodes) {
	
	_componentCounter = new Counter(0);
	_components = new HashMap<>();
	_componentMap = new HashMap<>();

	for (int nodeId : nodes) {
	    int componentId = _componentCounter.inc();
	    HashIDSet component = new HashIDSet();
	    component.add(nodeId);
	    _components.put(componentId, component);
	    _componentMap.put(nodeId, componentId);
	}
    }
    
    public int componentCount() {
        
        return _components.size();
    }

    @Override
    public synchronized IdentifiableObjectSet<IdentifiableIDSet> componentsOfSizeOrGreater(int size) {

	HashObjectSet result = new HashObjectSet();
	
	for (int compId : _components.keySet()) {
            IDSet comp = _components.get(compId);
            if (comp.length() >= size) {
                result.add(
                        new ImmutableIdentifiableIDSet(
                                compId,
                                new ImmutableIDSet(comp.toSortedList(), true)
                        )
                );
            }
	}
	
        return result;
    }

    /**
     * Get the connected component that contains the node with the given
     * identifier.
     * 
     * @param nodeId
     * @return 
     */
    private int getComponentForNode(int nodeId) {
	
        if (_componentMap.containsKey(nodeId)) {
            return _componentMap.get(nodeId);
        } else {
            HashIDSet component = new HashIDSet();
            component.add(nodeId);
            int componentId = _componentCounter.inc();
            _components.put(componentId, component);
            _componentMap.put(nodeId, componentId);
            return componentId;
        }
    }
    
    @Override
    public synchronized void edge(int sourceId, int targetId) {	
        
        int targetComponent = this.getComponentForNode(sourceId);
        int sourceComponent = this.getComponentForNode(targetId);

        if (targetComponent != sourceComponent) {
            MutableIDSet target = _components.get(targetComponent);
            MutableIDSet source = _components.get(sourceComponent);
            if (source.length() > target.length()) {
                target = source;
                int c = targetComponent;
                targetComponent = sourceComponent;
                sourceComponent = c;
            }
            for (int nodeId : _components.remove(sourceComponent)) {
                target.add(nodeId);
            _componentMap.put(nodeId, targetComponent);
            }
        }
    }
    
    @Override
    public synchronized IdentifiableObjectSet<IdentifiableIDSet> getComponents() {

	HashObjectSet result = new HashObjectSet();
	
	for (int compId : _components.keySet()) {
	    result.add(
                    new ImmutableIdentifiableIDSet(
                            compId,
                            new ImmutableIDSet(
                                    _components.get(compId).toSortedList(),
                                    true
                            )
                    )
            );
	}
	
        return result;
    }

    @Override
    public boolean isDirected() {

        return false;
    }
    
    public synchronized boolean nodesAreInSameComponent(int node1, int node2) {
        
        int comp1 = this.getComponentForNode(node1);
        int comp2 = this.getComponentForNode(node2);
        return (comp1 == comp2);
    }
}
