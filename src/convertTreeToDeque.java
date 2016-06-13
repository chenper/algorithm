public class convertTreeToDeque {
	public class BinaryTreeNode{
		int value;
		BinaryTreeNode leftNode;
		BinaryTreeNode rightNode;
	}
	
	BinaryTreeNode convert(BinaryTreeNode root){
		BinaryTreeNode lastNodeInList = null;
		convertNode(root, lastNodeInList);
		
		//得到尾节点，返回头结点
		BinaryTreeNode headNode = lastNodeInList;
		while(headNode != null && headNode.leftNode != null){
			headNode = headNode.leftNode;
		}
		
		return headNode;
	}
	
	void convertNode(BinaryTreeNode node, BinaryTreeNode lastNodeInList){
		if (node == null)
			return;
		
		//每次迭代新建一个节点
		BinaryTreeNode currentNode = node;
		
		//按中序顺序遍历
		if (currentNode.leftNode != null)
			convertNode(currentNode.leftNode, lastNodeInList);
		
		//双向连接
		currentNode.leftNode = lastNodeInList;
		
		if(lastNodeInList != null)
			lastNodeInList.rightNode = currentNode;
		
		lastNodeInList = currentNode;
		
		if (currentNode.rightNode !=null)
			convertNode(currentNode.rightNode, lastNodeInList);
	}
}
