#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, ans, l, r;
long long k;
bool fl;

long long factorial(int a) {
    return !a ? 1 : a * factorial(a - 1);
}

int main() {
	
    freopen("permutations.in", "r", stdin);
    freopen("permutations.out", "w", stdout);
    
    cin >> n;
    
    int perm[n];
    
    for (int i = 0; i < n; i++)
        perm[i] = i + 1;
    
    for (int k = 0; k < factorial(n); k++) {
        
        for (int j = 0; j < n; j++)
            cout << perm[j] << " ";
        cout << endl;
        
        for (int i = n - 2; i >= 0; i--){
            if (perm[i] < perm[i+1]){
                l = i+1;
                
                for (int j = l; j < n; j++)
                    if ((perm[j] < perm[l]) && (perm[j] > perm[i]))
                        l = j;
                
                swap (perm[l], perm[i]);
                
                l = i+1;
                r = n-1;
                
                for (int j = l; j <= (l+r)/2; j++)
                    swap (perm[j], perm[r+l-j]);
                
                break;
            }
        }
    }
    
}