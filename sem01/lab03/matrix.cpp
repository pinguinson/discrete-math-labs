#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>
#include <cstring>

#define infinity 1000000007
typedef long long ll;

using namespace std;

ll d[404][404];
ll s[404][404];
int n, v[404];

void countChain(int n){
    ll cur;
    for (int i = 1; i <= n; i++)
        d[i][i] = 0;

    for (int t = 2; t <= n; t++){
        for (int i = 1; i <= n - t + 1; i++){
            int j = i + t - 1;
            d[i][j] = infinity;
            for (int k = i; k <= j - 1; k++){
                cur = v[i-1] * v[k] * v[j] + d[k+1][j] + d[i][k];
                if (d[i][j] > cur){
                    d[i][j] = cur;
                    s[i][j] = k;
                }
            }
        }
    }
}

void printChain(int l, int r){
    if (l == r)
        cout << "A";
    else{
        cout << "(";
        printChain(l, s[l][r]);
        printChain(s[l][r] + 1, r);
        cout << ")";
    }
}


int main(){

    freopen("matrix.in", "r", stdin);
    freopen("matrix.out", "w", stdout);

    cin >> n;
    for (int i = 1; i <= n; i++)
        cin >> v[i - 1] >> v[i];

    countChain(n);
    printChain(1, n);
}
