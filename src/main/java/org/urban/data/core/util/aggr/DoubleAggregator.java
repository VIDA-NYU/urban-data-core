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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.urban.data.core.object.IdentifiableDouble;
import org.urban.data.core.set.HashObjectSet;
import org.urban.data.core.sort.DoubleValueAscSort;

/**
 * Aggregator for identifiable values.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class DoubleAggregator implements IdentifiableAggregator<Double> {
    
    private final HashObjectSet<IdentifiableDouble> _elements;
    
    public DoubleAggregator() {
        
         _elements = new HashObjectSet<>();
    }
    
    @Override
    public void add(int id, Double value) {

        if (_elements.contains(id)) {
            _elements.add(
                    new IdentifiableDouble(
                            id,
                            value + _elements.get(id).value()
                    )
            );
        } else {
            _elements.add(new IdentifiableDouble(id, value));
        }
    }

    @Override
    public List<Integer> getRankingAsc() {

        List<IdentifiableDouble> sortedList = _elements.toList();
        Collections.sort(sortedList, new DoubleValueAscSort());
        
        List<Integer> result = new ArrayList<>();
        for (IdentifiableDouble el : sortedList) {
            result.add(el.id());
        }
        return result;
    }
}
