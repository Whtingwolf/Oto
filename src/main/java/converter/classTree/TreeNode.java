package converter.classTree;

import java.util.List;

public interface TreeNode<T> {

    /**
     * @return the node's data is null
     */
    public boolean isEmpty();

    /**
     * @param index the child's index
     * @return the Node which extends TreeNode of the child
     */
    public <R extends TreeNode<T>> R getChild(int index);

    /**
     * Get all children of the node
     * @return a List of TreeNode
     */
    public <R extends TreeNode<T>> List<R> getChildren();

    /**
     * insert a node if it can be inserted
     * and set the child's parent is this.node
     *
     * @param child the inserted node
     * @param index
     */
    public <R extends TreeNode<T>> boolean insert(R child, int index);

    /**
     * remove child where on the index
     * and remove parent from the child
     *
     * @param index the child position
     * @return the remove child
     */
    public <R extends TreeNode<T>> R remove(int index);

    /**
     * get the node's parent
     *
     * @return Treenode
     */
    public <R extends TreeNode<T>> R getParent();

    /**
     * child set the parent node
     *
     * @param parent parent node
     */
    public <R extends TreeNode<T>> void setParent(R parent);

    /**
     * remove the parent Reference of parent
     *
     * @return
     */

    public <R extends TreeNode<T>> R removeParent();

    /**
     * if the children contains a node equal the param node
     * return true
     *
     * @param node the search node
     * @return
     */
    public boolean contains(TreeNode<T> node);

    /**
     * return the node' data
     *
     * @return generic data
     */
    public T getData();

    /**
     * set data
     *
     * @param data
     */
    public void setData(T data);

    /**
     * remove the node data
     */
    public void cleanData();


}

