#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>

using namespace std;

int n, ans, l, k;
char buff;
string curr;
bool fl;

int main() {
	
    freopen("vectors.in", "r", stdin);
    freopen("vectors.out", "w", stdout);
    
    cin >> n;
    l = pow(2,n);
    string bins[66000], ideal[2600];
    int shift[n];
    
    for (int i = 0; i < n; i++)
        shift[i] = l / pow(2,i+1);
    
    for (int j = 0; j < n; j++)
        for (int i = 0; i < l; i++){
            if (((i+1) / shift[j]) % 2 == 0)
                bins[i] += "0";
            if (((i+1) / shift[j]) % 2 == 1)
                bins[i] += "1";
        }
    
    
    for (int i = 0; i < l; i++){
        fl = 1;
        for (int j = 1; j < n; j++)
            if (bins[i][j] == '1' && bins[i][j-1] == '1'){
                fl = 0;
                break;
            }
        if (fl == 1){
            ans++;
            ideal[k] = bins[i];
            k++;
        }
    }
    
    cout << k;
    cout << endl << ideal[k-1];
    for (int i = 0; i < k-1; i++)
        cout << endl << ideal[i];
}