package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        MyFileVisitor fileVisitor = new MyFileVisitor();
        Files.walkFileTree(Paths.get(root), fileVisitor);

        return fileVisitor.getFileTree();
    }

    public static void main(String[] args) {

    }

    public static class MyFileVisitor extends SimpleFileVisitor<Path> {
        private List<String> fileTree = new ArrayList<>();

        public List<String> getFileTree() {
            return fileTree;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            fileTree.add(file.toAbsolutePath().toString());

            return super.visitFile(file, attrs);
        }
    }
}


