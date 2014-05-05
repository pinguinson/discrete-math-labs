#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>
#include <cstring>

#define inf 1000000000

using namespace std;
int n, m, x, y;
long long res;
long long w[20][20];
long long d[20][1 << 20];
bool used[20];
vector<int> ans;

int step(int n){
    return (1 << n);
}

long long findCheapest(int i, int mask){
    if (d[i][mask] != inf)
        return d[i][mask];
    for (int j = 0; j < n; j++)
        if (w[i][j] >= 0 && (mask & step(j) == 1))
            d[i][mask] = min(d[i][mask], findCheapest(j, mask - step(j)) + w[i][j]);
    return d[i][mask];
}


int main(){

    freopen("salesman.in", "r", stdin);
    freopen("salesman.out", "w", stdout);

    cin >> n >> m;

    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            if (i == j)
                w[i][j] = 0;
            else
                w[i][j] = -1;
        }
    }

    for (int i = 0; i < m; i++){
        cin >> x >> y;
        x--;
        y--;
        cin >> w[x][y];
        w[y][x] = w[x][y];
    }





    for (int i = 0; i < n; i++)
        for (int mask = 0; mask < step(n); mask++)
            d[i][mask] = inf;
    for (int i = 0; i < n; i++)
        d[i][0] = 0;

    res = findCheapest(1, step(n) - 1);

    for (int i = 0; i < n; i++){
        for (int mask = 0; mask < step(n); mask++)
           cout << d[i][mask] << " ";
        cout << endl;
    }

    if (res == inf){
        cout << -1;
        return 0;
    }

    cout << res;
    return 0;

}
