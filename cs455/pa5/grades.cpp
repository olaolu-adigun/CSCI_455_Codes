// Name:ADIGUN OLAOLUWA 
// Loginid: adigun
// CSCI 455 PA5
// Spring 2016

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"
#include <string>
// cstdlib needed for call to atoi
#include <cstdlib>
#include <sstream>

// Define all the functions
void insert(string &command, Table  &grades);
void change(string &command, Table  &grades);
void lookUp(string &command, Table  &grades);
void remove(string &command, Table  &grades);
void print(string &command, Table  &grades);
void printHelp();
void commandControl(string &word, Table &grades, string &command);
void validCommand(string &command, Table &grades);

// This function controls the command options for grades.
// This function uses commandControl function.
// params string command and Table grades
// PRE: -- Valid commands have corect arguments
void validCommand(string &command, Table &grades){
  string word;
  while (!cin.fail()&& command!="quit"){
    std::stringstream stream(command);
    stream >> word;
    // Terminate if command is quit
    if (word == "quit"){
      return;
    } 
    // Use the helper to run other commands 
    commandControl(word,grades, command);
    cout <<"cmd> ";
    getline(cin, command);
  }
}


// This function calls the helper functiopn for each command
// params string command, string word, and Table grades
// PRE: -- Valid commands  have correct input arguments
void commandControl(string &word, Table &grades, string &command){

    // Check the command and run the corresponding helper function
    if (word == "insert"){
      insert(command, grades);
    }
    else if (word == "change"){
      change(command, grades);
    }
    else if (word == "lookup"){
      lookUp(command, grades);
    }
   else if (word == "remove"){
      remove(command, grades);
   }
   else if (word == "print"){
     grades.printAll();
   }
   else if(word == "size"){
     cout<< "Number of Entires: "<< grades.numEntries()<<endl;
   }
   else if (word == "stats"){ 
     grades.hashStats(cout);
   }
   else if(word == "help"){
     printHelp();
   }
   else {
       cout<<"ERROR: invalid command"<<endl;
      printHelp();
   }
}


// This function prints the help summary.
// Helper function for command control. 
void printHelp(){
  // Print help summary
  cout<< "Type \'insert name score\' to add new set of name and score. E.g. \'insert Mike 10\'"<<endl;
  cout<< "Type \'change name score\' to change score of name. E.g. \'change Mike 40\'"<<endl;
  cout<< "Type \'lookup name\' to print the deatils of from the table. E.g. \'lookup Mike\'"<<endl;
  cout<< "Type \'remove name\' to delete name for the table. E.g. \'remove Mike\'"<<endl;
  cout<< "Type \'print\' to view the content of the table"<<endl;
  cout<< "Type \'size\' to see the number of entries in the table"<<endl;
  cout<< "Type \'stats\' to view the statistics of the table"<<endl;
  cout<< "Type \'help\' to read brief command history"<<endl;
  cout<< "Type \'quit\' to exit the program.'"<<endl;
}


// Helper function for insert command.
// params string command containing key and value, and Table grades
// Prints a message if the key was already on the Table. 
// PRE: Input arguments in command are valid.
//     -- Key is string, and value is an integer.
void insert(string &command, Table &grades){
  string action, key;
  int value;
  bool stat;
  std::stringstream stream(command);
  // Break the command into components
  stream >> action;
  stream >> key;
  stream >> value;
  // Insert the new arguments
  stat = grades.insert(key, value);
  if (stat == false){
    cout<<"The name exist already on the table"<< endl;
  }
  return;
}


// Helper function for change command
// params string command and Table grades
// Prints a message if the key to be changed does not exist on the Table
// PRE: The argument of command is valid. i.e. type string. 
void change(string &command, Table  &grades){
  string action, key;
  int value;
  bool stat;
  int *val;
  std::stringstream stream(command);
  // Break the command into components
  stream >> action;
  stream >> key;
  stream >> value;
  // Lookup the table and act accordingly
  val = grades.lookup(key);
  if (val == NULL){
    cout <<" The name is not present on the table"<<endl;
    return;
  }
  stat = grades.remove(key);
  stat = grades.insert(key, value);
  return;
}


// Helper function to lookup a name on the Table
// Prints a message indicating whether the name is present or not
// PRE: The argument of in command is valid 
void lookUp(string &command,Table  &grades){
  string action, key;
  std::stringstream stream(command);
  // Break the command into components
  stream >> action;
  stream >> key;
  
  int *val = grades.lookup(key);
  if (val == NULL){
    cout <<"The name is not present on the table"<<endl;
    return;
  }

  cout<< "NAME: " <<key<< "   SCORE: "<< *val<<endl;
}


// Helper Function to remove a key and its value from the table
// Prints a message about the action 
//  PRE: The argument for remove in command string is valid
void remove(string &command, Table  &grades){
  string action, key;
  bool stat;
  int *val;
  std::stringstream stream(command);
  // Break the commands into components
  stream >> action;
  stream >> key;
  val = grades.lookup(key);

  if (val == NULL){
    cout <<" The name is not present on the table"<<endl;
    return;
  }
  stat = grades.remove(key);
  return;
}


int main(int argc, char * argv[]) {

  // gets the hash table size from the command line
  string command;
  int hashSize = Table::HASH_SIZE;

  Table * grades;  // Table is dynamically allocated below, so we can call
                   // different constructors depending on input from the user.
  if (argc > 1) {
    hashSize = atoi(argv[1]);  // atoi converts c-string to int

    if (hashSize < 1) {
      cout << "Command line argument (hashSize) must be a positive number" 
	   << endl;
      return 1;
    }

    grades = new Table(hashSize);

  }
  else {   // no command line args given -- use default table size
    
    grades = new Table();
  }

  // add more code here
  // Reminder: use -> when calling Table methods, since grades is type Table*
   grades->hashStats(cout);
   cout <<"cmd> ";
  getline (cin, command);
  validCommand(command, *grades);

  return 0;
}
