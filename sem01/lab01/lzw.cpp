#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>

using namespace std;

string instr, dict[1000], temp, cur;
bool found;

int main() {

    freopen("lzw.in", "r", stdin);
    freopen("lzw.out", "w", stdout);

    int n = 25;

    for (int i = 0; i <= n; i++)
        dict[i] += char(i + int('a'));

    cin >> instr;

    for (int i = 0; i < instr.length(); i++){
        found = false;
        cur.assign(instr,i,1);
        temp.append(cur);

        for (int j = 0; j <= n; j++)
            if (dict[j] == temp){
                found = true;
                break;
            }

        if (!found){
            n++;
            dict[n] = temp;
            temp.assign(temp,0,temp.length()-1);
            for (int j = 0; j <= n; j++)
                if (dict[j] == temp){
                    cout << j << " ";
                    break;
                }
            temp = cur;
        }
    }

    if (!temp.empty())
        for (int j = 0; j <= n; j++)
            if (dict[j] == temp){
                cout << j << " ";
                break;
            }
}
