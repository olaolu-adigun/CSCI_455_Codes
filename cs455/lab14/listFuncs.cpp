
// Name:
// Loginid:
// CSCI 455 PA5
// Spring 2016


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
  key = theKey;
  value = theValue;
  next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
  key = theKey;
  value = theValue;
  next = n;
}


//*************************************************************************
// put the function definitions for your list functions below

void addValue(ListType &list, const string &key, int value){

 Node *p = list;
 int sent = -1; 
 if (p == NULL){
   list = new Node(key,value,list);
 }
 else{
   while(p->next!= NULL && sent!= 0){
     string th = p->key;
     if (key == th){
       sent = 0;
     }   
     p = p->next;
   }
 }
 if (sent == -1 && p->key != key){
   list = new Node(key, value,list);
 } 
}

bool lookup(ListType &list, const string &key){

 Node*p = list;
 if(p == NULL) {
   return false;
 }
 while (p->next != NULL){
  if (p->key == key){
    return true;
  }
  p = p->next;
 }
 if ( p->key == key){
  return true;
 }
 return false;
}


//void printValue (ListType &list, const string &key);

void removeValue (ListType &list, const string &key)
{
  string test = "";
  Node  *p = list;
  Node *upd;
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
        upd = p->next->next;
        p->next = upd;
        return; 
       }
      if(p->next->next == NULL){
        p->next == NULL;
        return;
     }
     p = p->next;  
    }
  }
}
 
int getValue(ListType &list, const string &key)
{
   Node*p = list;
 
   while (p->next != NULL){
     if (p->key == key){
       return p->value;
     }
     p = p->next;
   }
  return p->value;
}


void printAll(ListType &list){
  // cout<<"here"<<endl;
  Node *p = list;
  if (p==NULL){
    cout<<"<empty"<<endl;
  }
  else{
    while(p->next != NULL){
      cout<<"KEY: "<< p->key << " VALUE: "<< p->value<<endl;
      p = p->next;
    }
  }
  cout<<"KEY: "<< p->key << " VALUE: "<< p->value<<endl;
  cout<< " "<< endl;
}



int size(ListType &list){
  Node *p = list;
  int N = 0;
  if(p == NULL){
    return 0;
  }
  else {
    while(p->next != NULL){
      N = N+1;
      p = p->next;
    }
  }
  return N+1;
}


