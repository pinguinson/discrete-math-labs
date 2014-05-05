//absmarkchain.cpp

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

float probability[200000];
float c, mul, prob;

int input[200000][2];
int total_abs, total_nonabs, count_q, count_r, m, total_conditions, a, b;

float P[404][404];
float Q[404][404];
float E[404][404];
float N[404][404];
float G[404][404];
float R[404][404];

int position[404];
bool absorbing[404];

void getInput(){

    cin >> total_conditions >> m;

    for (int i = 0; i < m; i++){
        cin >> a >> b >> c;
        a--;
        b--;
        input[i][0] = a;
        input[i][1] = b;
        probability[i] = c;
    }

    return;
}


void countAbsorbing(){

    total_abs = 0;
    for (int i = 0; i < m; i++){
        if (input[i][0] == input[i][1] && probability[i] == 1.0){
            absorbing[input[i][0]] = true;
            total_abs++;
        }
    }
    total_nonabs = total_conditions - total_abs;

    return;
}

void getQR(){

    count_q = 0;
    count_r = 0;
    for (int i = 0; i < total_conditions; i++){
        if (absorbing[i]){
            position[i] = count_r;
            count_r++;
        }else{
            position[i] = count_q;
            count_q++;
        }
    }

    for (int i = 0; i < m; i++){
        if (absorbing[input[i][1]]){
            if (!absorbing[input[i][0]]){
                R[position[input[i][0]]][position[input[i][1]]] = probability[i];
            }
        }else{
            Q[position[input[i][0]]][position[input[i][1]]] = probability[i];
        }
    }

    return;
}

void getEN(){

    for (int i = 0; i < total_nonabs; i++){
        N[i][i] = 1.0;
        E[i][i] = 1.0;
        for (int j = 0; j < total_nonabs; j++)
            E[i][j] -= Q[i][j];
    }

    return;
}

void reductionEN(){

    for (int i = 0; i < total_nonabs; i++){
        if (E[i][i] != 1.0){
            mul = E[i][i];
            for (int j = 0; j < total_nonabs; j++){
                E[i][j] /= mul;
                N[i][j] /= mul;
            }
        }
        for (int row = 0; row < total_nonabs; row++){
            if (i != row){
                mul = E[row][i];
                for (int j = 0; j < total_nonabs; j++){
                    E[row][j] -= mul * E[i][j];
                    N[row][j] -= mul * N[i][j];
                }
            }
        }
    }

    return;
}

void getG(){

    for (int i = 0; i < total_nonabs; i++){
        for (int j = 0; j < total_abs; j++){
            G[i][j] = 0.0;
            for (int k = 0; k < total_nonabs; k++)
                G[i][j] += N[i][k] * R[k][j];
        }
    }

    return;
}

void printAnswer(){

    for (int i = 0; i < total_conditions; i++){
        prob = 0.0;
        if (absorbing[i]){
            for (int j = 0; j < total_nonabs; j++)
                prob += G[j][position[i]];
            prob += 1.0;
            prob /= float(total_conditions);
        }
        printf("%.6f\n", prob);
    }

    return;
}




int main() {

    freopen("absmarkchain.in", "r", stdin);
    freopen("absmarkchain.out", "w", stdout);

    getInput();
    countAbsorbing();
    getQR();
    getEN();
    reductionEN();
    getG();
    printAnswer();

}


