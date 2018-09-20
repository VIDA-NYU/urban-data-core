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
package org.urban.data.core.constraint;

import java.math.BigDecimal;

/**
 * Threshold constraints check whether a given value satisfies a threshold.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public abstract class ThresholdConstraint {
    
    public static ThresholdConstraint getGreaterConstraint(BigDecimal threshold) {
        
        if (threshold.compareTo(BigDecimal.ONE) == 0) {
            return new EqualsOneConstraint();
        } else {
            return new GreaterThanConstraint(threshold);
        }
    }

    public static ThresholdConstraint getGreaterConstraint(double threshold) {
        
        return getGreaterConstraint(new BigDecimal(threshold));
    }
    
    public boolean isSatisfied(double value) {
        
        return this.isSatisfied(new BigDecimal(value));
    }
    
    public abstract boolean isSatisfied(BigDecimal value);
}
