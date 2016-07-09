// Name:
// Loginid:
// CSCI 455 PA5
// Spring 2016

// Table.cpp  Table class implementation


/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
  data = new Node*[HASH_SIZE];
  hashSize = HASH_SIZE;
}


Table::Table(unsigned int hSize) {
  data = new Node*[hSize];
  hashSize = hSize; 
}


int * Table::lookup(const string &key) {

  
  return NULL;   // dummy return value for stub
}

bool Table::remove(const string &key) {

  return false;  // dummy return value for stub
}

bool Table::insert(const string &key, int value) {
  int hCode = hashCode(key);
  Node* p = data[hCode];
  bool b = listFuncs::lookup(p,key);
  return true;  // dummy return value for stub
}

int Table::numEntries() const {
  return 0;      // dummy return value for stub
}


void Table::printAll() const {

}

void Table::hashStats(ostream &out) const {
  
}


// add definitions for your private methods here
