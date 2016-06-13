public class FindPath {

	public class BinaryTreeNode {
		int nodeValue;
		BinaryTreeNode leftChild;
		BinaryTreeNode rightChild;
	}

	boolean findPath(BinaryTreeNode root, int expectSum) {
		if (root == null)
			return false;
		Stack<Integer> path = null;
		int currentSum = 0;
		boolean isFound = false;
		findPath(root, expectSum, path, currentSum, isFound);
		return isFound;
	}

	void findPath(BinaryTreeNode root, int expectSum, Stack<Integer> path, int currentSum, boolean isFound) {
		currentSum += root.nodeValue;
		path.push(root.nodeValue);

		boolean isLeaf = root.leftChild == null && root.rightChild == null;

		if (currentSum == expectSum && isLeaf)
			isFound = true;
		// print all the element in path

		// continue with child if not leaf
		if (root.leftChild != null)
			findPath(root.leftChild, expectSum, path, currentSum, isFound);
		if (root.rightChild != null)
			findPath(root.rightChild, expectSum, path, currentSum, isFound);

		// pop and minus the node and return to its parent
		currentSum -= root.nodeValue;
		path.pop();
	}
}
