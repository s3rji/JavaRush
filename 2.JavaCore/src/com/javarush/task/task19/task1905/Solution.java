package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
        String string = "+38(050)123-45-67";
        System.out.println(string.replaceAll("\\D", ""));


    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String countryCode = null;
            for (Map.Entry<String, String> pair : countries.entrySet()) {
                if(pair.getValue().equals(customer.getCountryName()))
                    countryCode = pair.getKey();
            }
            return countryCode;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String[] split = contact.getName().split(", ");
            return split[1];
        }

        @Override
        public String getContactLastName() {
            String[] split = contact.getName().split(", ");
            return split[0];
        }

        @Override
        public String getDialString() {
            String phoneNumber = "";

            char[] characters = contact.getPhoneNumber().toCharArray();
            for (int i = 0; i < characters.length; i++) {
                if (Character.isDigit(characters[i]))
                    phoneNumber = phoneNumber + characters[i];
            }
            return String.format("callto://+%s", phoneNumber);
        }
    }

    public static interface RowItem {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        String getDialString();         //For example: callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example: +38(050)123-45-67 or +3(805)0123-4567 or +380(50)123-4567 or ...
    }
}