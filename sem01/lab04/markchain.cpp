//markchain.cpp

#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>
#include <cstring>
#include <utility>

using namespace std;

int n;

float m[104][104];
float b[104][104];
float a[104][104];

void copyMatrixMB(){
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            b[i][j] = m[i][j];
}

void copyMatrixMA(){
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            a[i][j] = m[i][j];
}
void clearM(){
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            m[i][j] = 0.0;
}
int main(){

    freopen("markchain.in", "r", stdin);
    freopen("markchain.out", "w", stdout);

    cin >> n;

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> m[i][j];

    copyMatrixMA();
    for (int u = 0; u < 100; u++){
        copyMatrixMB();
        clearM();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    m[i][j] += a[i][k]*b[k][j];
    }

    for (int i = 0; i < n; i++)
        printf("%.5f\n", m[0][i]);
}


