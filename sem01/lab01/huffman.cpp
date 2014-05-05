#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>

using namespace std;

int n;
long long freq[1001], ans;

int compare (const void* x, const void* y){

   long long a = *(long long*) x;
   long long b = *(long long*) y;

   if (a > b)   return -1;
   if (a == b)  return 0;
   if (a < b)   return 1;
}


int main(){

    freopen("huffman.in", "r", stdin);
    freopen("huffman.out", "w", stdout);

    cin >> n;

    for (int i = 0; i < n; i++)
        cin >> freq[i];

    for (int i = n - 1; i > 0; i--){
        qsort (freq, n, sizeof(long long), compare);
        freq[i-1] += freq[i];
        ans += freq[i-1];
    }

    cout << ans;

}
