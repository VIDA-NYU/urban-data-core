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

    public static final String GEQ = "GEQ";
    public static final String GT = "GT";
    
    public static ThresholdConstraint getConstraint(String spec) {
    
        String[] tokens = spec.split(":");
        if (tokens.length == 1) {
            return new GreaterThanConstraint(new BigDecimal(tokens[0]));
        } else if (tokens.length == 2) {
            String name = tokens[0];
            if (name.equalsIgnoreCase(GEQ)) {
                try {
                    return new GreaterOrEqualConstraint(new BigDecimal(tokens[1]));
                } catch (java.lang.NumberFormatException ex) {
                    throw new java.lang.IllegalArgumentException("Invalid constraint specification: " + ex);
                }
            } else if (name.equalsIgnoreCase(GT)) {
                try {
                    return new GreaterThanConstraint(new BigDecimal(tokens[1]));
                } catch (java.lang.NumberFormatException ex) {
                    throw new java.lang.IllegalArgumentException("Invalid constraint specification: " + ex);
                }
            } else {
                throw new java.lang.IllegalArgumentException("Unknown constring type: " + name);
            }
        }
        throw new java.lang.IllegalArgumentException("Invalid constraint specification: " + spec);
    }
    
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
    
    public static String validateCommand(String spec, String commandLine) {
        
        String[] tokens = spec.split(":");
        if (tokens.length == 1) {
            try {
                new BigDecimal(tokens[0]);
            } catch (java.lang.NumberFormatException ex) {
                System.out.println("Invalid constraint threshold: " + spec);
                System.out.println(commandLine);
                System.exit(-1);
            }
            return spec;
        } else if (tokens.length == 2) {
            String name = tokens[0];
            if (name.equalsIgnoreCase(GEQ)) {
            } else if (name.equalsIgnoreCase(GT)) {
            } else {
                System.out.println("Unknown comprator: " + name);
                System.out.println(commandLine);
                System.exit(-1);
            }
            try {
                new BigDecimal(tokens[1]);
            } catch (java.lang.NumberFormatException ex) {
                System.out.println("Invalid constraint threshold: " + spec);
                System.out.println(commandLine);
                System.exit(-1);
            }
            return spec;
        }
        System.out.println("Invalid constraint threshold: " + spec);
        System.out.println(commandLine);
        System.exit(-1);
        return null;
    }
}
