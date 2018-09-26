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
package org.urban.data.core.graph.components;

import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.graph.build.GraphBuilderEdgeCondition;

/**
 * Draw an edge between two nodes if both nodes contain each other's id in their
 * id set.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class MutualEdgeCondition <T extends IdentifiableIDSet> implements GraphBuilderEdgeCondition {

    private final GraphBuilderEdgeCondition _condition;
    
    public MutualEdgeCondition(GraphBuilderEdgeCondition condition) {
    
        _condition = condition;
    }
    
    @Override
    public boolean hasEdge(int sourceId, int targetId) {

        if (_condition.isSymmetric()) {
            return _condition.hasEdge(sourceId, targetId);
        } else {
            return _condition.hasEdge(sourceId, targetId) && _condition.hasEdge(targetId, sourceId);
        }
    }

    @Override
    public boolean isSymmetric() {

        return true;
    }
}
