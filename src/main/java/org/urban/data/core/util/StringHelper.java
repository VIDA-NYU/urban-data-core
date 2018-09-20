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

import java.util.List;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public final class StringHelper {
    
    public static boolean isDigitOnly(String value) {
        
        for (char c : value.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isLetterOnly(String value) {
        
        for (char c : value.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLetterOrDigitOnly(String value) {
        
        for (char c : value.toCharArray()) {
            if ((!Character.isLetter(c)) && (!Character.isDigit(c))) {
                return false;
            }
        }
        return true;
    }

    public static String joinIntegers(List<Integer> columns) {
        
        if (columns.isEmpty()) {
            return "";
        }
        
        StringBuilder buf = new StringBuilder(Integer.toString(columns.get(0)));
        for (int iColumn = 1; iColumn < columns.size(); iColumn++) {
            buf.append(",").append(columns.get(iColumn));
        }
        return buf.toString();
    }

    public static String joinIntegers(int[] columns) {
        
        if (columns.length == 0) {
            return "";
        }
        
        StringBuilder buf = new StringBuilder(Integer.toString(columns[0]));
        for (int iColumn = 1; iColumn < columns.length; iColumn++) {
            buf.append(",").append(columns[iColumn]);
        }
        return buf.toString();
    }

    public static String joinStrings(List<String> tokens, int start, int end) {
        
        StringBuilder buf = new StringBuilder(tokens.get(start));
        for (int iToken = start + 1; iToken < end; iToken++) {
            buf.append(" ").append(tokens.get(iToken));
        }
        return buf.toString();
    }
    
    public static String joinStrings(List<String> tokens) {
        
        if (!tokens.isEmpty()) {
            return joinStrings(tokens, 0, tokens.size());
        } else {
            return "";
        }
    }
    
    public static String joinStrings(String[] tokens, String delim) {
        
        StringBuilder buf = new StringBuilder(tokens[0]);
        for (int iToken = 1; iToken < tokens.length; iToken++) {
            buf.append(delim).append(tokens[iToken]);
        }
        return buf.toString();
    }
    
    public static String joinStrings(String[] tokens, int start, String delim) {
        
        if (tokens.length > start) {
            StringBuilder buf = new StringBuilder(tokens[start]);
            for (int iToken = start + 1; iToken < tokens.length; iToken++) {
                buf.append(delim).append(tokens[iToken]);
            }
            return buf.toString();
        } else {
            return null;
        }
    }
    
    public static int[] splitIntegers(String text) {
        
        String[] tokens = text.split(",");
        
        int[] result = new int[tokens.length];
        for (int iToken = 0; iToken < tokens.length; iToken++) {
            result[iToken] = Integer.parseInt(tokens[iToken]);
        }
        
        return result;
    }
}
