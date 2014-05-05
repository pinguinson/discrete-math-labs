#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>

using namespace std;

int n;
long long k;

int main(){

    freopen ("brackets2num2.in", "r", stdin);
    freopen ("brackets2num2.out", "w", stdout);

    string s;
    cin >> s;
    n = 0;
    k = 0;
    for (int i = 0; i < s.length(); i++)
        if (s[i] == '(' || s[i] == '[')
            n++;

    long long d[2*n+1][2*n+1];

    for (int i = 0; i <= 2*n; i++)
        for (int j = 0; j <= 2*n; j++)
            d[i][j] = 0;

    d[0][0] = 1;
    for (int i = 0; i < 2*n; i++)
        for (int j = 0; j <= n; j++) {
            if (j+1 <= n)
                d[i+1][j+1] += d[i][j];
            if (j > 0)
                d[i+1][j-1] += d[i][j];
        }

    int depth = 0;
    int depth_round = 0;
    int depth_square = 0;
    vector<int> open_round;
    open_round.clear();
    vector<int> open_square;
    open_square.clear();
    int last_round = 0;
    int last_square = 0;

    for (int i = 0; i < 2*n; i++){
        if (s[i] == '('){
            depth++;
            depth_round++;
            open_round.push_back(i);
        }
        if (s[i] == ')'){
            k += d[2*n - i - 1][depth+1] << ((2*n-i-depth-2) / 2);
            depth--;
            depth_round--;
            open_round.pop_back();
        }
        if (s[i] == '['){
            k += d[2*n - i - 1][depth+1] << ((2*n-i-depth-2) / 2);
            if (open_round.size() > 0)
                last_round = open_round.back();
            else
                last_round = 0;
            if (open_square.size() > 0)
                last_square = open_square.back();
            else
                last_square = 0;
            if (last_round > last_square || depth_square == 0)
                k += d[2*n - i - 1][depth-1] << ((2*n-i-depth) / 2);
            depth++;
            depth_square++;
            open_square.push_back(i);
        }
        if (s[i] == ']'){
            k += d[2*n - i - 1][depth+1] << ((2*n-i-depth-2) / 2);
            k += d[2*n - i - 1][depth+1] << ((2*n-i-depth-2) / 2);
            if (open_round.size() > 0)
                last_round = open_round.back();
            else
                last_round = 0;
            if (open_square.size() > 0)
                last_square = open_square.back();
            else
                last_square = 0;
            if (last_round > last_square || depth_square == 0)
                k += d[2*n - i - 1][depth-1] << ((2*n-i-depth) / 2);
            depth--;
            depth_square--;
            open_square.pop_back();
        }
    }

    cout << k;
}
