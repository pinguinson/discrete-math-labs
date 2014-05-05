#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>

#define inf 1000000007

using namespace std;

int n, cur;
vector<int> a;

int main(){

    freopen("lis.in", "r", stdin);
    freopen("lis.out", "w", stdout);

	cin >> n;

	for (int i = 0; i < n; i++){
		cin >> cur;
		a.push_back(cur);
	}

    vector<int> prev(n);
    vector<int> d(n);
    for (int i = 0; i < n; i++){
        d[i] = 1;
        prev[i] = -1;
        for (int j = 0; j < i; j++){
            if (a[j] < a[i] && d[j] + 1 > d[i]){
                d[i] = d[j] + 1;
                prev[i] = j;
            }
        }
    }
    int length = d[0];
    int pos = 0;
    for (int i = 0; i < n; i++){
        if (d[i] > length){
            length = d[i];
            pos = i;
        }
    }
    vector<int> answer;
    while (pos != -1){
        answer.push_back(a[pos]);
        pos = prev[pos];
    }

    cout << answer.size() << endl;
    for (int i = answer.size() - 1; i >= 0; i--)
    	cout << answer[i] << " ";
}
