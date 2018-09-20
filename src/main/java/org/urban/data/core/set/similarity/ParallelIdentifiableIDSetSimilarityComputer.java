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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.similarity.ObjectSimilarityConsumer;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class ParallelIdentifiableIDSetSimilarityComputer {

    private class SetSimilarityComputer implements Runnable {

        private final ObjectSimilarityConsumer _consumer;
        private final List<IdentifiableIDSet> _elements;
        private final int _id;
        private final IdentifiableIDSetEnumerator _setFactory;
        private final IDSetSimilarityFunction _sim;
        private final int _threads;
        
        public SetSimilarityComputer(
                int id,
                int threads,
                List<IdentifiableIDSet> elements,
                IdentifiableIDSetEnumerator setFactory,
                IDSetSimilarityFunction sim,
                ObjectSimilarityConsumer consumer
        ) {
            _id = id;
            _threads = threads;
            _elements = elements;
            _setFactory = setFactory;
            _sim = sim;
            _consumer = consumer;
        }
        
        @Override
        public void run() {

            for (int iElement = 0; iElement < _elements.size() - 1; iElement++) {
                if ((iElement % _threads) == _id) {
                    IdentifiableIDSet set1 = _elements.get(iElement);
                    for (IdentifiableIDSet set2 : _setFactory.getSet(iElement)) {
                        BigDecimal sim = _sim.eval(set1, set2);
                        _consumer.consume(set1.id(), set2.id(), sim);
                    }
                }
            }
        }
    }
    
    private final int _threads;
    
    public ParallelIdentifiableIDSetSimilarityComputer(int threads) {
        
        _threads = threads;
    }
    
    public void run(
            Collection<IdentifiableIDSet> elements,
            IDSetSimilarityFunction sim,
            ObjectSimilarityConsumer consumer
    ) throws java.lang.InterruptedException {
        ArrayList<IdentifiableIDSet> sortedElements = new ArrayList<>(elements);
        Collections.sort(sortedElements, new Comparator<IdentifiableIDSet>(){
            @Override
            public int compare(IdentifiableIDSet s1, IdentifiableIDSet s2) {
                return Integer.compare(s1.id(), s2.id());
            }
        });
        
        ExecutorService es = Executors.newCachedThreadPool();
        for (int iThread = 0; iThread < _threads; iThread++) {
            es.execute(
                    new SetSimilarityComputer(
                            iThread,
                            _threads,
                            sortedElements,
                            new DefaultIdentifiableIDSetEnumerator(sortedElements),
                            sim,
                            consumer
                    )
            );
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);
    }
    
    public void run(
            Collection<IdentifiableIDSet> elements,
            ObjectSimilarityConsumer consumer
    ) throws java.lang.InterruptedException {
        this.run(elements, new IDSetJaccardIndex(), consumer);
    }    

    public void run(
            List<IdentifiableIDSet> lhs,
            List<IdentifiableIDSet> rhs,
            IDSetSimilarityFunction sim,
            ObjectSimilarityConsumer consumer
    ) throws java.lang.InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int iThread = 0; iThread < _threads; iThread++) {
            es.execute(
                    new SetSimilarityComputer(
                            iThread,
                            _threads,
                            lhs,
                            new FullSetEnumerator(rhs),
                            sim,
                            consumer
                    )
            );
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);
    }

    public void run(
            List<IdentifiableIDSet> lhs,
            List<IdentifiableIDSet> rhs,
            ObjectSimilarityConsumer consumer
    ) throws java.lang.InterruptedException {
        this.run(lhs, rhs, new IDSetJaccardIndex(), consumer);
    }
}
