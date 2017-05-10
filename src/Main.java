import javax.script.ScriptException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        /* #### TASK 1 #### */
        System.out.println("[TASK1] Euler-Tour");
        //The tree I create here is exactly the same as from the slide "5 - Bäume: 5.17. Euler-Tour-Traversierung"
        BinaryTreeNode root = CreateTreeFromSlide();
        String eulerExpression = root.EulerTour(new PreVisit(), new InVisit(), new PostVisit());
        System.out.println("[INFO] Testing Euler-Tour");
        System.out.println("Expected Result:\t((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))");
        System.out.println("Created Result: \t" + eulerExpression);
        System.out.println("");
        System.out.println("");

        /*
        The advantages of using an function as parameter: (even though I actually don't pass a pure-function,
            but rather pass an interface, which declares the desired function and invoke the methode inside
            our euler-tour. The invoked method is implemented by the class)

        First of all, the Euler-Tour is "finished", so let's say, if we want to change the mathematical expression
        we get from my euler-tour, we don't actually have to change the code inside the euler-tour, but can
        take the action inside our passed functions. That will result in tremendously easier maintainance and
        additional stability (since the rest of the program will not be effected - we don't touch the euler tour-code!

        Also, not in this example though, passing a function can be used as async callbacks - we pass the function
        that we would want to be executed after the called function finishes. While this might not always be async
        it can always be used as a custom callback. Very useful!
         */


        /* #### TASK 2 #### */
        System.out.println("[TASK2] Expressions");
        //Here we go with some test-cases
        try //this is required because my eval might throw an exception
        { //and yes, I understand that you wouldn't place a try/catch around just everything
            //but it does look better for presentation...


            //for the first test we just use the node from above as expr
            Expr expr = new Expr(root);
            int x = 7;
            System.out.println("[INFO] Test-Case 1:");
            System.out.println("[EVALUATE] " + expr.toString());
            System.out.println("[EVALUATE] Assuming x = " + x);
            System.out.println("[RESULT] The result is: " + Expr.eval(expr, x));
            System.out.println("");
            System.out.println("[INFO] Test-Case 2:");
            System.out.println("[EVALUATE] " + expr.toString());
            x = 10;
            System.out.println("[EVALUATE] Assuming x = " + x);
            System.out.println("[RESULT] The result is: " + Expr.eval(expr, x));
            System.out.println("");
            System.out.println("[INFO] Jokes on you, there was no x for the above Expressions");
            System.out.println("[INFO] Creating Expression with some variable now...");
            System.out.println("");
            System.out.println("[INFO] Test-Case 3:");
            System.out.println("[INFO] Using the Expression of the task-sheet:");
            System.out.println("[INFO] ((((2*x)*x)+(4*x))-1)");

            //Maybe you can read it more easily with these indentations :^}
            expr = Expr.sub(
                    Expr.add(
                            Expr.mul(
                                    Expr.mul(
                                            Expr.num(2),
                                            Expr.var()
                                    ),
                                    Expr.var()
                            ),
                            Expr.mul(
                                    Expr.num(4),
                                    Expr.var()
                            )
                    ),
                    Expr.num(1)
            );

            System.out.println("[EVALUATE] " + expr.toString());
            x = 2;
            System.out.println("[EVALUATE] Assuming x = " + x);
            System.out.println("[RESULT] The result is: " + Expr.eval(expr, x));
            System.out.println("");
            System.out.println("[INFO] Test-Case 4:");
            System.out.println("[EVALUATE] " + expr.toString());
            x = 16;
            System.out.println("[EVALUATE] Assuming x = " + x);
            System.out.println("[RESULT] The result is: " + Expr.eval(expr, x));
            System.out.println("");
            System.out.println("[INFO] Test-Case 5:");
            System.out.println("[INFO] Using my own Expression:");
            System.out.println("[INFO] (x-(((x*x)-(10*x))*(5+x)))");
            expr = Expr.sub(
                    Expr.var(),
                    Expr.add(
                            Expr.sub(
                                    Expr.mul(
                                            Expr.var(),
                                            Expr.var()
                                    ),
                                    Expr.mul(
                                            Expr.num(10),
                                            Expr.var()
                                    )
                            ),
                            Expr.mul(
                                    Expr.num(5),
                                    Expr.var()
                            )
                    )
            );

            System.out.println("[EVALUATE] " + expr.toString());
            x = 6;
            System.out.println("[EVALUATE] Assuming x = " + x);
            System.out.println("[RESULT] The result is: " + Expr.eval(expr, x));
            System.out.println("");
            System.out.println("[INFO] Test-Case 6:");
            System.out.println("[EVALUATE] " + expr.toString());
            x = 3;
            System.out.println("[EVALUATE] Assuming x = " + x);
            System.out.println("[RESULT] The result is: " + Expr.eval(expr, x));
        }
        catch (ScriptException e)
        {
            e.printStackTrace();
        }
    }



    /* Here we goooo with some helpeerrs */
    private static BinaryTreeNode CreateTreeFromSlide()
    {
        BinaryTreeNode root = BinaryTreeNode.CreateNode("-");
        root.leftChild = BinaryTreeNode.CreateNode("/");
        root.leftChild.leftChild = BinaryTreeNode.CreateNode("*");
        root.leftChild.leftChild.leftChild = BinaryTreeNode.CreateNode("+");
        root.leftChild.leftChild.leftChild.leftChild = BinaryTreeNode.CreateNode(3);
        root.leftChild.leftChild.leftChild.rightChild = BinaryTreeNode.CreateNode(1);
        root.leftChild.leftChild.rightChild = BinaryTreeNode.CreateNode(3);
        root.leftChild.rightChild = BinaryTreeNode.CreateNode("+");
        root.leftChild.rightChild.rightChild = BinaryTreeNode.CreateNode(2);
        root.leftChild.rightChild.leftChild = BinaryTreeNode.CreateNode("-");
        root.leftChild.rightChild.leftChild.leftChild = BinaryTreeNode.CreateNode(9);
        root.leftChild.rightChild.leftChild.rightChild = BinaryTreeNode.CreateNode(5);
        root.rightChild = BinaryTreeNode.CreateNode("+");
        root.rightChild.rightChild = BinaryTreeNode.CreateNode(6);
        root.rightChild.leftChild = BinaryTreeNode.CreateNode("*");
        root.rightChild.leftChild.leftChild = BinaryTreeNode.CreateNode(3);
        root.rightChild.leftChild.rightChild = BinaryTreeNode.CreateNode("-");
        root.rightChild.leftChild.rightChild.rightChild = BinaryTreeNode.CreateNode(4);
        root.rightChild.leftChild.rightChild.leftChild = BinaryTreeNode.CreateNode(7);
        return root;
    }
}
