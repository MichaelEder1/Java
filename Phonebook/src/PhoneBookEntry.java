/*-----------------------------------------------------------------------
 * PhoneBookEntry.java
 * Author: Michael Eder
 * Date: 29.03.2020
 * Task: Aufgabe 1
 *
 * This class creates objects from type PhoneBookEntry. A PhoneBookEntry
 * consists of first and last name and an array, in which the phoneNumbers
 * are stored in, and a counter. There are getter/setter methods to
 * read/write the first and last name. Furthermore you can add a number,
 * compare persons by first and last name and print the created entry.
 * --------------------------------------------------------------------*/
package kwm.phonebook;

public class PhoneBookEntry
{
	
	public String firstName;
	public String lastName;
	public String[] phoneNumbers;
	public int arrayCounterPhoneBookEntry = 0;
	
	public PhoneBookEntry(String firstName, String lastName, String phoneNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumbers = new String[3];
		this.phoneNumbers[arrayCounterPhoneBookEntry] = phoneNumber;
		this.arrayCounterPhoneBookEntry++;
	}
	
	/**
	 * getFirstName()
	 * This is a typical getter Method it returns the firstName of this object
	 * @return firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * getLastName()
	 * This is a typical getter Method it returns the lastName of this object
	 * @return lastName
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * setFirstName()
	 * This is a typical setter method, which sets the firstName by the given parameter.
	 * @param firstName; is the firstName which the current one should be changed to
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * setlastName()
	 * This is a typical setter method, which sets the lastName by the given parameter.
	 * @param lastName; is the lastName which the current one should be changed to
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * comparePerson()
	 * This method checks, if an existing entry has the equal first and last name as the given one
	 * @param firstName; the given firstName to check
	 * @param lastName; the given lastName to check
	 * @return true/false; returns true if the first and last name are equal to the given one, else false.
	 */
	public boolean comparePerson(String firstName, String lastName)
	{
		return (this.firstName.equals(firstName) && this.lastName.equals(lastName));
	}
	
	/**
	 * print()
	 * This method prints the whole entry if form
	 * 'Vorname: firstName | Nachname: lastName | Telefonnummer: phoneNumber, phoneNumber2, ... phoneNumberN'
	 */
	public void print()
	{
		System.out.print("Vorname: " + firstName + " | Nachname: " + lastName + " | Telefonnummer(n): ");
		for (int i = 0; i < this.arrayCounterPhoneBookEntry; i++)
		{
			System.out.print(this.phoneNumbers[i]);
			if (i < this.arrayCounterPhoneBookEntry - 1)
			{
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	/**
	 * addTelePhoneNumber()
	 * This method adds a new phoneNumer to the entry, but only, if the given phoneNumber
	 * doesn't exist in the entry. Furthermore it checks, if there is enough space in the
	 * phoneNumber array to add a new number. If there is not enough space, the array will be resized.
	 * @param telephoneNumber; the given phonenumber which should be added to entry
	 */
	public void addTelephoneNumber(String telephoneNumber)
	{
		if (!hasTelephoneNumber(telephoneNumber))
		{
			if (this.arrayCounterPhoneBookEntry == this.phoneNumbers.length)
			{
				resize();
			}
			this.phoneNumbers[arrayCounterPhoneBookEntry] = telephoneNumber;
			this.arrayCounterPhoneBookEntry++;
		}
	}
	
	/**
	 * hasTelephoneNumber()
	 * This methods iterates all telephoneNumbers in the array and checks, if the number already exists.
	 * @param searchPattern; this is the phoneNumber which should be searched
	 * @return true/false; returns true if number exists, else it returns false.
	 */
	public boolean hasTelephoneNumber(String searchPattern)
	{
		for (int i = 0; i < this.arrayCounterPhoneBookEntry; i++)
		{
			if (searchPattern.equals(this.phoneNumbers[i]))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * resize()
	 * This method resizes the the phoneNumber array, if there is not
	 * enough space for another number. By default it doubles the space of the array.
	 */
	public void resize()
	{
		int n = this.phoneNumbers.length;
		String[] phoneNumbersNew = new String[2 * n];
		for (int i = 0; i < n; i++)
		{
			phoneNumbersNew[i] = this.phoneNumbers[i];
		}
		this.phoneNumbers = phoneNumbersNew;
	}
}
