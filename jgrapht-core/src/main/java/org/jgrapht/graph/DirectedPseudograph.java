/*
 * (C) Copyright 2004-2016, by Christian Hammer and Contributors.
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
 * A directed pseudograph. A directed pseudograph is a non-simple directed graph in which both graph
 * loops and multiple edges are permitted. If you're unsure about pseudographs, see:
 * <a href="http://mathworld.wolfram.com/Pseudograph.html">
 * http://mathworld.wolfram.com/Pseudograph.html</a>.
 */
public class DirectedPseudograph<V, E>
    extends AbstractBaseGraph<V, E>
    implements DirectedGraph<V, E>
{
    private static final long serialVersionUID = -8300409752893486415L;

    /**
     * @see AbstractBaseGraph
     */
    public DirectedPseudograph(Class<? extends E> edgeClass)
    {
        this(new ClassBasedEdgeFactory<>(edgeClass));
    }

    /**
     * @see AbstractBaseGraph
     */
    public DirectedPseudograph(EdgeFactory<V, E> ef)
    {
        super(ef, true, true);
    }

    public static <V,
        E> DirectedGraphBuilderBase<V, E, ? extends DirectedPseudograph<V, E>, ?> builder(
            Class<? extends E> edgeClass)
    {
        return new DirectedGraphBuilder<>(new DirectedPseudograph<>(edgeClass));
    }

    public static <V,
        E> DirectedGraphBuilderBase<V, E, ? extends DirectedPseudograph<V, E>, ?> builder(
            EdgeFactory<V, E> ef)
    {
        return new DirectedGraphBuilder<>(new DirectedPseudograph<>(ef));
    }
}

// End DirectedPseudograph.java
