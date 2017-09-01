import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
    // you will need some private fields to represent the graph
    // you are also likely to want some private helper methods

	Map<Vertex, List<Edge>> hash = new HashMap<Vertex, List<Edge>>();
    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
	// YOUR CODE HERE
    	for(Vertex vertex : v){
    		if(!hash.containsKey(v)){
	        	List<Edge> temp = new ArrayList<Edge>();
		    	for(Edge edge : e){
		    		if(!v.contains(edge.getSource()) && !v.contains(edge.getDestination())){
		    			throw new IllegalArgumentException("edge " + edge.toString() + " contains no vertecies which exist in the collection passed");
		    		}
		    		else if(!v.contains(edge.getSource())){
		    			throw new IllegalArgumentException("edge " + edge.toString() + " contains vertex: " + edge.getSource() + " but it doesn't exist.");
		    		}
		    		else if(!v.contains(edge.getDestination())){
		    			throw new IllegalArgumentException("edge " + edge.toString() + " contains vertex: " + edge.getDestination() + " but it doesn't exist.");
		    		}
		    		if(edge.getWeight() < 0){
		    			throw new IllegalArgumentException("edge " + edge.toString() + " has a negative weight");
		    		}
		    		for(Edge check : temp){
		    			if(check.getDestination() == edge.getDestination() && check.getSource() == edge.getSource()){
		    				if(check.getWeight() != edge.getWeight()){
		    					throw new IllegalArgumentException("Edge " + edge.toString() + " has same origin and source as edge: " + check.toString() + " but different weight, which is not allowed");
		    				}
		    				else{
		    					edge.setAdd(false);
		    				}
		    			}
		    		}
		    		if(edge.getSource().equals(vertex) && edge.getAdd()){
		    			temp.add(edge);
		    		}
		    	}
		    	hash.put(vertex, temp);
	    	}
    	}

    }

    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
    	return hash.keySet();
	// YOUR CODE FROM HW4 HERE

    }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {

	// YOUR CODE FROM HW4 HERE
    	List<Edge> list = new ArrayList<Edge>();
    	for(List<Edge> edges : hash.values()){
    		for(Edge edge : edges){
    			list.add(edge);
    		}
    	}
    	return list;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {

	// YOUR CODE FROM HW4 HERE
    	if(v == null || !hash.containsKey(v)){
    		throw new IllegalArgumentException("V must exist");
    	}
    	Set<Vertex> neighbors = new HashSet<Vertex>();
    	for(Edge edges : hash.get(v)){
    		neighbors.add(edges.getDestination());
    	}
    	return neighbors;
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) {

	// YOUR CODE FROM HW4 HERE
    	if(a == null || !hash.containsKey(a) || b == null || !hash.containsKey(b)){
    		throw new IllegalArgumentException("A or B does not exist");
    	}
    	for(Edge e : hash.get(a)){
    		if(e.getDestination().equals(b)){
    			return e.getWeight();
    		}
    	}
    	return -1;
    }

    /**
     * Returns the shortest path from a to b in the graph, or null if there is
     * no such path.  Assumes all edge weights are nonnegative.
     * Uses Dijkstra's algorithm.
     * @param a the starting vertex
     * @param b the destination vertex
     * @return a Path where the vertices indicate the path from a to b in order
     *   and contains a (first) and b (last) and the cost is the cost of 
     *   the path. Returns null if b is not reachable from a.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public Path shortestPath(Vertex a, Vertex b) {
    	if(a.equals(b)){
    		List<Vertex> temp = new ArrayList<Vertex>();
    		temp.add(a);
    		return new Path(temp, 0);
    	}
    	else if(a == null || b == null){
    		throw new IllegalArgumentException("vertices must exist");
    	}
    	else{
    		Set<Vertex> decided = new HashSet<>();
    		List<Vertex> list = new LinkedList<>();
    		Map<Vertex, Integer> costs = new HashMap<>();
    		Map<Vertex, Vertex> route = new HashMap<>();
    		for(Vertex v : vertices()){
    			costs.put(v, Integer.MAX_VALUE);
    			route.put(v, null);
    		}
    		list.add(a);
    		decided.add(a);
    		costs.put(a, 0);
    		Vertex destination = null;
    		while(!list.isEmpty()){
    			Vertex current = getLeastCost(list, costs);
    			list.remove(current);
    			decided.add(current);
    			Collection<Vertex> temp = adjacentVertices(current);
    			for(Vertex v : temp){
    				if(!decided.contains(v)){
    					list.add(v);
    				}
    				if(costs.get(v) > edgeCost(current, v) + costs.get(current)){
    					costs.put(v, edgeCost(current, v) + costs.get(current));
    					route.put(v, current);
    					if(v.equals(b)){
    						destination = v;
    					}
    				}
    			}
    		}
    		Stack<Vertex> stack = new Stack<>();
    		stack.push(b);
    		while(!destination.equals(a)){
    			stack.push(route.get(destination));
    			destination = route.get(destination);
    		}
    		List<Vertex> finalRoute = new ArrayList<>();
    		int cost = costs.get(b);
    		while(!stack.isEmpty()){
    			finalRoute.add(stack.pop());
    		}
    		return new Path(finalRoute, cost);
    	}
    }
    
    private Vertex getLeastCost(Collection<Vertex> list, Map<Vertex, Integer> costs){
    	Vertex temp = null;
    	for(Vertex v : list){
    		if(temp == null){
    			temp = v;
    		}
    		if(costs.get(temp) > costs.get(v)){
    			temp = v;
    		}
    	}
    	return temp;
    }

}
