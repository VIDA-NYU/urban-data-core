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

/**
 * Interface for different methods to test whether a string value is numeric.
 * 
 * @author Heiko Mueller
 */
public interface NumericValueTest {

    /**
     * Returns true if the given value is numeric according to the
     * implementation.
     * 
     * @param value
     * @return 
     */
    public boolean isNumeric(String value);
}
