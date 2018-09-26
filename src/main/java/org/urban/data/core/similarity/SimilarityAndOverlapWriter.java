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

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;
import org.urban.data.core.io.SynchronizedWriter;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class SimilarityAndOverlapWriter implements ObjectSimilarityConsumer {

    private final SynchronizedWriter _out;
    private final IdentifiableObjectSet<IdentifiableIDSet> _set1;
    private final IdentifiableObjectSet<IdentifiableIDSet> _set2;
    
    public SimilarityAndOverlapWriter(
            IdentifiableObjectSet<IdentifiableIDSet> set1,
            IdentifiableObjectSet<IdentifiableIDSet> set2,
            SynchronizedWriter out
    ) {
        _set1 = set1;
        _set2 = set2;
        _out = out;
    }
    
    public SimilarityAndOverlapWriter(
            IdentifiableObjectSet<IdentifiableIDSet> set1,
            IdentifiableObjectSet<IdentifiableIDSet> set2,
            PrintWriter out
    ) {
        
        this(set1, set2, new SynchronizedWriter(out));
    }
    
    public SimilarityAndOverlapWriter(
            IdentifiableObjectSet<IdentifiableIDSet> set,
            PrintWriter out
    ) {
        
        this(set, set, new SynchronizedWriter(out));
    }
    
    @Override
    public void consume(int id1, int id2, BigDecimal sim) {

        IDSet nodes1 = _set1.get(id1);
        IDSet nodes2 = _set2.get(id2);
        
        _out.write(
            id1 + "\t" +
            id2 + "\t" +
            nodes1.length() + "\t" +
            nodes2.length() + "\t" +
            nodes1.overlap(nodes2) + "\t" +
            sim.setScale(8, RoundingMode.HALF_DOWN).toPlainString()
        );
    }
}
