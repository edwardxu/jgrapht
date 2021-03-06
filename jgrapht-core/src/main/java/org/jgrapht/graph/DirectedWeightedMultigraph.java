/*
 * (C) Copyright 2003-2016, by Barak Naveh and Contributors.
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
package org.jgrapht.graph;

import org.jgrapht.*;
import org.jgrapht.graph.builder.*;

/**
 * A directed weighted multigraph. A directed weighted multigraph is a non-simple directed graph in
 * which no loops are permitted, but multiple edges between any two vertices are permitted, and
 * edges have weights.
 */
public class DirectedWeightedMultigraph<V, E>
    extends DirectedMultigraph<V, E>
    implements WeightedGraph<V, E>
{
    private static final long serialVersionUID = 4049071636005206066L;

    /**
     * Creates a new directed weighted multigraph.
     *
     * @param edgeClass class on which to base factory for edges
     */
    public DirectedWeightedMultigraph(Class<? extends E> edgeClass)
    {
        this(new ClassBasedEdgeFactory<>(edgeClass));
    }

    /**
     * Creates a new directed weighted multigraph with the specified edge factory.
     *
     * @param ef the edge factory of the new graph.
     */
    public DirectedWeightedMultigraph(EdgeFactory<V, E> ef)
    {
        super(ef);
    }

    public static <V, E> DirectedWeightedGraphBuilderBase<V, E,
        ? extends DirectedWeightedMultigraph<V, E>, ?> builder(Class<? extends E> edgeClass)
    {
        return new DirectedWeightedGraphBuilder<>(new DirectedWeightedMultigraph<>(edgeClass));
    }

    public static <V, E> DirectedWeightedGraphBuilderBase<V, E,
        ? extends DirectedWeightedMultigraph<V, E>, ?> builder(EdgeFactory<V, E> ef)
    {
        return new DirectedWeightedGraphBuilder<>(new DirectedWeightedMultigraph<>(ef));
    }
}

// End DirectedWeightedMultigraph.java
