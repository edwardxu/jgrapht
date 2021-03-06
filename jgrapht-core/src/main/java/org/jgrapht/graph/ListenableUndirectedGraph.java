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

/**
 * An undirected graph which is also {@link org.jgrapht.ListenableGraph}.
 *
 * @see DefaultListenableGraph
 */
public class ListenableUndirectedGraph<V, E>
    extends DefaultListenableGraph<V, E>
    implements UndirectedGraph<V, E>
{
    private static final long serialVersionUID = 3256999969193145905L;

    /**
     * Creates a new listenable undirected simple graph.
     *
     * @param edgeClass class on which to base factory for edges
     */
    public ListenableUndirectedGraph(Class<? extends E> edgeClass)
    {
        this(new SimpleGraph<>(edgeClass));
    }

    /**
     * Creates a new listenable undirected graph.
     *
     * @param base the backing graph.
     */
    public ListenableUndirectedGraph(UndirectedGraph<V, E> base)
    {
        super(base);
    }
}

// End ListenableUndirectedGraph.java
