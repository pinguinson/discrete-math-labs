#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>

using namespace std;
int n, m;
int d[5005][5005];
string s1, s2;


int main() {

    freopen("levenshtein.in", "r", stdin);
    freopen("levenshtein.out", "w", stdout);

    cin >> s1 >> s2;

    m = s1.size();
    n = s2.size();

    for (int j = 0; j <= n; j++)
        d[0][j] = j;
    for (int i = 0; i <= m; i++)
        d[i][0] = i;
    for (int i = 1; i <= m; i++){
        for (int j = 1; j <= n; j++){
                d[i][j] = min(d[i-1][j] + 1, d[i][j-1] + 1);
            if (s1[i-1] == s2[j-1])
                d[i][j] = min(d[i][j], d[i-1][j-1]);
            else
                d[i][j] = min(d[i][j], d[i-1][j-1] + 1);
        }
    }

    cout << d[m][n];
    return 0;
}
