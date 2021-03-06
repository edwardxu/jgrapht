/*
 * (C) Copyright 2015-2016, by Andrew Chen and Contributors.
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
package org.jgrapht.graph.builder;

import org.jgrapht.*;

/**
 * Base class for {@link UndirectedWeightedGraphBuilder} for extending.
 */
public abstract class UndirectedWeightedGraphBuilderBase<V, E,
    G extends UndirectedGraph<V, E> & WeightedGraph<V, E>,
    B extends UndirectedWeightedGraphBuilderBase<V, E, G, B>>
    extends UndirectedGraphBuilderBase<V, E, G, B>
{
    /**
     * Creates a builder based on {@code baseGraph}. {@code baseGraph} must be mutable.
     *
     * @param baseGraph the graph object to base building on
     */
    public UndirectedWeightedGraphBuilderBase(G baseGraph)
    {
        super(baseGraph);
    }

    /**
     * Adds an weighted edge to the graph being built. The source and target vertices are added to
     * the graph, if not already included.
     *
     * @param source source vertex of the edge.
     * @param target target vertex of the edge.
     * @param weight weight of the edge.
     *
     * @return this builder object
     *
     * @see Graphs#addEdgeWithVertices(Graph, Object, Object, double)
     */
    public B addEdge(V source, V target, double weight)
    {
        Graphs.addEdgeWithVertices(this.graph, source, target, weight);
        return this.self();
    }
}

// End UndirectedWeightedGraphBuilderBase.java
