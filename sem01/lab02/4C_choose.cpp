#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, k;

int main(){

    freopen ("choose.in", "r", stdin);
    freopen ("choose.out", "w", stdout);

    cin >> n >> k;

    int limit[k+1], choose[k+1];

    for (int i = 1; i <= k; i++){
        limit[i] = n - k + i;
        choose[i] = i;
    }



    int cur = k;
    while (cur > 0){
        for (int i = 1; i < k; i++)
            cout << choose[i] << " ";
        cout << choose[k] << endl;

        cur = 0;
        if (choose[k] != limit[k])
            cur = k;
        else
            for (int i = k-1; i >= 1; i--)
                if (choose[i+1] - choose[i] > 1){
                    cur = i;
                    break;
                }

        if (cur > 0){
            choose[cur]++;
            for (int i = cur+1; i <= k; i++)
                choose[i] = choose[i-1] + 1;
            cur = k;
        }
    }
}
