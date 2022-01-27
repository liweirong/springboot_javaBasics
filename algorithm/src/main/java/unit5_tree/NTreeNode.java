package unit5_tree;

import java.util.List;

public class NTreeNode {
    int val;
    List<NTreeNode> children;

    NTreeNode() {
    }

    NTreeNode(int val) {
        this.val = val;
    }

    public NTreeNode(int _val, List<NTreeNode> _children) {
        val = _val;
        children = _children;
    }
}
