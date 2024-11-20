import java.util.Scanner;

class Node {
    String name;
    Node left, right;

    Node(String name) {
        this.name = name;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(String name) {
        root = insertRec(root, name);
    }

    Node insertRec(Node root, String name) {
        if (root == null) {
            root = new Node(name);
            return root;
        }

        if (name.compareTo(root.name) < 0)
            root.left = insertRec(root.left, name);
        else if (name.compareTo(root.name) > 0)
            root.right = insertRec(root.right, name);
        else
            System.out.println("Duplicate name! Not inserted."); //Handle duplicates

        return root;
    }

    boolean search(String name) {
        return searchRec(root, name);
    }

    boolean searchRec(Node root, String name) {
        if (root == null)
            return false;

        if (name.compareTo(root.name) == 0)
            return true;

        if (name.compareTo(root.name) < 0)
            return searchRec(root.left, name);

        return searchRec(root.right, name);
    }

    void delete(String name) {
        root = deleteRec(root, name);
    }

    Node deleteRec(Node root, String name) {
        if (root == null) {
            System.out.println("Name not found!");
            return root;
        }

        if (name.compareTo(root.name) < 0)
            root.left = deleteRec(root.left, name);
        else if (name.compareTo(root.name) > 0)
            root.right = deleteRec(root.right, name);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.name = minValue(root.right);
            root.right = deleteRec(root.right, root.name);
        }
        return root;
    }

    String minValue(Node root) {
        String minv = root.name;
        while (root.left != null) {
            minv = root.left.name;
            root = root.left;
        }
        return minv;
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.name + " ");
            inorderRec(root.right);
        }
    }
}

public class NameTree {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display (inorder)");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name to insert: ");
                    String name = scanner.nextLine();
                    bst.insert(name);
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    if (bst.search(name))
                        System.out.println(name + " found!");
                    else
                        System.out.println(name + " not found!");
                    break;
                case 3:
                    System.out.print("Enter name to delete: ");
                    name = scanner.nextLine();
                    bst.delete(name);
                    break;
                case 4:
                    System.out.print("Names in the tree (inorder): ");
                    bst.inorder();
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}