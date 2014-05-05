#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>

using namespace std;

int n;
long long k;

int main(){

    freopen ("num2brackets.in", "r", stdin);
    freopen ("num2brackets.out", "w", stdout);

    cin >> n >> k;
    k++;
    long long d[2*n+1][n+1];

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
    string s;
    for (int i = 2*n-1; i >= 0; i--)
        if (depth + 1 <= n && d[i][depth+1] >= k) {
            s += '(';
            depth++;
        }
        else {
            s += ')';
            if (depth + 1 <= n)
                k -= d[i][depth+1];
            depth--;
        }
    cout << s;
}
