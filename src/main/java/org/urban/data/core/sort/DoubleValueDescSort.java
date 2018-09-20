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
package org.urban.data.core.sort;

import java.util.Comparator;
import org.urban.data.core.object.IdentifiableDouble;

/**
 * Sort identifiable double objects by value in decreasing order.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class DoubleValueDescSort <T extends IdentifiableDouble> implements Comparator<T>{

    @Override
    public int compare(T o1, T o2) {

        return Double.compare(o2.value(), o1.value());
    }
}
