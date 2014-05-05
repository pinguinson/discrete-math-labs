#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, k, ans;


int main(){

    freopen ("choose2num.in", "r", stdin);
    freopen ("choose2num.out", "w", stdout);

    cin >> n >> k;
    int choose[k+1];
    int c[n+1][n+1];

    for (int i = 0; i <= n; i++)
        for (int j = 0; j <= n; j++)
            c[i][j] = 0;

    for (int i = 1; i <= n; i++){
        c[i][1] = i;
        c[i][i] = 1;
    }

    for (int i = 3; i <= n; i++)
        for (int j = 2; j < i; j++)
            c[i][j] = c[i-1][j-1] + c[i-1][j];


    choose[0] = 0;
    for (int i = 1; i <= k; i++)
        cin >> choose[i];

    for (int i = 1; i < k; i++)
        if (choose[i] > choose[i-1] + 1)
            for (int j = choose[i-1] + 1; j < choose[i]; j++)
                ans += c[n-j][k-i];

    if (choose[k] > choose[k-1] + 1)
        ans += choose[k] - choose[k-1] - 1;

    cout << ans;
}
