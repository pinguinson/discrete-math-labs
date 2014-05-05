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
double tot, ans, buff;
double p[10007];

int main(){

    freopen("shooter.in", "r", stdin);
    freopen("shooter.out", "w", stdout);

    cin >> n >> m >> k;

    for (int i = 0; i < n; i++)
        cin >> p[i];

    for (int i = 0; i < n; i++)
        p[i] = 1 - p[i];

    for (int i = 0; i < n; i++){
        buff = p[i];
        for (int j = 1; j < m; j++)
            p[i] *= buff;
    }

    for (int i = 0; i < n; i++)
        tot += p[i];

    if (tot == 0){
		cout << 0;
		return 0;
	}

    ans = p[k-1] / tot;

    printf("%.13lf", ans);
    return 0;
}
