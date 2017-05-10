import java.util.concurrent.Callable;

/**
 * Created by user on 03.05.2017.
 */
public class BinaryTreeNode
{
    /* Task was: You do not have to implement the full ADT for a binary tree. It is enough if
    you have access to the left and right child, given a parent node. */

    //yeah, i don't care about data encapsulation here, it's really just about the tree
    public Object data; //we can hold arbitrary data here
    public BinaryTreeNode leftChild;
    public BinaryTreeNode rightChild;

    /**
     * Starts an euler tour from this node-instance as root
     */
    public String EulerTour(Visit preVisitFunc, Visit inVisitFunc, Visit postVisitFunc)
    {
        //Now, I didn't know how Java usually implements the passing of functions, but I got suggested to do it
        //by using interfaces. Other than that (since Java8) you could also use lambda's, but I was already doing
        //the old-school style, when I read that, so I'll stick with this

        //Also, my implementation orientates on the example of "5 Bäume: 5.18 Binäre Euler-Tour-Traversierung"
        //where we build a arithmetical expression. Yet, this expression is a plain String for now...
        String arithmeticalExpression = "";

        arithmeticalExpression += preVisitFunc.visit(this);

        if(this.leftChild != null)
            arithmeticalExpression += this.leftChild.EulerTour(preVisitFunc, inVisitFunc, postVisitFunc);

        arithmeticalExpression += inVisitFunc.visit(this);

        if(this.rightChild != null)
            arithmeticalExpression += this.rightChild.EulerTour(preVisitFunc, inVisitFunc, postVisitFunc);

        arithmeticalExpression += postVisitFunc.visit(this);

        return arithmeticalExpression;
    }



    /**
     * yeah, just a helper method
     * @return
     */
    public boolean IsInternal()
    {
        if(leftChild != null || rightChild != null)
            return true;

        return false;
    }

    /**
     * This is a helper as well :)
     * @param data
     * @return
     */
    public static BinaryTreeNode CreateNode(Object data)
    {
        BinaryTreeNode node = new BinaryTreeNode();
        node.leftChild = null;
        node.rightChild = null;
        node.data = data;
        return node;
    }
}
