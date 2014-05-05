#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
 
using namespace std;
int n, m;
int w[1001];
int p[10001];
long long ans[1001][10001];
vector<int> taken;
 
void findAns(int k, int s) {
    if (ans[k][s] == 0) {
        return;
    }
    if (ans[k-1][s] == ans[k][s])
        findAns(k-1, s);
    else {
        findAns(k-1, s-w[k]);
        taken.push_back(k);
    }
}
 
int main() {
     
    freopen("knapsack.in", "r", stdin);
    freopen("knapsack.out", "w", stdout);
     
    cin >> n >> m;
     
    for (int i = 1; i <= n; i++)
        cin >> w[i];
    for (int i = 1; i <= n; i++)
        cin >> p[i];
    for (int i = 0; i <= n; i++)
        ans[i][0] = 0;
    for (int i = 0; i <= m; i++)
        ans[0][i] = 0;
     
    for (int k = 1; k <= n; k++){
        for (int s = 0; s <= m; s++){
            if (w[k] <= s)
                ans[k][s] = max(ans[k-1][s], ans[k-1][s - w[k]] + p[k]);
            else
                ans[k][s] = ans[k-1][s];
        }
    }
    findAns(n, m);
     
    cout << taken.size() << endl;
    for (int i = 0; i < taken.size(); i++)
        cout << taken[i] << " ";
}