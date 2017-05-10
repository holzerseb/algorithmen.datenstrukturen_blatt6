import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.beans.Expression;

/**
 * Created by user on 10.05.2017.
 */
public class Expr
{
    private BinaryTreeNode internalStructure; //this is the internal tree, representing the expr-structure

    public Expr(BinaryTreeNode node)
    {
        this.internalStructure = node;
    }

    /**
     * Creates a variable representing "x"
     * @return
     */
    public static Expr var()
    {
        return new Expr(BinaryTreeNode.CreateNode("x"));
    }

    /**
     * Creates a Expr with a constant
     * @param i
     * @return
     */
    public static Expr num(int i)
    {
        return new Expr(BinaryTreeNode.CreateNode(i));
    }

    /**
     * Creates a Expr representing the sum of the given Expressions
     * @param a
     * @param b
     * @return
     */
    public static Expr add(Expr a, Expr b)
    {
        Expr expr = new Expr(BinaryTreeNode.CreateNode("+"));
        expr.internalStructure.leftChild = a.internalStructure;
        expr.internalStructure.rightChild = b.internalStructure;

        return expr;
    }

    /**
     * Creates a Expr representing the difference of the given Expressions
     * @param a
     * @param b
     * @return
     */
    public static Expr sub(Expr a, Expr b)
    {
        Expr expr = new Expr(BinaryTreeNode.CreateNode("-"));
        expr.internalStructure.leftChild = a.internalStructure;
        expr.internalStructure.rightChild = b.internalStructure;

        return expr;
    }

    /**
     * Creates a Expr representing the product of the given Expressions
     * @param a
     * @param b
     * @return
     */
    public static Expr mul(Expr a, Expr b)
    {
        Expr expr = new Expr(BinaryTreeNode.CreateNode("*"));
        expr.internalStructure.leftChild = a.internalStructure;
        expr.internalStructure.rightChild = b.internalStructure;

        return expr;
    }

    /**
     * Prints the expression to the screen
     * @param expr
     */
    public static void print(Expr expr)
    {
        //Hint: Is there a connection to exercise 1?!?!?!
        System.out.println(expr.internalStructure.EulerTour(new PreVisit(), new InVisit(), new PostVisit()));

        /* The Complexity of this function is O(N), while N == number of nodes in the BinaryTree (internalStructure) */
    }

    /**
     * Prints the expression to the screen
     */
    @Override
    public String toString()
    {
        return this.internalStructure.EulerTour(new PreVisit(), new InVisit(), new PostVisit());
    }

    /**
     * Evaluates the given Expression, assuming all variables are of value "var"
     * @param expr
     * @param var
     * @return
     */
    public static int eval(Expr expr, int var) throws ScriptException
    {
        //Well, my implementation of Task 1 made it very attractive to just use some built-in
        //"String to Expression" things, since the mathematical Expression is already done by the Euler-Tour
        //So, why implement something complex, if we can use JDK-1.6's Javascript-Engine :^}

        //Note, I'm aware the javascript-engine executes a script actually - makes our program unstable, unsafe
        //and is probably a very bad programming-style. But hey, it works and fulfills the request of the task :^}

        ScriptEngineManager scriptManager = new ScriptEngineManager();
        ScriptEngine jsEngine = scriptManager.getEngineByName("JavaScript");
        String mathExpr = expr.internalStructure.EulerTour(new PreVisit(), new InVisit(), new PostVisit());
        mathExpr = mathExpr.replaceAll("x", String.valueOf(var));
        return (int)jsEngine.eval(mathExpr);

        /* The Complexity of this function is O(N), while N == number of nodes in the BinaryTree (internalStructure) */
    }
}
