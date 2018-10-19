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

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import org.urban.data.core.cache.LeastRecentlyUsedCache;
import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.ImmutableIDSet;
import org.urban.data.core.set.ImmutableIdentifiableIDSet;

/**
 * Reversed adjacency graph.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class CachedReverseGraph extends AdjacencyGraph {

    private final LeastRecentlyUsedCache<IdentifiableIDSet> _edges;
    private final AdjacencyGraph _g;
    
    public CachedReverseGraph(AdjacencyGraph g) {
    
        super(g.nodes());
        
        _g = g;
	
	// By default the cache size is 10% of the nodes in the graph
	_edges = new LeastRecentlyUsedCache<>(
		new BigDecimal(g.nodes().length())
			.divide(new BigDecimal(10), MathContext.DECIMAL64)
			.intValue()
	);
    }
    
    @Override
    public IDSet adjacent(int nodeId) {

	if (!_edges.contains(nodeId)) {
	    List<Integer> edges = new ArrayList<>();
	    for (int target : this.nodes()) {
		if (_g.adjacent(target).contains(nodeId)) {
		    edges.add(target);
		}
	    }
	    _edges.add(
		    new ImmutableIdentifiableIDSet(
			    nodeId,
			    new ImmutableIDSet(edges)
		    )
	    );
	}
	return _edges.get(nodeId);
    }

    @Override
    public AdjacencyGraph reverse() {

        return _g;
    }
}
