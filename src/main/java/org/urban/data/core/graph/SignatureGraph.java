/*
 * Copyright 2018 Heiko Mueller <heiko.mueller@nyu.edu>.
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
package org.urban.data.core.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.urban.data.core.set.IDSet;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class SignatureGraph extends AdjacencyGraph {

    private final boolean[][] _edges;
    private final HashMap<Integer, Integer> _nodeToIndexMap;
    private final List<Integer> _indexToNodeMap;
    private final boolean _reversed;
    
    public SignatureGraph(IDSet nodes) {
	
        super(nodes);

        _edges = new boolean[nodes.length()][nodes.length()];
        _reversed = false;

        _indexToNodeMap = nodes.toSortedList();
        _nodeToIndexMap = new HashMap<>();
        int index = 0;
        for (int nodeId : _indexToNodeMap) {
            _nodeToIndexMap.put(nodeId, index++);
        }
    }
    
    public SignatureGraph(IDSet nodes, boolean[][] edges, HashMap<Integer, Integer> nodeToIndexMap, List<Integer> indexToNodeMap) {
	
        super(nodes);

        _edges = edges;
        _reversed = true;
        _nodeToIndexMap = nodeToIndexMap;
        _indexToNodeMap = indexToNodeMap;
    }
    
    @Override
    public Iterable<Integer> adjacent(int nodeId) {

        ArrayList<Integer> edges = new ArrayList<>();

        int nodeIndex = _nodeToIndexMap.get(nodeId);
        if (_reversed) {
            for (int iNode = 0; iNode < _edges.length; iNode++) {
                if (_edges[iNode][nodeIndex]) {
                    edges.add(_indexToNodeMap.get(iNode));
                }
            }
        } else {
            for (int iNode = 0; iNode < _edges.length; iNode++) {
                if (_edges[nodeIndex][iNode]) {
                    edges.add(_indexToNodeMap.get(iNode));
                }
            }
        }
        return edges;
    }
    
    public void edge(int sourceId, int targetId) {
	
        _edges[_nodeToIndexMap.get(sourceId)][_nodeToIndexMap.get(targetId)] = true;
    }

    @Override
    public boolean hasEdge(int sourceId, int targetId) {
	
        int sourceIndex = _nodeToIndexMap.get(sourceId);
        int targetIndex = _nodeToIndexMap.get(targetId);
        if (_reversed) {
            return _edges[targetIndex][sourceIndex];
        } else {
            return _edges[sourceIndex][targetIndex];
        }
    }

    @Override
    public AdjacencyGraph reverse() {

        return new SignatureGraph(
            this.nodes(),
            _edges,
            _nodeToIndexMap,
            _indexToNodeMap
        );
    }    
}
