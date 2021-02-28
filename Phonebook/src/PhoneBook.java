/*-----------------------------------------------------------------------
 * PhoneBook.java
 * Author: Michael Eder
 * Date: 29.03.2020
 * Task: Aufgabe 1
 *
 * This class creates objects from type PhoneBook. A PhoneBook consists of
 * an PhoneBookEntry Array, which is set by default to a length of 10, but
 * can be resized. The object methods can add a phonebookentry to the
 * phonebook, can search for names and phonenumbers, can return the total
 * number of entries and phonenumbers in this Phonebook.
 * --------------------------------------------------------------------*/
package kwm.phonebook;

public class PhoneBook
{
	
	public PhoneBookEntry[] entries;
	public int arrayCounterPhoneBook = 0;
	
	public PhoneBook()
	{
		this.entries = new PhoneBookEntry[10];
	}
	
	/**
	 * addEntry
	 * This method adds an entry into the phonebook, if there is no other
	 * entry with the same firstName and lastName already in it. If so, only
	 * the phonenumber is added to the right entry, if this number doesn't exist.
	 * @param firstName -> String; is the firstName of the added person
	 * @param lastName -> String; ist the lastName of the added person
	 * @param phoneNumber -> String; is the phoneNumber of the added person
	 */
	public void addEntry(String firstName, String lastName, String phoneNumber)
	{
		//checks, if there is already an entry
		if (!isAlreadyAnEntry(firstName, lastName, phoneNumber))
		{
			//checks, if PhoneBook array can has enough space to add an entry
			if (this.arrayCounterPhoneBook == this.entries.length)
			{
				resize();
			}
			//adds entry
			PhoneBookEntry newEntry = new PhoneBookEntry(firstName, lastName, phoneNumber);
			this.entries[this.arrayCounterPhoneBook] = newEntry;
			this.arrayCounterPhoneBook++;
		}
	}
	
	/**
	 * isAlreadyAnEntry()
	 * This method checks, if an entry already exists in the phonebook. To find out,
	 * this method uses the comparePerson() function from the PhoneBookEntry class.
	 * If there is already an entry, the method checks, if the given phonenumber
	 * already is stored in the phonebookentry with method addTelephoneNumber()
	 * from PhoneBookEntry class.
	 * @param firstName -> String; the given firstName, which should be checked.
	 * @param lastName -> String; the given lastName, which should be checked.
	 * @param phoneNumber -> String; the given phoneNumer, which should be checked
	 * @return true/false -> booelan; if entry exists it returns true, else false.
	 */
	public boolean isAlreadyAnEntry(String firstName, String lastName, String phoneNumber)
	{
		for (int i = 0; i < this.arrayCounterPhoneBook; i++)
		{
			if (!this.entries[i].comparePerson(firstName, lastName))
			{
				continue;
			}
			this.entries[i].addTelephoneNumber(phoneNumber);
			return true;
		}
		return false;
	}
	
	/**
	 * print()
	 * This method prints each Entry in the PhoneBook. It calls the print()
	 * function from PhoneBookEntry class for each entry in the PhoneBook.
	 */
	public void print()
	{
		for (int i = 0; i < this.arrayCounterPhoneBook; i++)
		{
				this.entries[i].print();
		}
	}
	
	/**
	 * resize()
	 * This method resizes the the PhoneBook array, if there is not
	 * enough space for another entry. By default it doubles the space.
	 */
	public void resize()
	{
		int l = this.entries.length;
		PhoneBookEntry[] arrayNew = new PhoneBookEntry[2 * l];
		for (int i = 0; i < l; i++)
		{
			arrayNew[i] = this.entries[i];
		}
		this.entries = arrayNew;
	}
	
	/**
	 * nrOfEntries()
	 * This method returns the current number of entries in the
	 * phonebook, which is stored in the arrayCounterPhoneBook variable.
	 * @return arrayCounterPhoneBook -> int; the number of entries in the phonebook
	 */
	public int nrOfEntries()
	{
		return this.arrayCounterPhoneBook;
	}
	
	/**
	 * nrOfTelephoneNumbers()
	 * This method returns the total number of all telephonenumbers in this phonebook.
	 * Therefore it iterates over all entries and adds the counter variable of the
	 * PhoneBookEntry object to an local variable
	 * @return numberOfPhoneNumbers -> int; Variable, in which the total number of phonenumbers is stored.
	 */
	public int nrOfTelephoneNumbers()
	{
		int numberOfPhoneNumbers = 0;
		for (int i = 0; i < this.arrayCounterPhoneBook; i++)
		{
			numberOfPhoneNumbers += this.entries[i].arrayCounterPhoneBookEntry;
		}
		return numberOfPhoneNumbers;
	}
	
	/**
	 * searchNumber()
	 * This method searches the whole phonebook for a phoneNumber
	 * @param phoneNumber -> String; the given phoneNumber, which should be searched for
	 * @return this.entries[i] -> PhoneBookEntry; returns the first PhoneBookEntry object, which
	 *                            contains the phonenumber, else it returns null
	 */
	public PhoneBookEntry searchNumber(String phoneNumber)
	{
		for (int i = 0; i < this.arrayCounterPhoneBook; i++)
		{
			for (int j = 0; j < this.entries[i].arrayCounterPhoneBookEntry; j++)
			{
				if (phoneNumber.equals(this.entries[i].phoneNumbers[j]))
				{
					return this.entries[i];
				}
			}
		}
		return null;
	}
	
	/**
	 * searchPerson()
	 * This method searches the whole phonebook for a Person withe the given first and last name.
	 * @param firstName -> String; the firstName of the person, which should be searched.
	 * @param lastName -> String; the lastName of the person, which should be searched.
	 * @return this.entries[i] -> PhoneBookEntry; returns the first PhoneBookEntry object, which
	 *                            is equal to the given firstName and lastName, else return null.
	 */
	public PhoneBookEntry searchPerson(String firstName, String lastName)
	{
		for (int i = 0; i < this.arrayCounterPhoneBook; i++)
		{
			if (this.entries[i].comparePerson(firstName, lastName))
			{
				return this.entries[i];
			}
		}
		return null;
	}
}
