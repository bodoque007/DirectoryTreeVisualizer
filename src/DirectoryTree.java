import tree.Tree;

import java.io.FileNotFoundException;

public class DirectoryTree {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("Usage: java DirectoryTree <rootDirectoryPath>");
            return;
        }
        String rootDirectoryPath = args[0]; // or any other directory path
        Tree tree = new Tree(rootDirectoryPath);
        tree.printTree("", true);
    }
}
