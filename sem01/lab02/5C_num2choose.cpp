#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, k, m, ans;

int main(){

    freopen ("num2choose.in", "r", stdin);
    freopen ("num2choose.out", "w", stdout);

    cin >> n >> k >> m;
    int pre, cur_num = 0;
    int choose[k+1];
    int c[n+1][n+1];

    for (int i = 0; i <= n; i++)
        for (int j = 0; j <= n; j++)
            c[i][j] = 0;

    for (int i = 1; i <= n; i++){
        c[i][1] = i;
        c[i][i] = 1;
    }

    for (int i = 3; i <= n; i++)
        for (int j = 2; j < i; j++)
            c[i][j] = c[i-1][j-1] + c[i-1][j];

    choose[0] = 0;
    for (int j = 1; j <= k; j++)
        choose[j] = j;

    for (int i = 1; i <= k; i++){


        ans = 0;
        for (int j = 1; j < k; j++)
            if (choose[j] > choose[j-1] + 1)
                for (int l = choose[j-1] + 1; l < choose[j]; l++)
                    ans += c[n-l][k-j];

        if (choose[k] > choose[k-1] + 1)
            ans += choose[k] - choose[k-1] - 1;
        cur_num = ans;

        while (cur_num < m){
            choose[i]++;
            if (choose[i] >= choose[i+1]){
                for (int j = i+1; j <= k; j++)
                    choose[j] = choose[j-1] + 1;
            }
            pre = cur_num;

            ans = 0;
            for (int j = 1; j < k; j++)
                if (choose[j] > choose[j-1] + 1)
                    for (int l = choose[j-1] + 1; l < choose[j]; l++)
                        ans += c[n-l][k-j];

            if (choose[k] > choose[k-1] + 1)
                ans += choose[k] - choose[k-1] - 1;
            cur_num = ans;

        }
        if (cur_num > m){
            choose[i]--;
            cur_num = pre;
        }

        if (cur_num == m){
            for (int l = 1; l <= k; l++)
                cout << choose[l] << " ";
            break;
        }
    }

}








