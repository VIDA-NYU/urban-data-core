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
package org.urban.data.core.graph;

import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;

/**
 * Adjacency graph with fixed set of edges.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class StaticGraph <T extends IdentifiableIDSet> extends AdjacencyGraph {

    private final IdentifiableObjectSet<T> _edges;

    public StaticGraph(IdentifiableObjectSet<T> edges) {
        
        super(edges.keys());
        
        _edges = edges;
    }

    @Override
    public IDSet adjacent(int nodeId) {

	return _edges.get(nodeId);
    }

    @Override
    public AdjacencyGraph reverse() {

	return new ReverseGraph(this);
    }    
}
