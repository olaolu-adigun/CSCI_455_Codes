/*  Name:
 *  loginid:
 *  CS 455 Spring 2016, Extra Credit assignment
 *
 *  See ecListFuncs.h for specification of each function.
 */

#include <iostream>
#include <cassert>
#include "ecListFuncs.h"

using namespace std;

// Helper function for splitEvenOdd function 
static void writeList(ListType &theList, ListType &theListA, ListType &theListB);

/* Checks if the elements in a list are in increasing order.
   @params list of integers
   @returns bool true iff the elements are in increasing order
   PRE: list contains integer values
        list is well-formed
*/
bool isInOrder(ListType list) {
  // Check empty list
  if(list == NULL) {
    return true;
  }
  // Check single node
  if (list->next == NULL){
    return true;
  }
  
  while (list->next != NULL) {
    if (((list->data) - (list->next->data))> 0){ 
	return false;
    }
    list = list->next;
  }
  return true;
}


/* Inserts a node into its right position on an ordered list.
   @params list of well sorted integers in increasing order
   PRE: list contains integers arranged in increasing order.
        list is well-formed.
        itemP is a single node.
        itemP holds integer value.
*/
void insertInOrder(ListType & list, Node *itemP) {
  assert(isInOrder(list));     // checks the preconditions
  assert(itemP->next == NULL);
  // add the rest of the code after this line

  // Check if list is empty 
  if( list== NULL){
    list = itemP;
    return;  
  }
  Node *p = list;
  // Check if the position of itemP on list is 1.
  if ((itemP->data) <= (p->data)){
    itemP->next = list;
    list = itemP;
    return;
  }

  while(p->next != NULL){
    if((p->data <= itemP->data)&&(p->next->data >= itemP->data)){

       itemP->next = p->next;
       p->next = itemP; 
       return;
    }
    p = p->next;
  }
  p->next = itemP;
}


/* Sorts a list using the insertion sort algorithm.
   @params list of integers
   PRE: list is well-formed
*/
void insertionSort(ListType &list) {
 
  // Check if list is empty or contains single value
  if ((list == NULL)||(list->next == NULL)){
    return;  
  }
  
  Node *q = list;
  Node *p = list;
  p = p->next;
  list->next = NULL;
  while(p != NULL){
   q = p;
   p = p->next;
   q->next = NULL;
   insertInOrder(list, q);
  }
  
}

/* Separates a list number into 2 lists. One for numbers with 
   odd-value index and the other for number with even-value index.
   @params list of integers
   PRE: list is well-formed
*/
void splitEvenOdd(ListType &list, ListType &a, ListType &b){ 
  if((list == NULL)){
    return;
  }
   a = list;
   list = list->next;
  if (list->next == NULL){
   return; 
  }
  b = list;
  list = list->next;
  //int i = 1;
  Node *listA = a;
  Node *listB = b;
  writeList(list, listA, listB); 
  
}

/* This is the helper function for splitEvenOddd.
  It splits the values into 2 list based
  on the index value.
  @params list holds the integer values
          listA holds the odd-indexed values in list
          listB holds the even-indexed values in list
   PRE: list is well-formed
*/
void writeList(ListType &list, ListType &listA, ListType &listB){
  int i = 1;
  while (list != NULL){
   if((i%2) == 1){
      listA->next = listA->next->next;
      listA = listA->next;
    }
    else {
      listB->next = listB->next->next;
      listB = listB->next;
    }
    list = list->next;
    i = i+1;  
  }

  if ((i%2)==1){
    listA->next = NULL;
    return;
  }
  listB->next = NULL; 
}
