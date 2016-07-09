#include <vector>
#include <iostream>
#include <fstream>

using namespace std;

vector<int> readVals() {
 
  vector<int> ans;
  int val;

  ifstream myfile ("input.txt");
  while (!myfile.eof()) {
    
    myfile >> val;
    ans.push_back(val);
  }
  return ans;
}

void printVals (vector<int> V) {
  

  int k = V.size();
  for (int i = 0; i < k; i++)
  {
    cout << V[i] << " ";
  }

}


vector<int> valsGTO (vector<int> V) {
  
  vector<int> ans;
  
  int k = V.size();
  for (int i = 0; i < k; i++)
  {
     if ( V[i] > 3)
     {
	ans.push_back(V[i]);
     }
  }
  return ans;
}

int main(){
  
  vector<int> Vec = readVals();
  cout << "Vector: ";
  printVals(Vec);
  cout <<endl;
  vector<int> filter = valsGTO(Vec);
  cout<<"Filtered Vector: " ;
  printVals(filter);
  cout << endl;
  cout << "Original vector: ";
  printVals(Vec);
  cout << endl;
  
}
