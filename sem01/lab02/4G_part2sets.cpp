#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>

using namespace std;

int n, k;

int main(){

    freopen ("part2sets.in", "r", stdin);
    freopen ("part2sets.out", "w", stdout);

    cin >> n >> k;

    int maximums[n];
    int block[n];
    int cur = 0;

    for (int i = 0; i < n; i++){
        block[i] = 1;
        maximums[i] = 1;
    }

    while (cur != n){

        cur = 0;
        for (int i = 0; i < n; i++)
            cur = max(cur, block[i]);

        if (cur == k){
            for (int i = 1; i <= k; i++){
                for (int j = 0; j < n; j++)
                    if (block[j] == i)
                        cout << j+1 << " ";
                cout << endl;
            }
            cout << endl;
        }

        for (int i = n-1; i >= 0; i--)
            if (block[i] <= maximums[i-1]){
                block[i]++;
                maximums[i] = max(block[i], maximums[i-1]);

                for (int j = i+1; j < n; j++){
                    block[j] = 1;
                    maximums[j] = maximums[i];
                }
                break;
            }
    }
}
