#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, cur, k;
long long ans;

long long factorial(int a) {
    return !a ? 1 : a * factorial(a - 1);
}

int main() {
	
    freopen("perm2num.in", "r", stdin);
    freopen("perm2num.out", "w", stdout);
    
    cin >> n;
    
    int perm[n+1];
    
    for (int i = 1; i <= n; i++)
        perm[i] = 0;
    
    for (int i = 0; i < n; i++){
        cin >> cur;
        
        perm[cur] = 1;
        k = 0;
        for (int j = 1; j < cur; j++)
            k += perm[j];
        k = cur - 1 - k;
        if (k < 0)
            k = 0;
        ans += factorial(n-i-1)*k;
    }
    
    cout << ans;
    
    
    
}