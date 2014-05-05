#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <iomanip>

using namespace std;
int k, n, a, b;
int p[1000007];
int m[1000007];
long long ans;
float result;

int main(){
    
    freopen("exam.in", "r", stdin);
    freopen("exam.out", "w", stdout);
    
    cin >> k >> n;
    
    for (int i = 0; i < k; i++){
        cin >> a >> b;
        ans += a * b;
    }
    
    result = float(ans) / float(n) / 100;
    printf("%.5f", result);
}
