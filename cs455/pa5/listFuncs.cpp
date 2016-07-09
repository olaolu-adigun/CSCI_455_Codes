// Name: ADIGUN OLAOLUWA 
// Loginid: adigun
// CSCI 455 PA5
// Spring 2016


#include <iostream>
#include <cassert>
#include "listFuncs.h"
#include<string>
/* 
  listFuncs
  Creates a list from a connection of nodes.
  Defines method for manipulating list.
 */

using namespace std;

/* REPRESENTATION INVARIANT:
   -- All the nodes are non-empty
   -- Each node stores a string and an integer
*/

// Constructor
// Creates a list of one single node
// params string theKey and integer theValue
Node::Node(const string &theKey, int theValue) {
  key = theKey;
  value = theValue;
  next = NULL;
}

// Constructor 
// Constructs a new list from list and new parameters
// params list n, string theKey, and integer theValue
Node::Node(const string &theKey, int theValue, Node *n) {
  key = theKey;
  value = theValue;
  next = n;
}


//*************************************************************************
// put the function definitions for your list functions below


// Add a set of value and key to the list
// params list and a set of key and list to be added as a node.
void addValueList(ListType &list, const string &key, int value){    
  Node *p = list;
  int sentinel = -1; 

  if (p == NULL){
    list = new Node(key,value,list);
    return;
  }
  else{
    while(p->next!= NULL && sentinel!= 0){
      string theKey = p->key;
      if (key == theKey){
         sentinel = 0;
      }   
      p = p->next;
    }
  }

  if (sentinel == -1 && p->key != key){
    list = new Node(key, value,list);
  } 
}


// Lookup a key on a list
// params list of ListType and string key
// returns true iff key is present on the table 
bool lookupList(ListType &list, const string &key){ 
  Node*p = list;

  if(p == NULL) {
    return false;
  }
  while (p->next != NULL){
    if(p->key == key){
      return true;
    }
    p = p->next;
  }
  if ( p->key == key){
    return true;
  }
  return false;
}


// Removes key from the list
// params list if ListType and string key
// PRE: Key is present on the list.
//      Lookup key returns true for list.
void removeValueList (ListType &list, const string &key){
  string test = "";
  Node  *p = list;
  Node *d;
  if(p->key == key){
    list = list-> next;
    delete p;
    return;
  }
  if (p->next == NULL){
     p = NULL;
  }
  else{
    while( p->next != NULL) {
      if(p->next->key == key){
         Node*  update = p->next->next;
	 d = p->next;
         p->next = update;
	 delete d;
         return; 
      }
      if(p->next->next == NULL){
        d = p->next;
	delete d;
        return;
      }
      p = p->next;  
    }
  }
} 
 

// Gets the corresponding value of key from the list
// params list of ListType and string key
// returns the pointer to the value if key is present
//         (NULL if key is not present on the list)
int* getValueList(ListType &list, const string &key){ 
  Node *p = list;

  if (p==NULL){
     return NULL;
  }
  if(p->key == key){
    return &(p->value);
  }
  while (p->next != NULL){
    if (p->key == key){
      return &(p->value);
    }
    p = p->next;
  }
  return &(p->value);
}


// Prints all the keys and corrsponding value on the list.
// param list of ListType
void printAllList(ListType list){
  
  if (list == NULL){
    cout<<"<empty"<<endl;
    return;
  }
  else{
    while(list != NULL){
      cout<<"KEY: "<< list->key << " VALUE: "<< list->value <<endl;
      list = list->next;
    }
  }
}


// Determine the size of list.
// param list of ListType
// returns the size of type integer 
int sizeList(ListType list){
  int N = 0;

  if(list == NULL){
    return 0;
  }
  else {
    while(list != NULL){
      N = N+1;
      list = list->next;
    }
  }
  return N;
}
