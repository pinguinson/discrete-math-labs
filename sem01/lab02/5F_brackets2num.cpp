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

    freopen ("brackets2num.in", "r", stdin);
    freopen ("brackets2num.out", "w", stdout);

    string s;
    cin >> s;
    n = 0;
    k = 0;
    for (int i = 0; i < s.length(); i++)
        if (s[i] == '(')
            n++;

    long long d[2*n+1][2*n+1];

    for (int i = 0; i <= 2*n; i++)
        for (int j = 0; j <= 2*n; j++)
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

    for (int i = 0; i < 2*n; i++)
        if (s[i] == '(')
            depth++;
        else {
            k += d[2*n - i - 1][depth + 1];
            depth--;
        }

    cout << k;
}
