package sud.exmp1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

Class Main {
	
	public void bfs()
	{
		// BFS uses Queue data structure
		Queue queue = new LinkedList();
		queue.add(this.rootNode);
		printNode(this.rootNode);
		rootNode.visited = true;
		while(!queue.isEmpty()) {
			Node node = (Node)queue.remove();
			Node child=null;
			while((child=getUnvisitedChildNode(node))!=null) {
				child.visited=true;
				printNode(child);
				queue.add(child);
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}

	public void dfs() {
		// DFS uses Stack data structure
		Stack stack = new Stack();
		stack.push(this.rootNode);
		rootNode.visited=true;
		printNode(rootNode);
		while(!stack.isEmpty()) {
			Node node = (Node)s.peek();
			Node child = getUnvisitedChildNode(n);
			if(child != null) {
				child.visited = true;
				printNode(child);
				s.push(child);
			}
			else {
				s.pop();
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}
	
	enum VertexState {
        White, Gray, Black 
    }
   
    public void DFS()
    {
          VertexState state[] = new VertexState[vertexCount];
          for (int i = 0; i < vertexCount; i++)
                state[i] = VertexState.White;
          runDFS(0, state);
    }
   
    public void runDFS(int u, VertexState[] state)
    {
          state[u] = VertexState.Gray;
          for (int v = 0; v < vertexCount; v++)
                if (isEdge(u, v) && state[v] == VertexState.White)
                      runDFS(v, state);
          state[u] = VertexState.Black;
    }
}


Class Node {
   Char data;
   Public Node(char c) {
      this.data=c;
   }
}