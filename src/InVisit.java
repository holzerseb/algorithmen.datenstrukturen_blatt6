/**
 * Created by user on 10.05.2017.
 */
public class InVisit implements Visit
{
    @Override
    public String visit(BinaryTreeNode node)
    {
        return node.data.toString(); //expects the arbitrary data to have a reasonable toString-Implementation...
    }
}
