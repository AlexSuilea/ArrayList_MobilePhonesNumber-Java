package com.alexsuilea;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.SQLOutput;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("0721 233 210");
    public static void main(String[] args) {
        boolean quit = false;
        startPhone();
        printActions();

        while(!quit){
            System.out.println("Enter action: (6 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action){
                case 0:
                    System.out.println("Shutting down...");
                    quit = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;
            }
        }
    }

    private static void addNewContact(){
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phoneNumber);
        if(mobilePhone.addNewContact(newContact)){
            System.out.println("New contact added: name = " +name+ ", phone = " +phoneNumber);
        } else {
            System.out.println("Cannot add, " +name+ "already on file.");
        }
    }

    private static void updateContact(){
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if(existingContactRecord == null){
            System.out.println("Contact not found!");
            return;
        }
        System.out.println("Enter a new contact name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter a new contact phone number: ");
        String newPhoneNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName, newPhoneNumber);
        if(mobilePhone.updateContact(existingContactRecord, newContact)){
            System.out.println("Successfully updated record.");
        } else {
            System.out.println("Error updating record.");
        }
    }

    public static void removeContact(){
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if(existingContactRecord == null){
            System.out.println("Contact not found!");
            return;
        }
        if(mobilePhone.removeContact(existingContactRecord)){
            System.out.println("Successfully removed.");
        } else {
            System.out.println("Error removing the contact");
        }
    }

    public static void queryContact(){
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if(existingContactRecord == null){
            System.out.println("Contact not found!");
            return;
        }
        System.out.println("Name: " +existingContactRecord.getName()+ " phone number: " +existingContactRecord.getPhoneNumber());
    }

    private static void startPhone(){
        System.out.println("Starting phone...");
    }

    private static void printActions(){
        System.out.println("\nAvailable actions\npress");
        System.out.println( "0 - to shut down\n" +
                            "1 - to print contacts\n"+
                            "2 - to add a new contact\n"+
                            "3 - to update an existing contact\n"+
                            "4 - to remove an existing contact\n"+
                            "5 - query if a contact exists\n"+
                            "6 - to print a list of available actions");
        System.out.println("Choose your actions: ");
    }
}
