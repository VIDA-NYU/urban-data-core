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

import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;

/**
 * Small static graph.
 * 
 * Returns a dynamic graph as its reverse graph. For larger graphs this is more
 * likely to cause an out-of-memory exception.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class SmallStaticGraph <T extends IdentifiableIDSet> extends StaticGraph {

    public SmallStaticGraph(IdentifiableObjectSet<T> edges) {
        
        super(edges);
    }

    @Override
    public AdjacencyGraph reverse() {

	DynamicGraph g = new DynamicGraph(this.nodes());
	for (int target : this.nodes()) {
	    for (int source : this.adjacent(target)) {
		g.add(source, target);
	    }
	}
	return g;
    }    
}
