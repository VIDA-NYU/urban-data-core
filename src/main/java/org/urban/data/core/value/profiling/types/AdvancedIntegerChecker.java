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
 * Slightly advanced implementation that checks whether values in a column are
 * of type Integer. Removes all ','  and uses Integer.parseInt() to check if the
 * result is an Integer.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class AdvancedIntegerChecker extends IntegerChecker {

    @Override
    public boolean isMatch(String value) {

	try {
	    this.matchedValue(Integer.parseInt(value.replaceAll(",", "")));
	    return true;
	} catch (java.lang.NumberFormatException ex) {
	    return false;
	}
    }
}
