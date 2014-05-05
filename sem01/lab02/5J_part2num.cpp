#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <sstream>

using namespace std;

long long n, k, cur, ans;
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
    
    freopen("part2num.in", "r", stdin);
    freopen("part2num.out", "w", stdout);
    
    string s;
    cin >> s;
    
    for (long long i = 0; i < 110; i++)
        for (long long j = 0; j < 110; j++)
            d[i][j] = -1;
    
    long long total = 0;
    vector <long long> parts;
    
    istringstream iss(s);
    char ch;
    
    while (iss >> cur){
        parts.push_back(cur);
        total += cur;
        iss >> ch;
    }
    
    ans = 0;
    cur = 1;
    
    for (long long i = 0; i < parts.size(); i++){
        ans += F2(total, cur, parts[i]);
        total -= parts[i];
        cur = parts[i];
    }
    cout << ans;
}
