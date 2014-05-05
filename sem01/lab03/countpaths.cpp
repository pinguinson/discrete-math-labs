#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>
#include <cstring>

#define MAXN 100007

using namespace std;
int n, m, a, b;
vector<int> g[MAXN];
vector<int> q[MAXN];
vector<int> r[MAXN];
vector<int> h[MAXN];
bool used[MAXN];
vector<int> ans;

void dfs (int v) {
	used[v] = true;
	for (size_t i = 0; i < g[v].size(); ++i){
		int to = g[v][i];
		if (!used[to])
			dfs (to);
	}
	ans.push_back(v);
}

int ways (int n){
    if (n == 0)
        return 1;
    int ans = 0;
    for (int i = 0; i < r[n].size(); i++)
        ans += ways(r[n][i]);
    return ans;
}

void topological_sort() {
	for (int i = 0; i < n; ++i)
		used[i] = false;
	ans.clear();
	for (int i = 0 ; i < n; ++i)
		if (!used[i])
			dfs(i);
	reverse(ans.begin(), ans.end());
}

int main(){

    freopen("countpaths.in", "r", stdin);
    freopen("countpaths.out", "w", stdout);

    cin >> n >> m;

    for (int i = 0; i < m; i++){
        cin >> a >> b;
        g[a-1].push_back(b-1);
        h[b-1].push_back(a-1);
    }

    topological_sort();

    for (int i = 0; i < n; i++)
        for (int j = 0; j < g[ans[i]].size(); j++){
            q[i].push_back(ans[g[ans[i]][j]]);
            r[ans[g[ans[i]][j]]].push_back(i);
        }
    /*
    for (int i = 0; i < n; i++){
        cout << i << ": ";
        for (int j = 0; j < q[i].size(); j++)
            cout << q[i][j] << " ";
        cout << endl;
    }
    */


    cout << ways(n-1);

}
