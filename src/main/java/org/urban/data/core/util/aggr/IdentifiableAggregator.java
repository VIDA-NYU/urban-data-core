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
package org.urban.data.core.util.aggr;

import java.util.List;

/**
 * Aggregator for identifiable values.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public interface IdentifiableAggregator <T> {
    
    /**
     * Add value to aggregate for identified object.
     * 
     * @param id
     * @param value 
     */
    public void add(int id, T value);
    
    /**
     * Get list of identifier sorted by ascending value of their associated 
     * aggregate.
     * 
     * @return 
     */
    public List<Integer> getRankingAsc();
}
