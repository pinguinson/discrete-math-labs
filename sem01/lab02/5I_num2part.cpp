#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

long long n, k;
long long d[200][200];

long long F1(long long x, long long y){
    if (d[x][y] >= 0)
        return d[x][y];
    
    if (y == x || x == 0){
        d[x][y] = 1;
        return 1;
    }
    d[x][y] = 0;
    for (long long i = y; i <= x; i++)
        d[x][y] += F1(x - i, i);
    return d[x][y];
}

long long F2(long long x, long long y, long long z){
    return F1(x, y) - F1(x, z);
}

int main(){
    
    freopen("num2part.in", "r", stdin);
    freopen("num2part.out", "w", stdout);
    
    cin >> n >> k;
    long long cur = 0;
    for (long long i = 0; i < 200; i++)
        for (long long j = 0; j < 200; j++)
            d[i][j] = -1;
    
    long long ans[n];
    long long l = 1;
    long long t;
    
    while (n > 0){
        for (long long i = n; i >= l; i--){
            t = F2(n, l, i);
            if (k >= t){
                ans[cur++] = i;
                n -= i;
                k -= t;
                l = i;
                break;
            }
        }
    }
    
    for (long long i = 0; i < cur; i++){
        cout << ans[i];
        if (i != cur - 1)
            cout << "+";
    }
}