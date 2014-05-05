#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, cur, a, b;
char eq;

int main(){

    freopen ("nextpartition.in", "r", stdin);
    freopen ("nextpartition.out", "w", stdout);

    cin >> n;
    int part[n+1], sum[n+1];
    part[0] = 0;
    cin >> eq;
    cin >> part[1];
    int total = 0;
    total += part[1];
    sum[0] = 0;
    sum[1] = 0;
    int k = 1;
    while (total < n){
        k++;
        cin >> eq;
        cin >> part[k];
        total += part[k];
    }

    for (int j = k+1; j <= n; j++)
        part[j] = 0;

    for (int j = 2; j <= n; j++)
        sum[j] = sum[j-1] + part[j-1];

    if (part[1] == n){
        cout << "No solution";
        return 0;
    }

    for (int i = n; i >= 1; i--){
            if ( (part[i] > 0 && sum[i] + part[i] < n) || (i == 0) ){
                cur = part[i] + 1;
                a = (n - sum[i]) / cur;
                b = (n - sum[i]) % cur;
                for (int j = i; j < i+a-1; j++){
                    part[j] = cur;
                    sum[j] = sum[j-1] + part[j-1];
                }
                part[i+a-1] = cur + b;
                sum[i+a-1] = n - part[i+a-1];
                for (int j = i+a; j <= n; j++){
                    part[j] = 0;
                    sum[j] = n;
                }
                break;
            }
        }
        cout << n << "=" << part[1];
        for (int i = 2; i <= n; i++){
            if (part[i] > 0)
                cout << "+" << part[i];
            else
                break;
        }

}
