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
 * The constraint is satisfied if the given value equals one.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class EqualsOneConstraint extends Threshold {

    @Override
    public Threshold decreaseBy(BigDecimal value) {
        throw new UnsupportedOperationException("Cannot decrease equals ONE constraint.");
    }

    @Override
    public boolean isSatisfied(BigDecimal value) {

        return (value.compareTo(BigDecimal.ONE) == 0);
    }

    @Override
    public int getJIOverlap(int size1, int size2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMinJIOverlap(int size1, int size2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public String toPlainString() {
        
        return "EQ0";
    }
}
