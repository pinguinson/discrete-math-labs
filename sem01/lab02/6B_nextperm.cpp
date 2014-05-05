#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, l, r, cur;
bool first, last;

long long fct[19] = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800,
39916800, 479001600, 6227020800, 87178291200, 1307674368000, 20922789888000,
355687428096000, 6402373705728000};

int main(){

    freopen("nextperm.in", "r", stdin);
    freopen("nextperm.out", "w", stdout);

    cin >> n;
    int perm[n], perm_buff[n];

    last = true;
    first = true;

    for (int i = 0; i < n; i++)
        cin >> perm[i];

    for (int i = 1; i < n; i++)
        if (perm[i] < perm[i-1])
            first = false;

    for (int i = 1; i < n; i++)
        if (perm[i] > perm[i-1])
            last = false;

    for (int i = 0; i < n; i++)
        perm_buff[i] = perm[i];


    //Find previous perm

    if (!first){
        for (int i = n - 2; i >= 0; i--)
            if (perm[i] > perm[i+1]){

                l = i+1;
                r = n-1;

                for (int j = l; j <= (l+r)/2; j++)
                    swap (perm[j], perm[r+l-j]);

                cur = 0;
                for (int j = l; j < n; j++)
                    if ((perm[j] > cur) && (perm[j] < perm[i])){
                        l = j;
                        cur = perm[j];
                    }

                swap (perm[l], perm[i]);

                break;
            }
    }
    if (first)
        for (int i = 0; i < n; i++)
            perm[i] = 0;

    //Output previous perm

    for (int i = 0; i < n-1; i++)
        cout << perm[i] << " ";
    cout << perm[n-1] << endl;


    for (int i = 0; i < n; i++)
        perm[i] = perm_buff[i];

    //Find next perm

    if (!last){
        for (int i = n - 2; i >= 0; i--)
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
    if (last)
        for (int i = 0; i < n; i++)
            perm[i] = 0;

    for (int i = 0; i < n-1; i++)
        cout << perm[i] << " ";
    cout << perm[n-1] << endl;
}
