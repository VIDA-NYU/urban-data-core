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
package org.urban.data.core.io.json;

import com.google.gson.JsonPrimitive;

/**
 * Consumer for JSON primitives as emitted by a {@link JsonPrimitiveEmitter}.
 * 
 * @author Heiko Mueller
 */
public interface JsonPrimitiveConsumer {
    
    /**
     * Consume a given primitive. The path specifies the access path to the
     * primitive in the processed document.
     * 
     * @param path
     * @param element 
     */
    public void consume(String path, JsonPrimitive element);
}
