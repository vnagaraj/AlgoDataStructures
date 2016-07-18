package week1;

import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	class TreeNode{
		int key;
		ArrayList<TreeNode> children;

		TreeNode(int key){
			this.key = key;
			children = new ArrayList<TreeNode>();
		}

		void setChild(TreeNode treeNode){
			children.add(treeNode);
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		TreeNode[] getTreeNodes(int n){
			TreeNode[] treeNodes = new TreeNode[n];
			for (int i=0; i< treeNodes.length; i++){
				treeNodes[i] = new TreeNode(i);
			}
			return treeNodes;
		}

		TreeNode getRootTreeNode(TreeNode[] treeNodes, int[] parent){
			TreeNode root = null;
			for(int i=0; i< parent.length; i++){
				int par = parent[i];
				if (par == -1){
					root = treeNodes[i];
					continue;
				}
				treeNodes[par].setChild(treeNodes[i]);
			}
			return root;
		}

		int compHeight(TreeNode treeNode){
			if (treeNode == null){
				return 0;
			}
			int max = 0;
			for (TreeNode child : treeNode.children){
				int val = compHeight(child);
				if (val > max){
					max = val;
				}
			}
			return 1 + max;
		}

		int computeHeight() {
			TreeNode[] treeNodes = getTreeNodes(n);
			TreeNode root = getRootTreeNode(treeNodes, parent);
			return compHeight(root);
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
