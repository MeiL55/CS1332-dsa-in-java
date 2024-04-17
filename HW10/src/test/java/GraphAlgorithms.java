import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Mei Li
 * @version 1.0
 * @userid mli783
 * @GTID 903904935
 *
 * Collaborators:
 *
 * Resources:
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input
     *                                  is null, or if start doesn't exist in
     *                                  the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Cannot search null graph.");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                    "Graph does not contain start vertex");
        }
        List<Vertex<T>> list = new ArrayList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        queue.add(start);
        list.add(start);
        while (!queue.isEmpty()) {
            Vertex<T> curr = queue.peek();
            if (curr == null) {
                throw new IllegalArgumentException(
                        "Cannot search with null vertex");
            }
            List<VertexDistance<T>> adj = adjList.get(curr);
            for (VertexDistance<T> vd : adj) {
                if (!list.contains(vd.getVertex())) {
                    queue.add(vd.getVertex());
                    list.add(vd.getVertex());
                }
            }
            queue.remove();
        }
        return list;
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input
     *                                  is null, or if start doesn't exist in
     *                                  the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Cannot search null graph.");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                    "Graph does not contain start vertex");
        }
        List<Vertex<T>> list = new ArrayList<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        Set<Vertex<T>> vlist = new HashSet<>();
        dfsHelp(start, vlist, list, adjList);
        return list;
    }

    /**
     * Helper recursive method for DFS
     *
     * @param curr     The current vertex
     * @param vlist    Visited list
     * @param list     Final list
     * @param adjList  Adjacency list
     * @param <T>      Type T
     */
    private static <T> void dfsHelp(Vertex<T> curr,
                                    Set<Vertex<T>> vlist,
                                    List<Vertex<T>> list,
                                    Map<Vertex<T>,
                                            List<VertexDistance<T>>> adjList) {
        list.add(curr);
        vlist.add(curr);
        for (VertexDistance<T> v: adjList.get(curr)) {
            if (!vlist.contains(v.getVertex())) {
                dfsHelp(v.getVertex(), vlist, list, adjList);
            }
        }
    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Cannot search null graph.");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                    "Graph does not contain start vertex");
        }
        Map<Vertex<T>, Integer> map = new HashMap<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        Queue<VertexDistance<T>> pqueue = new PriorityQueue<>();
        List<Vertex<T>> vlist = new ArrayList<>();
        for (Vertex<T> vertex : adjList.keySet()) {
            if (vertex.equals(start)) {
                map.put(vertex, 0);
            } else {
                map.put(vertex, Integer.MAX_VALUE);
            }
        }
        pqueue.add(new VertexDistance<>(start, 0));
        while (vlist.size() < adjList.size() && !(pqueue.isEmpty())) {
            VertexDistance<T> curr = pqueue.remove();
            vlist.add(curr.getVertex());
            for (VertexDistance<T> v : adjList.get(curr.getVertex())) {
                int newDistance = curr.getDistance() + v.getDistance();
                if (!vlist.contains(v.getVertex()) &&  map.get(v.getVertex()) > newDistance) {
                    map.put(v.getVertex(), newDistance);
                    pqueue.add(new VertexDistance<>(v.getVertex(),
                            newDistance));
                }
            }
        }
        return map;
    }
    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use PriorityQueue, java.util.Set, and any class that
     * implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Invalid Input.");
        }
        Set<Edge<T>> returnSet = new HashSet<>();
        Queue<Edge<T>> queue = new PriorityQueue<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Map<Vertex<T>, List<VertexDistance<T>>> vMap = graph.getAdjList();

        for (VertexDistance<T> v: vMap.get(start)) {
            queue.add(new Edge<>(start, v.getVertex(), v.getDistance()));
        }

        visitedSet.add(start);

        while (!queue.isEmpty()
                && !(visitedSet.size() == graph.getVertices().size())) {
            Edge<T> currEdge = queue.remove();
            if (!visitedSet.contains(currEdge.getV())) {
                visitedSet.add(currEdge.getV());
                returnSet.add(new Edge<>(currEdge.getU(), currEdge.getV(),
                        currEdge.getWeight()));
                returnSet.add(new Edge<>(currEdge.getV(), currEdge.getU(),
                        currEdge.getWeight()));

                for (VertexDistance<T> v: vMap.get(currEdge.getV())) {
                    if (!visitedSet.contains(v.getVertex())) {
                        queue.add(new Edge<>(currEdge.getV(), v.getVertex(),
                                v.getDistance()));
                    }
                }
            }
        }

        if ((returnSet.size() / 2) == graph.getVertices().size() - 1) {
            return returnSet;
        } else {
            return null;
        }
    }
}