
public class CloneComplexList {
	public class  ComplexListNode{
		int value;
		ComplexListNode nextNode;
		ComplexListNode siblingNode;
	}
	
	void cloneNode(ComplexListNode headNode){
		ComplexListNode node = headNode;
		
		while (node != null){
			ComplexListNode cloneNode = new ComplexListNode();
			
			cloneNode.value = node.value;
			cloneNode.nextNode = node.nextNode;
			cloneNode.siblingNode = null;
			
			node.nextNode = cloneNode;
			node = cloneNode.nextNode;
		}
	}
	
	void connectSibLing(ComplexListNode headNode){
		ComplexListNode node = headNode;
		while(node != null){
			ComplexListNode cloneNode = node.nextNode;
			if (node.siblingNode != null)
				cloneNode.siblingNode = node.siblingNode.nextNode;
			node = cloneNode.nextNode;
		}
	}
	
	ComplexListNode reconnectNode(ComplexListNode headNode){
		ComplexListNode node = headNode;
		ComplexListNode cloneNode = null;
		ComplexListNode cloneHead = null;
		
		if (node != null){
			cloneHead = cloneNode = node.nextNode;
			node.nextNode = cloneNode.nextNode;
			node = node.nextNode;
		}
		
		while(node != null){
			cloneNode.nextNode = node.nextNode;
			cloneNode = cloneNode.nextNode;
			node.nextNode = cloneNode.nextNode;
			node = node.nextNode;
		}
		
		return cloneHead;
	}
	
	ComplexListNode clone(ComplexListNode headNode){
		cloneNode(headNode);
		connectSibLing(headNode);
		return reconnectNode(headNode);
	}
}
