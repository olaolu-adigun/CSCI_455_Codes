// Name:
// Loginid:
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


void addValue(ListType &list, const string &key, int value);

bool lookup(ListType &list, const string &key);

void printValue (ListType &list, const string &key);

void removeValue (ListType &list, const string &key);
 
int getValue(ListType &list, const string &key);

void printAll(ListType &list);

int size(ListType &list);



// keep the following line at the end of the file
#endif
