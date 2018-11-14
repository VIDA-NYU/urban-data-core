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
package org.urban.data.core.similarity;

import java.math.BigDecimal;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.urban.data.core.constraint.ThresholdConstraint;

/**
 * Compute string similarity as Jaro-Winkler distance between the given terms.
 * 
 * Takes a minimum threshold constraint as parameter. Returns null if the
 * distance between the two strings does not satisfy the constraint.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class JaroWinklerStringSimilarity implements StringSimilarityComputer {

    private final ThresholdConstraint _threshold;
    
    public JaroWinklerStringSimilarity(ThresholdConstraint threshold) {
        
        _threshold = threshold;
    }
    
    @Override
    public BigDecimal sim(String term1, String term2) {

        BigDecimal sim;
        sim = new BigDecimal(new JaroWinklerDistance().apply(term1, term2));
        
        if (_threshold.isSatisfied(sim)) {
            return sim;
        } else {
            return null;
        }
    }
}
