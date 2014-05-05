#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>

using namespace std;

int n, k;
string s;


int main() {
    
    freopen("subsets.in", "r", stdin);
    freopen("subsets.out", "w", stdout);
    
    cin >> n;
    vector< vector<int> > subsets;
    vector<int> sset;
    subsets.push_back(sset);
    sset.push_back(1);
    subsets.push_back(sset);
    
    
    for (int i = 2; i <= n; i++){
        for (int j = 0; j < (1<<(i-1)); j++)
            subsets.push_back(subsets[j]);
        for (int j = int(subsets.size())/2; j < subsets.size(); j++)
            subsets[j].push_back(i);
    }
    
    for (int i = 0; i < subsets.size(); i++)
        for (int j = 0; j < i; j++)
            if (subsets[i] < subsets[j])
                swap(subsets[i], subsets[j]);
    
    for (int i = 0; i < subsets.size(); i++){
        for (int j = 0; j < subsets[i].size(); j++)
            cout << subsets[i][j] << " ";
        cout << endl;
    }
    
}
