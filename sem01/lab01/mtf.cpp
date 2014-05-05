#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>

using namespace std;

string instr;
int pos[30], cur;

int main() {
    
    freopen("mtf.in", "r", stdin);
    freopen("mtf.out", "w", stdout);
    
    cin >> instr;
    
    
    for (int i = 1; i <= 26; i++)
        pos[i] = i;
    
    for (int i = 0; i < instr.length(); i++){
        cur = instr[i] - 96;
        cout << pos[cur] << " ";
        for (int j = 1; j <= 26; j++)
            if (pos[j] < pos[cur] && pos[j] != 0)
                pos[j]++;
        pos[cur] = 1;
    }
}
