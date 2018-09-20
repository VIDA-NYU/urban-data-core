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
package org.urban.data.core.set.similarity;

import org.urban.data.core.set.HashObjectSet;
import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;

/**
 * Find all sets that are proper subsets of a given set.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public abstract class SubsetFinder {
    
    private final IdentifiableObjectSet<IdentifiableIDSet> _elements;
    
    public SubsetFinder(IdentifiableObjectSet<IdentifiableIDSet> elements) {
        
        _elements = elements;
    }
    
    public IdentifiableObjectSet<IdentifiableIDSet> getSubsetsFor(IDSet query) {
        
        HashObjectSet<IdentifiableIDSet> result = new HashObjectSet<>();
        
        for (IdentifiableIDSet cand : _elements) {
            if (cand.length() < query.length()) {
                if (query.contains(cand)) {
                    result.add(cand);
                }
            }
        }
        return result;
    }

    public abstract IdentifiableObjectSet<IdentifiableIDSet> getSubsetsFor(int id);
}
