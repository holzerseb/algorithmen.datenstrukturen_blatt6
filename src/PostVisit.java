/**
 * Created by user on 10.05.2017.
 */
public class PostVisit implements Visit
{
    @Override
    public String visit(BinaryTreeNode node)
    {
        if(node.IsInternal())
            return ")";

        return "";
    }
}
