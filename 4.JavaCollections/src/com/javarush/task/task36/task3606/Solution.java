package com.javarush.task.task36.task3606;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    String pathToFiles = packageName.endsWith("/") ? packageName : packageName + "/";
                    byte[] b = fetchClassFromFS(pathToFiles + name + ".class");
                    return defineClass(null, b, 0, b.length);
                } catch (IOException e) {
                    return super.findClass(name);
                }
            }
        };

        File[] files = new File(packageName).listFiles(f -> f.getName().toLowerCase().endsWith(".class"));

        for (File f : files) {
            boolean hasInterface = false;
            boolean hasConstructor = false;
            String fileName = f.getName();
            Class<?> clazz = loader.loadClass(fileName.substring(0, fileName.length() - 6));

            if (Arrays.stream(clazz.getInterfaces()).anyMatch(i -> i == HiddenClass.class))
                hasInterface = true;


            if (Arrays.stream(clazz.getDeclaredConstructors()).anyMatch(c -> c.getParameterCount() == 0))
                hasConstructor = true;

            if (hasInterface && hasConstructor) hiddenClasses.add(clazz);
        }
    }

    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(path);

        long length = new File(path).length();

        if (length > Integer.MAX_VALUE) {

        }

        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+path);
        }

        is.close();
        return bytes;
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class<?> clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();

                } catch (InstantiationException | IllegalAccessException |
                        InvocationTargetException | NoSuchMethodException e) {
                    return null;
                }
            }
        }

        return null;
    }
}

