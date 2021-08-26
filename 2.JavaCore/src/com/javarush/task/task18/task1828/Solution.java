package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Прайсы 2
*/

public class Solution {
    public static class Product {
        private String id;
        private String name;
        private String price;
        private String quantity;

        public Product(String id, String name, String price, String quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("%-8s%-30s%-8s%-4s%n", id, name, price, quantity);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) return;

        ArrayList<Product> products = new ArrayList<>();

        Scanner reader = new Scanner(System.in);
        String fileName = reader.nextLine();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                Product product = parseProduct(fileReader.readLine());
                products.add(product);
            }
        }

        switch (args[0]) {
            case "-d":
                products.removeIf(product -> product.id.equals(args[1]));
                break;
            case "-u":
                String id = args[1];
                if (id.length() > 8) id = id.substring(0, 8);

                StringBuilder name = new StringBuilder();
                for (int i = 2; i < args.length-2; i++) {
                    name.append(args[i]).append(" ");
                }
                if (name.length() > 30) name = new StringBuilder(name.substring(0, 30));

                String price = args[args.length - 2];
                if (price.length() > 8) price = price.substring(0, 8);

                String quantity = args[args.length - 1];
                if (quantity.length() > 4) quantity = quantity.substring(0, 4);

                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).id.equals(id))
                        products.set(i, new Product(id, name.toString(), price, quantity));
                }
        }

        try (BufferedWriter fileWritter = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                fileWritter.write(product.toString());
            }
        }

    }

    public static Product parseProduct(String product) {
        String id = product.substring(0, 8).trim();;
        String name = product.substring(8, 38).trim();
        String price = product.substring(38, 46).trim();
        String quantity = product.substring(46, 50).trim();

        return new Product(id, name, price, quantity);
    }
}
