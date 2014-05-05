#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, k, ans;
bool last;


int main(){

    freopen ("nextchoose.in", "r", stdin);
    freopen ("nextchoose.out", "w", stdout);

    cin >> n >> k;
    int choose[k+2];

    for (int i = 1; i <= k; i++)
        cin >> choose[i];

    bool last = true;
    for (int i = k; i > 0; i--)
        if (choose[i] != n - k + i){
            last = false;
            choose[i]++;
            for (int j = i+1; j <= k; j++)
                choose[j] = choose[j-1] + 1;
            break;
        }

    if (last){
        cout << "-1";
        return 0;
    }
    else
        for (int i = 1; i <= k; i++)
            cout << choose[i] << " ";


}
