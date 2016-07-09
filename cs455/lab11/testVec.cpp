#include <vector>
#include <iostream>
#include <fstream>
using namespace std;

vector<int> readVals() {
 
   vector<int> ans;

  int val;

  ifstream myfile("input.txt");

      while (!myfile.eof())
	{
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


int main(){
  
  vector<int> V;
 
  V = readVals();
  printVals(V);
  cout << " " << std::endl;
}
