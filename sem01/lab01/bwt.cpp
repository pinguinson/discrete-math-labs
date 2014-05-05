#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>

using namespace std;

string instr, outstr, str[10000];

int main() {

    freopen("bwt.in", "r", stdin);
    freopen("bwt.out", "w", stdout);

    cin >> instr;

    for (int i = 0; i < instr.length(); i++){
        str[i].append(instr, instr.length()-1, 1);
        str[i].append(instr, 0, instr.length()-1);
        instr = str[i];
    }

    for (int i = 0; i < instr.length(); i++)
        for (int j = 0; j < i; j++)
            if (str[j].compare(str[i]) > 0)
                swap (str[j], str[i]);

    for (int i = 0; i < instr.length(); i++)
        outstr += str[i][instr.length()-1];

    cout << outstr;

}