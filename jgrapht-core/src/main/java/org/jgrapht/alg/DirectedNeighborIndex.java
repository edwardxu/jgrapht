/*
 * (C) Copyright 2005-2016, by Charles Fry and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
package org.jgrapht.alg;

import java.util.*;

import org.jgrapht.*;
import org.jgrapht.alg.NeighborIndex.*;
import org.jgrapht.event.*;

/**
 * Maintains a cache of each vertex's neighbors. While lists of neighbors can be obtained from
 * {@link Graphs}, they are re-calculated at each invocation by walking a vertex's incident edges,
 * which becomes inordinately expensive when performed often.
 *
 * <p>
 * A vertex's neighbors are cached the first time they are asked for (i.e. the index is built on
 * demand). The index will only be updated automatically if it is added to the associated graph as a
 * listener. If it is added as a listener to a graph other than the one it indexes, results are
 * undefined.
 * </p>
 *
 * @author Charles Fry
 * @since Dec 13, 2005
 */
public class DirectedNeighborIndex<V, E>
    implements GraphListener<V, E>
{
    Map<V, Neighbors<V>> predecessorMap = new HashMap<>();
    Map<V, Neighbors<V>> successorMap = new HashMap<>();
    private DirectedGraph<V, E> graph;

    /**
     * Creates a neighbor index for the specified directed graph.
     *
     * @param g the graph for which a neighbor index is to be created.
     */
    public DirectedNeighborIndex(DirectedGraph<V, E> g)
    {
        graph = g;
    }

    /**
     * Returns the set of vertices which are the predecessors of a specified vertex. The returned
     * set is backed by the index, and will be updated when the graph changes as long as the index
     * has been added as a listener to the graph.
     *
     * @param v the vertex whose predecessors are desired
     *
     * @return all unique predecessors of the specified vertex
     */
    public Set<V> predecessorsOf(V v)
    {
        return getPredecessors(v).getNeighbors();
    }

    /**
     * Returns the set of vertices which are the predecessors of a specified vertex. If the graph is
     * a multigraph, vertices may appear more than once in the returned list. Because a list of
     * predecessors can not be efficiently maintained, it is reconstructed on every invocation by
     * duplicating entries in the neighbor set. It is thus more efficient to use
     * {@link #predecessorsOf(Object)} unless duplicate neighbors are required.
     *
     * @param v the vertex whose predecessors are desired
     *
     * @return all predecessors of the specified vertex
     */
    public List<V> predecessorListOf(V v)
    {
        return getPredecessors(v).getNeighborList();
    }

    /**
     * Returns the set of vertices which are the successors of a specified vertex. The returned set
     * is backed by the index, and will be updated when the graph changes as long as the index has
     * been added as a listener to the graph.
     *
     * @param v the vertex whose successors are desired
     *
     * @return all unique successors of the specified vertex
     */
    public Set<V> successorsOf(V v)
    {
        return getSuccessors(v).getNeighbors();
    }

    /**
     * Returns the set of vertices which are the successors of a specified vertex. If the graph is a
     * multigraph, vertices may appear more than once in the returned list. Because a list of
     * successors can not be efficiently maintained, it is reconstructed on every invocation by
     * duplicating entries in the neighbor set. It is thus more efficient to use
     * {@link #successorsOf(Object)} unless duplicate neighbors are required.
     *
     * @param v the vertex whose successors are desired
     *
     * @return all successors of the specified vertex
     */
    public List<V> successorListOf(V v)
    {
        return getSuccessors(v).getNeighborList();
    }

    /**
     * @see GraphListener#edgeAdded(GraphEdgeChangeEvent)
     */
    @Override
    public void edgeAdded(GraphEdgeChangeEvent<V, E> e)
    {
        E edge = e.getEdge();
        V source = graph.getEdgeSource(edge);
        V target = graph.getEdgeTarget(edge);

        // if a map does not already contain an entry,
        // then skip addNeighbor, since instantiating the map
        // will take care of processing the edge (which has already
        // been added)

        if (successorMap.containsKey(source)) {
            getSuccessors(source).addNeighbor(target);
        } else {
            getSuccessors(source);
        }
        if (predecessorMap.containsKey(target)) {
            getPredecessors(target).addNeighbor(source);
        } else {
            getPredecessors(target);
        }
    }

    /**
     * @see GraphListener#edgeRemoved(GraphEdgeChangeEvent)
     */
    @Override
    public void edgeRemoved(GraphEdgeChangeEvent<V, E> e)
    {
        V source = e.getEdgeSource();
        V target = e.getEdgeTarget();
        if (successorMap.containsKey(source)) {
            successorMap.get(source).removeNeighbor(target);
        }
        if (predecessorMap.containsKey(target)) {
            predecessorMap.get(target).removeNeighbor(source);
        }
    }

    /**
     * @see VertexSetListener#vertexAdded(GraphVertexChangeEvent)
     */
    @Override
    public void vertexAdded(GraphVertexChangeEvent<V> e)
    {
        // nothing to cache until there are edges
    }

    /**
     * @see VertexSetListener#vertexRemoved(GraphVertexChangeEvent)
     */
    @Override
    public void vertexRemoved(GraphVertexChangeEvent<V> e)
    {
        predecessorMap.remove(e.getVertex());
        successorMap.remove(e.getVertex());
    }

    private Neighbors<V> getPredecessors(V v)
    {
        Neighbors<V> neighbors = predecessorMap.get(v);
        if (neighbors == null) {
            neighbors = new Neighbors<>(Graphs.predecessorListOf(graph, v));
            predecessorMap.put(v, neighbors);
        }
        return neighbors;
    }

    private Neighbors<V> getSuccessors(V v)
    {
        Neighbors<V> neighbors = successorMap.get(v);
        if (neighbors == null) {
            neighbors = new Neighbors<>(Graphs.successorListOf(graph, v));
            successorMap.put(v, neighbors);
        }
        return neighbors;
    }
}

// End DirectedNeighborIndex.java
