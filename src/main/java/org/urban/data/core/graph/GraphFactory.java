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
package org.urban.data.core.graph;

import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;

/**
 * Factory for static graphs based on graph type.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public final class GraphFactory {
    
    public static <T extends IdentifiableIDSet> StaticGraph<T> getGraph(
	    StaticGraphType type,
	    IdentifiableObjectSet<T> edges
    ) {
	switch (type) {
	    case SMALL:
		return new SmallStaticGraph(edges);
	    case MEDIUM:
		return new MediumStaticGraph(edges);
	    case LARGE:
		return new LargeStaticGraph(edges);
	    default:
		throw new java.lang.IllegalArgumentException("Unknown graph type: " + type.toString());
	}
    }
}
