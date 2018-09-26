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
package org.urban.data.core.value.profiling.types;

import java.math.BigDecimal;

/**
 * The default numeric type test tries to instantiate a BigDecimal from a
 * given string. Returns true on success and false otherwise.
 * 
 * @author Heiko Mueller
 */
public class DefaultNumericValueTest implements NumericValueTest {

    @Override
    public boolean isNumeric(String value) {

	try {
	    new BigDecimal(value.replaceAll(",", ""));
	    return true;
	} catch (java.lang.NumberFormatException nfe) {
	    return false;
	}
    }
}
