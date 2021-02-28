/*-----------------------------------------------------------------------
 * PhoneBookTest.java
 * Author: Michael Eder
 * Date: 29.03.2020
 * Task: Aufgabe 1
 *
 * This class purpose is to test the class PhoneBook and PhoneBookEntry.
 * Therefore, a textfile with different persons and phoneNumbers is read
 * and added into the PhoneBook as PhoneBookEntry. After the Phonebook
 * was filled, different object methods are called on it.
 * --------------------------------------------------------------------*/

import kwm.*;
import kwm.phonebook.*;

public class PhoneBookTest
{
	
	public static void main(String[] args)
	{
		testPhoneBook();
	}
	
	public static void testPhoneBook()
	{
		//Read entries from textfile 'telefonbuch.txt'
		String file = "telefonbuch.txt";
		Input.openInput(file);
		if (!Input.isOkay())
		{
			System.out.println("Datei " + file + " konnte nicht geöffnet werden.");
			System.exit(-1);
		}
		PhoneBook book = new PhoneBook();
		while (true)
		{
			String line = Input.readString();
			if(line == null) break;
			if (Input.hasEnded()) break;
			if (!Input.isOkay())
			{
				Input.clearError();
				break;
			}
			//Splits the words whenever a space appears
			String [] words = line.split(" ");
			if (words.length==3)
			{
				//declare variables with the correct values from array and add the to PhoneBook
				String firstName = words[0];
				String lastName = words[1];
				String phoneNumber = words[2];
				book.addEntry(firstName, lastName, phoneNumber);
			}
		}
		Input.closeInput();
		
		//print whole phoneBook
		book.print();
		
		//search for name
		String[] firstAndLastName = askUserForName();
		PhoneBookEntry searchName = book.searchPerson(firstAndLastName[0], firstAndLastName[1]);
		searchName.print();
		
		//search for phoneNumber
		String phoneNumber = askUserForNumber();
		PhoneBookEntry searchPhoneNumber = book.searchNumber(phoneNumber);
		searchPhoneNumber.print();

		//get and print the amount of telephoneNumbers within the book
		int allNUmbers = book.nrOfTelephoneNumbers();
		System.out.println("Anzahl der Nummern im Telefonbuch: " + allNUmbers);
		
		//get and print the amount of entries within the book
		int numberOfEntries = book.nrOfEntries();
		System.out.println("Anzahl der Einträge im Telefonbuch: " + numberOfEntries);
	}
	
	/**
	 * askUserForName
	 * This method asks the user to enter a firstName and a LastName.
	 * The user inputs are stored in an Array 'input'
	 * @return input -> This is the array, the inputs from users are stored
	 */
	public static String[] askUserForName()
	{
		String[] input = new String[2];
		
		while (true)
		{
			System.out.print("Bitte Vornamen eingeben: ");
			String answer = Input.readString();
			if (Input.isOkay())
			{
				input[0] = answer;
				break;
			}
			System.out.println("Bitte geben Sie einen gültigen Vornamen ein!");
		}
		
		while (true)
		{
			System.out.print("Bitte Nachnamen eingeben: ");
			String answer = Input.readString();
			if (Input.isOkay())
			{
				input[1] = answer;
				break;
			}
			System.out.println("Bitte geben Sie einen gültigen Nachnamen ein!");
		}
		
		return input;
	}
	
	/**
	 * askUserForNumber
	 * This method asks the user to enter a telephoneNumber.
	 * @return answer -> this is the input from user as String value
	 */
	public static String askUserForNumber()
	{
		while (true)
		{
			System.out.print("Bitte Telefonnummer eingeben: ");
			String answer = Input.readString();
			if (Input.isOkay())
			{
				return answer;
			}
			System.out.println("Bitte geben Sie eine gültige Telefonnumer ein!");
		}
	}
}
