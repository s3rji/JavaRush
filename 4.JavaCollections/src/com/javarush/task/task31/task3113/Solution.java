package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution {
    private static int totalDirectories;
    private static int totalFiles;
    private static long totalSize;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Path rootDirectory = Paths.get(reader.readLine());

        if (!Files.isDirectory(rootDirectory)) {
            System.out.println(rootDirectory.toAbsolutePath() + " - не папка");
            return;
        }

        findAllFilesAndDirectories(rootDirectory);

        System.out.println("Всего папок - " + totalDirectories);
        System.out.println("Всего файлов - " + totalFiles);
        System.out.println("Общий размер - " + totalSize);
    }

    private static void findAllFilesAndDirectories(Path rootDirectory) throws IOException {
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rootDirectory);
        for (Path path : directoryStream) {
            if (Files.isDirectory(path)) {
                totalDirectories++;
                findAllFilesAndDirectories(path);
            }

            if (Files.isRegularFile(path)) {
                totalFiles++;
                totalSize += Files.size(path);
            }
        }
    }
}
