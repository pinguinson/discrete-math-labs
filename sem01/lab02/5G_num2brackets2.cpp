#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <iomanip>

using namespace std;

long long cur, k;
int n, z;

int main(){

    freopen ("num2brackets2.in", "r", stdin);
    freopen ("num2brackets2.out", "w", stdout);

    cin >> n >> k;
    k++;
    long long d[2*n+1][n+1];
    char st[2*n];

    for (int i = 0; i <= 2*n; i++)
        for (int j = 0; j <= n; j++)
            d[i][j] = 0;

    d[0][0] = 1;
    for (int i = 0; i < 2*n; i++)
        for (int j = 0; j <= n; j++) {
            if (j+1 <= n)
                d[i+1][j+1] += d[i][j];
            if (j > 0)
                d[i+1][j-1] += d[i][j];
        }

    int depth = 0;
    string ans;

    for (int i = 2*n - 1; i >= 0; i--){

        if (depth + 1 <= n)
           cur = d[i][depth+1] << ((i-depth-1) / 2);
        else
            cur = 0;

        if (cur >= k){
            ans += '(';
            st[z++] = '(';
            depth++;
            continue;
        }

        k -= cur;

        if (z > 0 && st[z-1] == '(' && depth - 1 >= 0)
            cur = d[i][depth-1] << ((i-depth+1) / 2);
        else
            cur = 0;

        if (cur >= k){
            ans += ')';
            z--;
            depth--;
            continue;
        }

        k -= cur;

        if (depth + 1 <= n)
            cur = d[i][depth+1] << ((i-depth-1) / 2);
        else
            cur = 0;

        if (cur >= k){
            ans += '[';
            st[z++] = '[';
            depth++;
            continue;
        }

        k -= cur;

        ans += ']';
        depth--;
        z--;
    }

    cout << ans;
}
