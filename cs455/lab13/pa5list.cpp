// Name: ADIGUN OLAOLUWA
// loginid: 2543889589
// CS 455 Lab 13 / PA5

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile (and lab 13 Makefile) includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"

void testAddValue (Node * &list, const string &key, int value){
 cout<< "The list before adding value is:" << endl;
 printAllList(list);
 cout<< "New key is: " << key <<". New value is: " <<value<<endl;
 addValueList(list,key,value);
 cout<<"The new list is: "<< endl;
printAllList(list);
}


void testLookUp(Node * &list, const string key){
cout<< "Lookup "<< key<<endl;
cout<<lookupList(list, key)<<endl;

}

int main() {

Node *g  = new Node("Mike",23);

testAddValue(g, "Henry", 2);
testAddValue(g,"Kate",3);
testAddValue(g,"Harry", 4);
testAddValue(g,"Dave", 5);
testLookUp(g, "Harry");
testLookUp(g,"Sol");

cout<<*(getValueList(g,"Henry"))<<endl;
cout<<*(getValueList(g, "Harry"))<<endl;
cout<< "Test the size of list"<< endl;
cout<<sizeList(g)<<endl;
printAllList(g);
cout<<"Remove KATE from the list"<<endl;
removeValueList(g, "Kate");
printAllList(g);
cout<<"REMOVE MIKE from the list" <<endl;
removeValueList(g, "Mike");
printAllList(g);
}

 
