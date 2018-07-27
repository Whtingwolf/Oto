package converter.classTree;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class ConvertMutableTreeNode implements MutableTreeNode, Serializable {

    static public final Enumeration<TreeNode> EMPTY_ENUMERATION = Collections.emptyEnumeration();
    private static final long serialVersionUID = 1L;
    protected MutableTreeNode parent;
    protected ArrayList children;
    NodeElement element;

    public void add(MutableTreeNode Node) {

    }

    @Override
    public void insert(MutableTreeNode child, int index) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void remove(MutableTreeNode node) {

    }

    @Override
    public void setUserObject(Object object) {

    }

    @Override
    public void removeFromParent() {

    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public void setParent(MutableTreeNode newParent) {

    }

    @Override
    public int getIndex(TreeNode node) {
        return 0;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    public void setAllowsChildren(boolean allows) {

    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration children() {
        return null;
    }
}
