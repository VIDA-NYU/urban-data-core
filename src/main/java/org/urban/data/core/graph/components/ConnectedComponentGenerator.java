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

import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;
import org.urban.data.core.graph.build.GraphBuilder;

/**
 * Generate connected components for a set of nodes. Starts with a single
 * component for each node. Merges components to find connected components.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public interface ConnectedComponentGenerator extends GraphBuilder {
    
    /**
     * Return all connected components with at least size elements.
     * 
     * @param size
     * @return 
     */
    public IdentifiableObjectSet<IdentifiableIDSet> componentsOfSizeOrGreater(int size);

    /**
     * Connected component result.
     * 
     * @return 
     */
    public IdentifiableObjectSet<IdentifiableIDSet> getComponents();
}
