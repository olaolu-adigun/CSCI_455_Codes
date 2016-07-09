// Name: ADIGUN OLAOLUWA
// Loginid: adigun
// CSCI 455 PA5
// Spring 2016


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// used by the Table class; not by any Table client code.


#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  
using namespace std;


struct Node {
 
  // Constructors
  // Defines the components of a list and constructor for creating a list
  string key;
  int value;

  Node *next;

  Node(const string &theKey, int theValue);

  Node(const string &theKey, int theValue, Node *n);
};

typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// Add a set of value and key to list
void addValueList(ListType &list, const string &key, int value);

// Lookup if a key is present on the table.
// return true iff key was already in the list
bool lookupList(ListType &list, const string &key);

//Removes key and its corrsponding value from the table 
void removeValueList (ListType &list, const string &key);

// Finds key on the table and obtains the corresponding value
// returns pointer to the value iff key is present
//         (returns NULL if the key is not present)
int* getValueList(ListType &list, const string &key);

// Prints all the keys and coreesponding values in list
void printAllList(ListType list);

  // Determines the number of elements in list.
  // returns int value of list size
  int sizeList(ListType list);


  // keep the following line at the end of the file
#endif
