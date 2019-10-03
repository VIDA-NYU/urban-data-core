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
package org.urban.data.core.prune;

import java.util.List;
import org.urban.data.core.constraint.Threshold;
import org.urban.data.core.object.IdentifiableDouble;

/**
 * Return list of candidates that satisfy a given threshold constraint.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class ThresholdFinder <T extends IdentifiableDouble> extends CandidateSetFinder<T> {
 
    private final Threshold _constraint;

    public ThresholdFinder(Threshold constraint) {
        
        _constraint = constraint;
    }

    @Override
    public int getPruneIndex(List<T> elements, int start) {

        for (int iIndex = start; iIndex < elements.size(); iIndex++) {
            if (!_constraint.isSatisfied(elements.get(iIndex).value())) {
                return iIndex;
            }
        }
        return elements.size();
    }
}
