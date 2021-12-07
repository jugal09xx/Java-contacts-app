package com.jugallad;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("909 996 1848");

    public static void main(String[] args){

        boolean quit = false;
        startPhone();
        printActions();

        while(!quit) {
            System.out.println("\nEnter action: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action) {

                case 0:
                    System.out.println("\nShutting down...");
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

                default:
                    System.out.println("Please enter a valid choice");
                    printActions();
                    break;
            }
        }

    }

    private static void startPhone() {
        System.out.println("Starting Phone....");
    }

    private static void printActions(){
        System.out.println("\nAvailable Actions:\npress");
        System.out.println("0 - to power off\n");
        System.out.println("1 - to print contacts\n");
        System.out.println("2 - to add a new contact\n");
        System.out.println("3 - to update an existing contact\n");
        System.out.println("4 - to remove an existing contact\n");
        System.out.println("5 - to query if a contact already exists\n");
        System.out.println("6 - to see a list of available actions\n");
    }

    private static void addNewContact(){
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Phone number: ");
        String phoneNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(name,phoneNumber);
        if(mobilePhone.addNewContact(newContact)){
            System.out.println("New contact added: "+name+", phone: "+phoneNumber);
        }
        else
            System.out.println("Error, A contact by that name already exists!");
    }

    private static void updateContact(){
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();

        Contact existingContactRecord = mobilePhone.queryContact(name);

        if(existingContactRecord == null){
            System.out.println("Contact not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new phone Number: ");
        String newPhoneNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName,newPhoneNumber);
        if(mobilePhone.updateContact(existingContactRecord,newContact)){
            System.out.println("Successfully updated the contact.");
        } else {
            System.out.println("Error updating contact");
        }

    }

    private static void removeContact(){
        System.out.println("Enter contact to delete: ");
        String name = scanner.nextLine();

        Contact existingContactRecord = mobilePhone.queryContact(name);

        if(existingContactRecord == null){
            System.out.println("Contact not found.");
            return;
        }

        if(mobilePhone.removeContact(existingContactRecord)){
            System.out.println("Successfully deleted.");
        } else {
            System.out.println("Contact could not be deleted.");
        }
    }

    private static void queryContact(){
        System.out.println("Enter contact to search: ");
        String name = scanner.nextLine();

        Contact existingContactRecord = mobilePhone.queryContact(name);

        if(existingContactRecord == null){
            System.out.println("Contact not found.");
            return;
        }

        System.out.println("Name: "+existingContactRecord.getName()+" ,phone: "+existingContactRecord.getPhoneNumber());
    }
}
