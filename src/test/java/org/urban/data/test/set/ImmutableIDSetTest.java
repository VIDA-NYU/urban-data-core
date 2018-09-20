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
package org.urban.data.test.set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.ImmutableIDSet;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class ImmutableIDSetTest {
    
    public ImmutableIDSetTest() {
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
    public void testContainment() {
    
        IDSet set = new ImmutableIDSet(new Integer[]{1,2,3,5,7,10});
        IDSet emptySet = new ImmutableIDSet(new Integer[]{});
        
        for (int id : new Integer[]{1,2,3,5,7,10}) {
            assertTrue(set.contains(id));
            assertFalse(emptySet.contains(id));
        }
        for (int id : new Integer[]{4,6,8,9}) {
            assertFalse(set.contains(id));
            assertFalse(emptySet.contains(id));
        }
        
        IDSet set1 = new ImmutableIDSet(new Integer[]{13439});
        IDSet set2 = new ImmutableIDSet(new Integer[]{13439, 14179});
        
        assertTrue(set2.contains(set1));
    }
}
