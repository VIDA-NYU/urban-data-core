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
package org.urban.data.profiling.types;

import org.urban.data.core.value.ValueCounter;

/**
 * Abstract class for counting matches with aggregated column value sets.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class MatchCounter {
   
    /**
     * Representations for different match types:
     * - ALL : All tested values passed test
     * - ALL_BUT_ONE: All (at least one) but one tested values passed test
     * - NONE: No matches
     * - PARTIAL: At least one value passed test
     */
    public static final String MATCH_TYPE_ALL = "ALL";
    public static final String MATCH_TYPE_ALL_BUT_ONE = "ALL-BUT-ONE";
    public static final String MATCH_TYPE_NONE = "NONE";
    public static final String MATCH_TYPE_PARTIAL = "PARTIAL";
    
    private ValueCounter _lastUnmatched = null;
    private int _valuesMatchedDistinct = 0;
    private int _valuesMatchedTotal = 0;
    private int _valuesTestedDistinct = 0;
    private int _valuesTestedTotal = 0;
    
    /**
     * True if all tested values passed the data type test.
     * 
     * @return 
     */
    public boolean allMatched() {
	
	return (_valuesMatchedDistinct == _valuesTestedDistinct);
    }

    /**
     * True if all but one of the tested values passed the data type test. The
     * result is false if the total number of values tested is zero.
     * 
     * @return 
     */
    public boolean allMatchedExceptOne() {
	
	return ((_valuesMatchedDistinct > 0) && (_valuesMatchedDistinct == (_valuesTestedDistinct -1)));
    }

    /**
     * Consume value and the test result. Adjusted the internal counters.
     * 
     * @param value
     * @param isMatch 
     */
    public void consume(ValueCounter value, boolean isMatch) {
	
	_valuesTestedDistinct++;
	_valuesTestedTotal += value.getCount();
	if (isMatch) {
	    _valuesMatchedDistinct++;
	    _valuesMatchedTotal += value.getCount();
	} else {
	    _lastUnmatched = value;
	}
    }
    
    /**
     * Number of distinct values that successfully passed the checkers test.
     * 
     * @return 
     */
    public int getDistinctValuesMatched() {
	
	return _valuesMatchedDistinct;
    }
    
    /**
     * Number of distinct values tested.
     * 
     * @return 
     */
    public int getDistinctValuesTested() {
	
	return _valuesTestedDistinct;
    }
    
    /**
     * Fraction of distinct values that passed the checker test successfully.
     * 
     * @return 
     */
    public double getFractionDistinctMatched() {
	
	if (_valuesTestedDistinct > 0) {
	    return ((double)_valuesMatchedDistinct) / ((double)_valuesTestedDistinct);
	} else {
	    return 0;
	}
    }
    
    /**
     * Fraction of total values that passed the checker test successfully.
     * 
     * @return 
     */
    public double getFractionTotalMatched() {
	
	if (_valuesTestedTotal > 0) {
	    return ((double)_valuesMatchedTotal) / ((double)_valuesTestedTotal);
	} else {
	    return 0;
	}
    }
    
    /**
     * Last consumed value that did not match the evaluated condition. The
     * result is null if all tested values were matches.
     * 
     * @return 
     */
    public ValueCounter getLastUnmatched() {
	
	return _lastUnmatched;
    }
    
    /**
     * Total number of values that successfully passed the checkers test.
     * 
     * @return 
     */
    public int getTotalValuesMatched() {
	
	return _valuesMatchedTotal;
    }
    
    /**
     * Total number of values tested.
     * 
     * @return 
     */
    public int getTotalValuesTested() {
	
	return _valuesTestedTotal;
    }

    /**
     * True if at least one of the tested values matched the data type.
     * 
     * @return 
     */
    public boolean hasMatches() {
	
	return (_valuesMatchedDistinct > 0);
    }
}
