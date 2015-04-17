package bs032_Tree;

public class GraphInfo {
	private int [][] edges; //pairs of connected nodes
	private int [] nodeSubtreeRefs;//corresponding ids of trees in STI array  
	private int [] subTreeIndices;//STI - tree's ids
//	for node i the big subtree, to which it's merged, will be accessible via
//	the NSR "referencer". Thus, changing the STI item k, we change it for the
//	whole bunch of node with NSR = k
	private int [] nodeCount;
	private int [] edgeCount;
	private int size;
	
	public final int getSize() {
		return size;
	}
	public final int getNumEdges() {
		return edges.length;
	}
	
	public GraphInfo (int sz, int [][] edges) {
		System.out.println("graph size: "+sz);
		this.edges = edges;
		size = sz;
		nodeSubtreeRefs = new int[size+1];
		nodeCount = new int [size+1];
		edgeCount = new int [size+1];
		subTreeIndices = new int [size+1];
		
		buildSubtreeIndex();
	}
	private int treeId(int node) {
		return subTreeIndices[ nodeSubtreeRefs[node] ];
	}
	
	private void setTreeIdForNode(int node, int newTreeId) {
		subTreeIndices[ nodeSubtreeRefs[node] ] = newTreeId;
	}
	
	private void addToExistingNode (int treeNode, int newNode) {
		nodeSubtreeRefs[newNode] = nodeSubtreeRefs[treeNode];//set ref
//		nodeCount[treeId(treeNode)]++;
//		edgeCount[treeId(treeNode)]++;
	}
	
	private void connectExistingNodes (int node1, int node2) {
//		nodeCount[treeId(node1)]+=nodeCount[treeId(node2)];
//		edgeCount[treeId(node1)]+=(edgeCount[treeId(node2)]+1);
		setTreeIdForNode(node2, treeId(node1));
	}
	private void buildSubtreeIndex() {
		int cst = 1; //curSubTree
		int numNodes=0;
		for (int i = 0; i < edges.length; i++) {
			int start = edges[i][0];
			int end = edges[i][1];
//			System.out.println("processing "+ start+" "+end);
			if (treeId(start)==0 && treeId(end)==0) {
//			if (nodeSubtreeRefs[edges[i][0]]==0 && 
//					nodeSubtreeRefs[edges[i][1]]==0) {
				nodeSubtreeRefs[edges[i][0]] = cst;
				nodeSubtreeRefs[edges[i][1]] = cst;
				subTreeIndices[cst]=cst++;
				numNodes+=2;
			}
//			else if (nodeSubtreeRefs[edges[i][0]]==0)
//				nodeSubtreeRefs[edges[i][1]]=nodeSubtreeRefs[edges[i][0]];
//			else if (nodeSubtreeRefs[edges[i][1]]==0)
//				nodeSubtreeRefs[edges[i][0]]=nodeSubtreeRefs[edges[i][1]];
			else if (treeId(start)==0) {
				numNodes++;
				addToExistingNode(end, start);
			}
			else if (treeId(end)==0) {
				numNodes++;
				addToExistingNode(start, end);
			}
//			else if (subTreeIndices[ nodeSubtreeRefs[edges[i][0]] ]!=
//					subTreeIndices[ nodeSubtreeRefs[edges[i][1]] ])
			else if (treeId(start)!=treeId(end))
				connectExistingNodes(start, end);
//				subTreeIndices[ nodeSubtreeRefs[edges[i][1]] ] = 
//				subTreeIndices[	nodeSubtreeRefs[edges[i][0]] ];
			else {
				System.out.println("cycle found");
				System.exit(1);
			}
		}
		System.out.println("Total nodes: "+size+
						"Connected: "+numNodes+
						"edges: "+edges.length);
	}
}
