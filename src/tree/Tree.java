package tree;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class Tree {
    private final String rootDirectory;
    private final List<Tree> children;

    public Tree(String path) throws FileNotFoundException {
        File file = new File(path);
        this.rootDirectory = file.getName();
        this.children = new ArrayList<>();
        buildTree(file);
    }
    private void buildTree(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("Directory not found: " + file.getAbsolutePath());
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                // Sorts the root directory inner directories and files to have the directories first and then the files, for easier and more organized reading.
                Arrays.sort(files, (file1, file2) -> {
                    if (file1.isDirectory() && !file2.isDirectory()) {
                        return -1; // Directories come before files
                    } else if (!file1.isDirectory() && file2.isDirectory()) {
                        return 1; // Files come after directories
                    } else {
                        return file1.getName().compareToIgnoreCase(file2.getName()); // Sort alphabetically
                    }
                });
                for (File childFile : files) {
                    children.add(new Tree(childFile.getAbsolutePath()));
                }
            }
        }
    }
    public void printTree(String prefix, boolean isLast) {
        System.out.println(prefix + (isLast ? "└── " : "├── ") + rootDirectory);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).printTree(prefix + (isLast ? "    " : "│   "), i == children.size() - 1);
        }
    }
}
