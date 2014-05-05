#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <algorithm>

using namespace std;

int n;

int main(){
    
    freopen("nextmultiperm.in", "r", stdin);
    freopen("nextmultiperm.out", "w", stdout);
    
    cin >> n;
    int p[n];
    
    for (int i = 0; i < n; i++)
        cin >> p[i];
    
    if (!next_permutation(p, p+n)){
        for (int i = 0; i < n-1; i++)
            cout << "0 ";
        cout << "0" << endl;
    }
    
    else{
        for (int i = 0; i < n-1; i++)
            cout << p[i] << " ";
        cout << p[n-1] << endl;
    }
}