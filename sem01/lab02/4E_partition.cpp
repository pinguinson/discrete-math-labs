#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, cur, a, b;

int main(){

    freopen ("partition.in", "r", stdin);
    freopen ("partition.out", "w", stdout);

    cin >> n;

    int part[n+1], sum[n+1];
    part[0] = 0;
    sum[0] = 0;
    for (int i = 1; i <= n; i++){
        part[i] = 1;
        sum[i] = i-1;
    }

    cout << part[1];
        for (int i = 2; i <= n; i++)
            cout << "+" << part[i];

    while (part[1] < n){

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
        cout << endl << part[1];
        for (int i = 2; i <= n; i++){
            if (part[i] > 0)
                cout << "+" << part[i];
            else
                break;
        }
    }
}
