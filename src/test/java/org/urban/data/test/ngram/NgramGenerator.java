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
package org.urban.data.test.ngram;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.urban.data.core.set.StringSet;
import org.urban.data.core.value.ngram.NGramGenerator;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class NgramGenerator {
    
    public NgramGenerator() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ngramTest() {
    
        StringSet ngrams = new NGramGenerator(3).getNGrams("abcde");
        assertEquals(3, ngrams.length());
        assertTrue(ngrams.contains("abc"));
        assertTrue(ngrams.contains("bcd"));
        assertTrue(ngrams.contains("cde"));

        ngrams = new NGramGenerator(4).getNGrams("abcde");
        assertEquals(2, ngrams.length());
        assertTrue(ngrams.contains("abcd"));
        assertTrue(ngrams.contains("bcde"));

        ngrams = new NGramGenerator(3).getNGrams("abcabc");
        assertEquals(3, ngrams.length());
        assertTrue(ngrams.contains("abc"));
        assertTrue(ngrams.contains("bca"));
        assertTrue(ngrams.contains("cab"));
        
        ngrams = new NGramGenerator(3).getPaddedNGrams("abcde");
        assertEquals(7, ngrams.length());
        assertTrue(ngrams.contains(NGramGenerator.START_CHAR + NGramGenerator.START_CHAR + "a"));
        assertTrue(ngrams.contains(NGramGenerator.START_CHAR + "ab"));
        assertTrue(ngrams.contains("abc"));
        assertTrue(ngrams.contains("bcd"));
        assertTrue(ngrams.contains("cde"));
        assertTrue(ngrams.contains("de" + NGramGenerator.END_CHAR));
        assertTrue(ngrams.contains("e" + NGramGenerator.END_CHAR + NGramGenerator.END_CHAR));
    }
}
