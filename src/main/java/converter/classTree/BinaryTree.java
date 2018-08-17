package converter.classTree;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class BinaryTree<T> implements TreeNode<T> {

    private T element;

    private BinaryTree<T> firstChild;

    private BinaryTree<T> nextBro;

    private BinaryTree<T> parent;


    public BinaryTree() {
    }

    public BinaryTree(T element) {
        this.element = element;
    }

    public BinaryTree(T element, BinaryTree<T> firstChild, BinaryTree<T> nextBro) {
        this.element = element;
        this.firstChild = firstChild;
        this.nextBro = nextBro;
    }

    @Override
    public boolean isEmpty() {
        return element == null;
    }

    @Override
    public BinaryTree<T> getChild(int index) {
        if (index < 0) {
            return null;
        }
        if (this.firstChild == null) {
            return null;
        }
        BinaryTree<T> temp = this.firstChild;
        while (index > 0 && temp != null) {
            temp = temp.nextBro;
            index--;
        }
        return temp;
    }

    @Override
    public List<BinaryTree<T>> getChildren() {
        ArrayList<BinaryTree<T>> children = new ArrayList<>();
        BinaryTree<T> temp = this.firstChild;
        while (temp != null) {
            children.add(temp);
            temp = temp.nextBro;
        }
        return children;
    }

    @Override
    public <R extends TreeNode<T>> boolean insert(R child, int index) {
        if (index < 0 || child == null) {
            return false;
        }
        if (index == 0) {
            BinaryTree<T> temp = this.getFirstChild();
            this.setFirstChild((BinaryTree<T>) child);
            ((BinaryTree<T>) child).setNextBro(temp);
            child.setParent(this);
            return true;
        }
        BinaryTree<T> left = this.getChild(index - 1);
        if (left != null) {
            BinaryTree<T> right = this.getChild(index);
            left.setNextBro((BinaryTree<T>) child);
            ((BinaryTree<T>) child).setNextBro(right);
            child.setParent(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BinaryTree<T> remove(int index) {
        BinaryTree<T> temp = this.getChild(index);
        if (temp == null) {
            return null;
        }
        BinaryTree<T> postNode = this.getChild(index - 1);
        if (postNode == null) {
            this.firstChild = temp.getNextBro();
        } else {
            postNode.setNextBro(temp.getNextBro());
        }
        temp.setNextBro(null);
        return temp;
    }

    @Override
    public BinaryTree<T> getParent() {
        return parent;
    }

    @Override
    public <R extends TreeNode<T>> void setParent(R parent) {
        this.parent = (BinaryTree<T>) parent;
    }

    @Override
    public BinaryTree<T> removeParent() {
        BinaryTree<T> temp = parent;
        parent = null;
        return temp;
    }

    @Override
    public boolean contains(TreeNode<T> node) {
        if (node == null) {
            return false;
        }
        if (this.equals(node)) {
            return true;
        }
        BinaryTree<T> temp = this.firstChild;
        while (temp != null) {
            if (temp.equals(node)) {
                return true;
            }
            temp = temp.getNextBro();
        }
        return false;
    }

    @Override
    public T getData() {
        return element;
    }

    @Override
    public void setData(T data) {
        element = data;
    }

    @Override
    public void cleanData() {
        element = null;
    }

    public BinaryTree<T> getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(BinaryTree<T> firstChild) {
        this.firstChild = firstChild;
    }

    public BinaryTree<T> getNextBro() {
        return nextBro;
    }

    public void setNextBro(BinaryTree<T> nextBro) {
        this.nextBro = nextBro;
    }

    public void add(BinaryTree<T> node) {
        if (this.firstChild == null) {
            this.firstChild = node;
        }
        BinaryTree<T> temp = firstChild;
        while (temp.nextBro != null) {
            temp = temp.nextBro;
        }
        temp.nextBro = node;
    }

    public void add(T element) {
        add(new BinaryTree<>(element));
    }

}
