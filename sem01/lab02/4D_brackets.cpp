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


    freopen("brackets.in", "r", stdin);
    freopen("brackets.out", "w", stdout);

    cin >> n;

    for (int i = 0; i < n; i++)
        bra += '(';
    for (int i = 0; i < n; i++)
        bra += ')';

    not_last = true;
    while (not_last){

        brackets_close = 0;
        brackets_open = 0;

        cout << bra << endl;

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
            not_last = false;
        else{
            bra += ')';
            for (int i = 0; i < brackets_open; i++)
                bra += '(';
            for (int i = 1; i < brackets_close; i++)
                bra += ')';
        }

    }
}
