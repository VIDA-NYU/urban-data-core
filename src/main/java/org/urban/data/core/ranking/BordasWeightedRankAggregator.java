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
package org.urban.data.core.ranking;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.urban.data.core.object.IdentifiableObject;
import org.urban.data.core.set.IdentifiableObjectSet;

/**
 * Rank aggregator using Borda's method.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class BordasWeightedRankAggregator {
    
    private final DoubleAggregator _elements = new DoubleAggregator();
    
    public <T extends IdentifiableObject> void add(
            List<T> elements,
            double weight,
            Comparator<T> comparator
    ) {

        if (elements.isEmpty()) {
            return;
        }
        
        Collections.sort(elements, comparator);
        int rank = 1;
	int atRank = 1;
        T el = elements.get(0);
        _elements.add(el.id(), weight * rank);
        for (int iElement = 1; iElement < elements.size(); iElement++) {
            T nextEl = elements.get(iElement);
            if (comparator.compare(el, nextEl) != 0) {
                rank += atRank;
		atRank = 0;
            }
            _elements.add(nextEl.id(), weight * rank);
            el = nextEl;
	    atRank++;
        }
    }

    public <T extends IdentifiableObject> void add(
            IdentifiableObjectSet<T> elements,
            double weight,
            Comparator<T> comparator
    ) {
        this.add(elements.toList(), weight, comparator);
    }
    
    public List<Integer> getRanking() {
        
        return _elements.getRankingAsc();
    }
}
