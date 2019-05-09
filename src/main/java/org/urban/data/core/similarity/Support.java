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
import org.urban.data.core.util.FormatedBigDecimal;

/**
 * BigDecimal for support for an object from a set of objects.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class Support extends FormatedBigDecimal implements Comparable<Support>, OverlapSimilarityFunction {
    
    public Support(int overlap, int setSize, int scale) {
        
        super(new BigDecimal((double)overlap/(double)setSize), scale);
    }
    
    public Support(int overlap, int setSize) {
        
        super(new BigDecimal((double)overlap/(double)setSize));
    }
    
    public Support() {
        
        super(BigDecimal.ZERO);
    }

    @Override
    public int compareTo(Support r) {

        return this.value().compareTo(r.value());
    }

    @Override
    public double sim(int size1, int size2, int overlap) {

        return ((double)overlap/(double)size1);
    }
}