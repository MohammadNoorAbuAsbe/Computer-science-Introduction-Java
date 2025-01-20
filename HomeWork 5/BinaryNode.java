/*---------------------------------------
 Genuine author: Mohammad Noor, I.D.: 315226472
 Date: xx-xx-2020 
---------------------------------------*/
public class BinaryNode<T> {

    protected T data;
    protected BinaryNode<T> left;
    protected BinaryNode<T> right;

    public BinaryNode(T element) {
        if (element == null)
            throw new IllegalArgumentException();
        this.data = element;
        left = null;
        right = null;
    }

    public void insert(T element) {
        if (Math.random() < 0.5) {
            if (left == null) left = new BinaryNode<T>(element);
            else left.insert(element);
        } else {
            if (right == null) right = new BinaryNode<T>(element);
            else right.insert(element);
        }
    }

    public BinaryNode<T> remove(T toRemove) {
        BinaryNode<T> output = this;

        if (data.equals(toRemove)) {
            if (left != null) {
                data = left.data;
                left = left.remove(data);
            } else if (right != null) {
                data = right.data;
                right = right.remove(data);
            } else output = null;
        } else {
            if (left != null && left.contains(toRemove))
                left = left.remove(toRemove);
            else if (right != null)
                right = right.remove(toRemove);
        }
        return output;
    }

    public boolean contains(T element) {
        boolean found = false;
        if (data.equals(element))
            found = true;
        else if (left != null && left.contains(element))
            found = true;
        else if (right != null && right.contains(element))
            found = true;
        return found;
    }

    public int height() {
        int leftH = -1, rightH = -1;
        if (left != null)
            leftH = left.height();
        if (right != null)
            rightH = right.height();
        return Math.max(leftH, rightH) + 1;
    }

    public int size() {
        int leftS = 0, rightS = 0;
        if (left != null)
            leftS = left.size();
        if (right != null)
            rightS = right.size();
        return leftS + rightS + 1;
    }

    public boolean equals(Object other) {
        boolean isEqual = true;
        //Check type
        if (!(other instanceof BinaryNode<?>))
            isEqual = false;
        else {
            BinaryNode<?> otherNode = (BinaryNode<?>) other;
            //Check data
            if (!data.equals(otherNode.data))
                isEqual = false;
                //Check left
            else if (((left == null) | (otherNode.left == null)) & (left != otherNode.left))
                isEqual = false;
            else if ((left != null) && !left.equals(otherNode.left))
                isEqual = false;
                //Check right
            else if (((right == null) | (otherNode.right == null)) & (right != otherNode.right))
                isEqual = false;
            else if ((right != null) && !right.equals(otherNode.right))
                isEqual = false;
        }
        return isEqual;
    }

    //Complete the following method
    public String toString() {
    	// task4
    	//return a string sorted with inOrder with depth
    	return StringWithDepth(0);
    }

    //returns a string representing the tree , sorted with inOrder, including the depth of each Binary node
    private String StringWithDepth(int depth) {
    	String finalString = "";
    	String tempString = data + "";
    	//checks if there is a child to the left
    	if(left != null) {
    		//depth is increased
    		depth = depth +1;
    		//get the string for the next child
    		finalString = finalString + left.StringWithDepth(depth);
    		//restore the depth for the current binary node
    		depth = depth -1;
    	}
    	//adds spaces in relative to the depth
    	for (int i = 0; i < 2 * depth; i++) {
    		tempString = " " + tempString;
    	}
    	//gets the string for the current node
    	finalString = finalString + tempString + "\n";
    	//checks if there is a child to the right
    	if(right != null) {
    		//depth is increased
    		depth = depth +1;
    		//get the string for the next child
    		finalString = finalString + right.StringWithDepth(depth);
    	}
    	return finalString;
    }

}