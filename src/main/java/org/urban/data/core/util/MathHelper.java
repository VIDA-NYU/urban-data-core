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
package org.urban.data.core.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Helper methods for math operations.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public final class MathHelper {
    
    public static BigDecimal div(int dividend, int divisor) {
        
        return new BigDecimal(dividend)
                .divide(new BigDecimal(divisor), MathContext.DECIMAL64);
    }
    
    public static BigDecimal div(long dividend, long divisor) {
        
        return new BigDecimal(dividend)
                .divide(new BigDecimal(divisor), MathContext.DECIMAL64);
    }
    
    public static BigDecimal f1(BigDecimal precision, BigDecimal recall) {
        
        return new BigDecimal(2)
                .multiply(precision)
                .multiply(recall)
                .divide(precision.add(recall), MathContext.DECIMAL64);
    }
}
