/*
 * (C) Copyright 2015-2016, by Barak Naveh and Contributors.
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
package org.jgrapht.graph.specifics;

import java.io.*;
import java.util.*;

/**
 * .
 *
 * @author Barak Naveh
 */
public abstract class Specifics<V, E>
    implements Serializable
{
    private static final long serialVersionUID = 785196247314761183L;

    public abstract void addVertex(V vertex);

    public abstract Set<V> getVertexSet();

    /**
     * .
     *
     * @param sourceVertex
     * @param targetVertex
     *
     * @return
     */
    public abstract Set<E> getAllEdges(V sourceVertex, V targetVertex);

    /**
     * .
     *
     * @param sourceVertex
     * @param targetVertex
     *
     * @return
     */
    public abstract E getEdge(V sourceVertex, V targetVertex);

    /**
     * Adds the specified edge to the edge containers of its source and target vertices.
     *
     * @param e
     */
    public abstract void addEdgeToTouchingVertices(E e);

    /**
     * .
     *
     * @param vertex
     *
     * @return
     */
    public abstract int degreeOf(V vertex);

    /**
     * .
     *
     * @param vertex
     *
     * @return
     */
    public abstract Set<E> edgesOf(V vertex);

    /**
     * .
     *
     * @param vertex
     *
     * @return
     */
    public abstract int inDegreeOf(V vertex);

    /**
     * .
     *
     * @param vertex
     *
     * @return
     */
    public abstract Set<E> incomingEdgesOf(V vertex);

    /**
     * .
     *
     * @param vertex
     *
     * @return
     */
    public abstract int outDegreeOf(V vertex);

    /**
     * .
     *
     * @param vertex
     *
     * @return
     */
    public abstract Set<E> outgoingEdgesOf(V vertex);

    /**
     * Removes the specified edge from the edge containers of its source and target vertices.
     *
     * @param e
     */
    public abstract void removeEdgeFromTouchingVertices(E e);
}
