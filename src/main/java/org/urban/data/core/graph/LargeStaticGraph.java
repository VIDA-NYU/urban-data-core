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
 * Large static graph.
 * 
 * Returns a reverse graph instead of a dynamic graph as its reverse graph. For
 * larger graphs this is less likely to cause an out-of-memory exception.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class LargeStaticGraph <T extends IdentifiableIDSet> extends StaticGraph {

    public LargeStaticGraph(IdentifiableObjectSet<T> edges) {
        
        super(edges);
    }

    @Override
    public AdjacencyGraph reverse() {

	return new ReverseGraph(this);
    }    
}
