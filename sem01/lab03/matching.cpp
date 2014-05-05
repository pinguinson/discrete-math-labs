#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>
#include <cstring>
#include <utility>

using namespace std;
vector< pair<int, int> > v[100007];
pair<int, int> cur;
long long a[100007], b[100007], c[100007];
int n, x, y, z;
bool used[100007];

void dfs(int x){
    used[x] = true;
    for (int i = 0; i < v[x].size(); i++){
        int child = v[x][i].first;
        if (used[child])
            continue;
        dfs(child);
        a[x] = max(a[x], b[child] + v[x][i].second - c[child]);
        b[x] += c[child];
   }
    a[x] += b[x];
    c[x] = max(a[x], b[x]);
}
int main(){

    freopen("matching.in", "r", stdin);
    freopen("matching.out", "w", stdout);

    cin >> n;
    for (int i = 1; i < n; i++){
        cin >> x >> y >> z;
        cur = make_pair(y, z);
        v[x].push_back(cur);
        cur = make_pair(x, z);
        v[y].push_back(cur);
    }

    for (int i = 1; i <= n; i++)
        used[i] = false;

    dfs(1);
    cout << c[1];
}
