/**
 * Created by pranav on 28/07/17.
 */
public class BST {
    public static void main(String[] args) {
        BST bst = new BST();
        BstNode root = null;
        root = bst.insert(root, 15);
        root = bst.insert(root, 155);
        root = bst.insert(root, 20);
        root = bst.insert(root, 10);
        root = bst.insert(root, 19);
        System.out.println(bst.search(root, 19));
        System.out.println(bst.findMin(root).data);
        System.out.println(bst.isBST(root)); //most commonly asked in interview
        bst.preorderTraversal(root);
        System.out.println();
        bst.inorderTraversal(root);
        System.out.println();
        bst.postorderTraversal(root);
        System.out.println();
    }
    BstNode insert(BstNode root, int data){
        if(root == null){
            root = new BstNode(data);
            return root;
        }
        else if(data <= root.data)
            root.left = insert(root.left, data);
        else
            root.right = insert(root.right, data);
        return root;
    }

    boolean search(BstNode root, int data){
        //empty tree
        if(root == null)
            return false;
        //root matched
        if(root.data == data){
            return true;
        }
        else if(data <= root.data)
            return search(root.left, data);
        else
            return search(root.right, data);
    }
    BstNode delete(BstNode root, int data){

        if(root == null)
            return root;
        //only one child to a node
        if(data <= root.data)
            delete(root.left, data);
        else if (data > root.data)
            delete(root.right, data);
        else{
            //No childs
            if(root.left == null && root.right == null){
                root = null; //make pointer to that node, ie loose the connection
            }
            //one child
            else if(root.left == null)
                root = root.right;
            else if(root.right == null)
                root = root.left;
            //2 children
            else{
                BstNode min =  findMin(root.right);//else you can find max in root.left
                root.data = min.data;
                root.right = delete(root.right, min.data);
            }
        }
     return root;
    }
    BstNode findMin(BstNode root){
        if(root == null)
            return  null;
        if(root.left == null)
            return root;
        root = findMin(root.left);
        return root;
    }
    BstNode findMax(BstNode root){
        if(root.right == null)
            return root;
        root = findMax(root.right);
        return root;
    }
    Boolean isBST(BstNode root){
        if(root == null)
            return true;
        if(root.left !=null && root.left.data > root.data)
            return false;
        if(root.right !=null && root.right.data < root.data)
            return false;
        if(!isBST(root.left) || !isBST(root.right))
            return false;
        return true;

    }
    void preorderTraversal(BstNode root){
        if(root == null)
            return;
        System.out.print(root.data+" ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }
    void inorderTraversal(BstNode root){
        if(root == null)
            return;
        inorderTraversal(root.left);
        System.out.print(root.data+" ");
        inorderTraversal(root.right);
    }
    void postorderTraversal(BstNode root){
        if(root == null)
            return;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.data+" ");
    }
    //asked in interviews
    void levelOrderTraversal(BstNode root){

    }

}
