#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, brackets_open, brackets_close;
string bra;
bool not_last;

int main() {
    
    freopen("nextbrackets.in", "r", stdin);
    freopen("nextbrackets.out", "w", stdout);
    
    cin >> bra;
    
    not_last = true;
    
    brackets_close = 0;
    brackets_open = 0;
    
    for (int i = bra.length()-1; i >= 0; i--){
        if (bra[i] == '('){
            brackets_open++;
            if (brackets_close > brackets_open)
                break;
        }
        else
            brackets_close++;
    }
    
    bra.resize(bra.length()-brackets_close-brackets_open);
    
    if (bra.length() == 0)
        cout << "-";
    else{
        bra += ')';
        for (int i = 0; i < brackets_open; i++)
            bra += '(';
        for (int i = 1; i < brackets_close; i++)
            bra += ')';
        cout << bra;
    }
}