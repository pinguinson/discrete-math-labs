#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, k;
string vr_in, vr_cur, vr_prev, vr_next;
char buff;
bool first, last;


int main() {

    freopen ("nextvector.in", "r", stdin);
    freopen ("nextvector.out", "w", stdout);

    cin >> vr_in;

    first = true;
    last = true;

    for (int i = 0; i < vr_in.length(); i++)
        if (vr_in[i] == '1')
            first = false;

    for (int i = 0; i < vr_in.length(); i++)
        if (vr_in[i] == '0')
            last = false;

    if (first){
        printf("-\n");
        vr_in[vr_in.length()-1] = '1';
        printf("%s", vr_in.c_str());
    }

    if (last){
        vr_in[vr_in.length()-1] = '0';
        printf("%s\n", vr_in.c_str());
        printf("-");
    }

    if (!first && !last){

    buff = '1';
    vr_cur = vr_in;
    k = vr_in.length() - 1;
    while (buff != '0'){
        if (vr_cur[k] == '0')
            vr_cur[k] = '1';
        else{
            vr_cur[k] = '0';
            buff = '0';
        }
        k--;
    }

        printf("%s\n", vr_cur.c_str());

    buff = '1';
    vr_cur = vr_in;
    k = vr_in.length() - 1;
    while (buff != '0'){
        if (vr_cur[k] == '0'){
            vr_cur[k] = '1';
            buff = '0';
        }
        else
            vr_cur[k] = '0';
        k--;
    }

    printf("%s", vr_cur.c_str());
    }
}
