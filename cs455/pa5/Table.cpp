// Name: ADIGUN OLAOLUWA
// Loginid: adigun
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
/* REPRESENTATION INVARIANT:
  -- The data array holds pointers to lists.
  -- Each list holds keys and values.
  -- 1 <= sizeOf.data() <= 9973
  -- 0<= hashCode <= 9972
  -- The index of data array corresponds to the hashCode
*/


// Default Constructor
// Create data array of size 9973 and intializes the pointers in data
Table::Table() {
  data = new Node*[HASH_SIZE];
  // Initialize the pointers
  for (int i = 0; i < HASH_SIZE; ++i){
    data[i] = NULL;
  }
  hashSize = HASH_SIZE;  
} 

// Constructor
// Create data array of size hSize
Table::Table(unsigned int hSize) {
  data = new Node*[hSize];
  hashSize = hSize;
  
  // Initialize the pointers in the array
  for (unsigned int i = 0; i < hSize; ++i){
     data[i] = NULL;
  }
}

  
// Lookup checks if a key is present in Table.
// pram the key string
// returns pointer to the value iff key is present
//         (NULL if key is not present)
int * Table::lookup(const string &key) {
  int hCode = hashCode(key);
  Node* p = data[hCode];
 
  if (lookupList(data[hCode],key) == true){

    return (getValueList(p,key));
  } 
  return NULL;   // dummy return value for stub
}


// Remove a key and its corresponding value from the Table
// param  string key
// returns true iff key was present and removed
//        (false if key was not present)
bool Table::remove(const string &key) {
  int hCode = hashCode(key);
   
  if(lookupList(data[hCode],key) == true){
    removeValueList(data[hCode],key);
    return true;
  }
  return false;  // dummy return value for stub
}

// Insert a new set of key and value into the Table
// params string key and integer value
// returns true iff the key does not exist already
//         (false if the key exist and no insertion was done)  
bool Table::insert(const string &key, int value) {
  int hCode = hashCode(key);

  if( data[hCode] == NULL || (lookupList(data[hCode],key)==false)){
    addValueList(data[hCode],key,value);
    return true;
  }
  return false;  // dummy return value for stub
}

// Determines the number of enteries on table
// returns int size of enteries on table 
int Table::numEntries() const {
  int sum = 0;
  Node* p;
  for ( unsigned int i = 0; i< hashSize; ++i){
    p = data[i];
    sum = sum + sizeList(p);
  }
  return sum;      // dummy return value for stub
}
  
// Print all the enteries in table
void Table::printAll() const {
  
  for (unsigned int i = 0; i < hashSize; ++i){
  
    if ( data[i] != NULL){
      printAllList(data[i]);
    }
  }
}


// Prints the statistic of enteries on table
// param ostream
void Table::hashStats(ostream &out) const { 
  int entry = 0, longest = 0, notEmpty = 0 , bucket = 0, hsize = 0;
    
  for (unsigned int i = 0; i < hashSize; ++i){ 
    hsize = sizeList(data[i]);
    entry = entry + hsize;
    if(hsize != 0){
      notEmpty = notEmpty + 1;
    }
        
    if (longest < hsize){
      longest = hsize;
    } 
    bucket = bucket+1;
  };
    
  out<<"Number of buckets: "<< bucket <<endl;
  out<<"Number of entries: "<< entry <<endl;
  out<<"Number of non-empty buckets: "<< notEmpty << endl;
  out<<"Longest chain: " << longest << endl;
}

