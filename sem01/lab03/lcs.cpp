#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>

using namespace std;

int d[2002][2002];
int m, n, ctr;
int x[2002], y[2002];

void PrintLCS(int a, int b)
{
    if (a == 0 || b == 0)
        return;
    if (x[a-1] == y[b-1])
    {
        PrintLCS(a-1, b-1);
        cout << x[a-1] << " ";
    }
    else if (d[a][b] == d[a-1][b])
        PrintLCS(a-1, b);
    else
        PrintLCS(a, b-1);
}

int main()
{
    freopen("lcs.in", "r", stdin);
    freopen("lcs.out", "w", stdout);

    cin >> m;
    for (int i = 0; i < m; i++)
        cin >> x[i];
    cin >> n;
    for (int i = 0; i < n; i++)
        cin >> y[i];

    for (int i = 0; i <= m; i++)
        for (int j = 0; j <= n; j++)
            d[i][j] = 0;

    for (int i = 1; i <= m; i++)
        {
        for (int j = 1; j <= n; j++)
        {
            if (x[i-1] == y[j-1])
                d[i][j] = d[i-1][j-1]+1;
            else
                d[i][j] = max(d[i][j-1], d[i-1][j]);
        }
    }

    cout << d[m][n] << endl;
    PrintLCS(m, n);
}
