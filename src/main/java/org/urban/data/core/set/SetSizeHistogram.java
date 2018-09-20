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
package org.urban.data.core.set;

import java.util.Arrays;
import java.util.HashMap;
import org.urban.data.core.util.count.Counter;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class SetSizeHistogram <T extends SetObject> {

    private final HashMap<Integer, Counter> _counts;
    
    public SetSizeHistogram(Iterable<T> elements) {
        
        _counts = new HashMap<>();
        
        for (T element : elements) {
            int size = element.getSize();
            if (!_counts.containsKey(size)) {
                _counts.put(size, new Counter(1));
            } else {
                _counts.get(size).inc();
            }
        }
    }
    
    public int[][] getBuckets(int[] bounds) {
        
        Arrays.sort(bounds);
        
        int[][] result = new int[bounds.length + 1][2];
        for (int iBound = 0; iBound < bounds.length; iBound++) {
            result[iBound][0] = bounds[iBound];
        }
        result[bounds.length][0] = -1;
        
        for (int size : _counts.keySet()) {
            int index = 0;
            while (index < bounds.length) {
                if (size <= bounds[index]) {
                    break;
                } else {
                    index++;
                }
            }
            result[index][1] += _counts.get(size).value();
        }
        
        return result;
    }
        
    public int[][] getBuckets() {
        
        int[] bounds = new int[_counts.size()];
        
        int index = 0;
        for (int bound : _counts.keySet()) {
            bounds[index++] = bound;
        }
        
        return this.getBuckets(bounds);
    }
}
