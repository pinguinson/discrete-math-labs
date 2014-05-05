#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;


long long numOfPermutation, alreadyWas, curFree, n, j;

long long fct[19] = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800,
39916800, 479001600, 6227020800, 87178291200, 1307674368000, 20922789888000,
355687428096000, 6402373705728000};

int main(){

    freopen("num2perm.in", "r", stdin);
    freopen("num2perm.out", "w", stdout);

    cin >> n >> numOfPermutation;
    bool was[n+1];
    int permt[n+1];

    for (int i = 1; i <= n; i++)
        was[i] = false;

    for (int i = 1; i <= n; i++){

        alreadyWas = numOfPermutation / fct[n-i];
        numOfPermutation = numOfPermutation % fct[n-i];
        curFree = 0;

        for (int k = 1; k <= n; k++)
            if (was[k] == false){
                curFree++;
                if (curFree == alreadyWas + 1){
                    j = k;
                    break;
                }
            }

        permt[i] = j;
        was[j] = true;
    }

    for (int i = 1; i < n; i++)
        cout << permt[i] << " ";
    cout << permt[n];
}
