package converter.classTree;

import java.util.Enumeration;

public interface TreeNode {

    /**
     * Returns the child TreeNode at index
     * childIndex
     */
    TreeNode getChildAt(int childIndex);

    /**
     * Returns the number of children TreeNodes the receiver
     * contains.
     */
    int getChildCount();

    /**
     * Returns the parent TreeNode of the receiver.
     */
    TreeNode getParent();

    /**
     * Returns the index of node in the receivers children.
     * If the receiver does not contain node, -1 will be
     * returned.
     */
    int getIndex(TreeNode node);

    /**
     * Returns true if the receiver allows children.
     */
    boolean getAllowsChildren();

    /**
     * Returns true if the receiver is a leaf.
     */
    boolean isLeaf();

    /**
     * Returns the children of the receiver as an Enumeration.
     */
    Enumeration children();

    /**
     * Returns the TreeNode's element whether extends Collection
     */
    boolean isCollectionNode();

    /**
     * Returns the TreeNode's element whether is null
     * @return
     */
    boolean isNullNode();

    /**
     * Returns the TreeNode's element whether extends Enum
     * @return
     */
    boolean isEnumNode();

    /**
     * Returns the TreeNode's element whether is a String
     * @return
     */
    boolean isStringNode();

    /**
     * Returns the TreeNode's element whether is a Number
     * @return
     */
    boolean isNumberNode();

}
