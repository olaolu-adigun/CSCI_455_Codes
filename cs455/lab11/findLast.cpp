#include <vector>
#include <iostream>
#include <fstream>

using namespace std;

vector<int> readVals() {
 
  vector<int> ans;
  int val;
  
  ifstream myfile("input.txt");

  while (!myfile.eof()) {
    myfile >> val;
    ans.push_back(val);
  }
  return ans;
}


void printVals (vector<int> V) {
  
  int k = V.size();
  for (int i =0; i < k; i++)
  {
    cout << V[i] << " ";
  }

}


vector<int> valsGTO (vector<int> V) {
  
  vector<int> ans;
  
  int k = V.size();
  for (int i =0; i < k; i++)
  {
     if ( V[i] > 3)
     {
	ans.push_back(V[i]);
     }
  }
  return ans;
}


int findLast(vector<int> v , int target) {
  
  int val = -1;
  int k = v.size(); 
  for (int i =0; i < k; i++)
  {
     if ( v[i] == target)
     {
	val = i;
     }
  }
  return val;
}

void testFindLast(vector<int> v, int target) {

  int t = findLast(v,target);
    
  if (t == -1)
   {
     cout << target << " is not in vector ";
     printVals(v);
     cout <<endl; 
   }
   else 
   {
      cout << "Last position is "<< t <<endl;
      cout << "Target is " << target<< endl;
      cout << "Vector is: ";
      printVals(v);
      cout<<endl;
   }

}

int main(){
  
  vector<int> V = readVals();
  testFindLast(V,3);
  testFindLast(V,6);
  
}
