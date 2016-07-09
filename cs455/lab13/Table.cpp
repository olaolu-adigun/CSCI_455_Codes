// Name: ADIGUN OLAOLUWA
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

static void diagnostic(int &entry, int &longest, int &notEmpty, int &bucket, const unsigned &hashSize, Node* *&data);

Table::Table() {
   data = new Node*[HASH_SIZE];
   for (int i = 0; i < HASH_SIZE; ++i){
     data[i] = NULL;
   }
   
   hashSize = HASH_SIZE;  

}


Table::Table(unsigned int hSize) {
  data = new Node*[hSize];
  hashSize = hSize;
  for (unsigned int i = 0; i < hSize; ++i){
    data[i] = NULL;
  }
}


int * Table::lookup(const string &key) {
  int hCode = hashCode(key);
  Node* p = data[hCode];
 
  if (lookupList(data[hCode],key) == true){

	return (getValueList(p,key));
	} 
  return NULL;   // dummy return value for stub
}

bool Table::remove(const string &key) {
   int hCode = hashCode(key);
   
   //Node* p = data[hCode];
   
   if(lookupList(data[hCode],key) == true){
      removeValueList(data[hCode],key);
      return true;
	}
  return false;  // dummy return value for stub
}

bool Table::insert(const string &key, int value) {
  int hCode = hashCode(key);
  //Node* p;
  // printAllList(data[hCode]);
  // cout<< typeid(p).name()<<endl;
  //p = data[hCode];
  if( data[hCode] == NULL || (lookupList(data[hCode],key)==false)){
   addValueList(data[hCode],key,value);
   
   return true;
}
  return false;  // dummy return value for stub
}

int Table::numEntries() const {
  int sum = 0;
  Node* p;
  for ( unsigned int i = 0; i< hashSize; ++i){
	p = data[i];
        sum = sum + sizeList(p);
}
  return sum;      // dummy return value for stub
}
  

void Table::printAll() const {
  
 for (unsigned int i = 0; i < hashSize; ++i){
        //cout<<(data[i] == NULL) << endl;
 	if ( data[i] != NULL){
	  printAllList(data[i]);
	}
  }
}

void Table::hashStats(ostream &out) const { 
    int entry = 0, longest = 0, notEmpty = 0 , bucket = 0, hsize = 0;
    //Node * *theData = data; 
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
    //cout<<sizeof(theData)<<endl;
    //diagnostic(entry, longest, notEmpty, bucket, hashSize, theData);
    
    out<<"Number of buckets: "<< bucket <<endl;
    out<<"Number of entries: "<< entry <<endl;
    out<<"Number of non-empty buckets: "<< notEmpty << endl;
    out<<"Longest chain: " << longest << endl;
}

// add definitions for your private methods here

static void diagnostic(int &entry, int &longest, int &notEmpty, int &bucket, const unsigned &hashSize, Node* *&data){
  Node *p;
  int hsize;
  // int entry = 0, longest = 0, notEmpty = 0 , bucket = 0;
  //cout<<hashSize<<endl;
  for (unsigned int i = 0; i < hashSize; ++i){
	p = data[i];
        //cout<< typeid(p).name()<<endl;
	hsize = sizeList(p);
	entry = entry + hsize;
	if(hsize != 0){
	   notEmpty = notEmpty + 1;
	}
        
	if (longest < hsize){
	  longest = hsize;
	} 
	bucket = bucket+1;
  }
}

