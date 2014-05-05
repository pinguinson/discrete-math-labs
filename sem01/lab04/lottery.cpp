#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <iomanip>

using namespace std;
int k, n, m;
int x[100007];
int y[100007];
double a[100007];
double b[100007];
double p[100007];
double f[100007];
double ans;

int main(){

    freopen("lottery.in", "r", stdin);
    freopen("lottery.out", "w", stdout);

    cin >> n >> m;

    for (int i = 1; i <= m; i++){
        cin >> x[i] >> y[i];
        a[i] = double(x[i]);
        b[i] = double(y[i]);
    }

    ans += n;

    b[0] = 0;
    p[0] = (a[1] - 1.0) / a[1];
    f[0] = 1.0 / a[1];

    for (int i = 1; i < m; i++){
        p[i] = f[i-1] * (a[i+1] - 1.0) / a[i+1];
        f[i] = f[i-1] / a[i+1];
        ans -= b[i] * p[i];
    }
    ans -= b[m] * f[m-1];

    printf("%.12Lf", ans);

}
