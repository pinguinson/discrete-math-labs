#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>

using namespace std;
string s, ans;
int d[2002][2002];
int n;

int maxPalindrome(int i, int j){
    if (d[i][j] == -1){
        if (s[i] == s[j])
            d[i][j] = maxPalindrome(i+1, j-1) + 2;
        else
            d[i][j] = max(maxPalindrome(i+1, j), maxPalindrome(i, j-1));
    }
    return d[i][j];
}

void getPalindrome(int i, int j, int l, int r){
    while (i <= j){
        if (i == j && d[i][j] == 1){
            ans[l] = s[i];
            l++;
            i++;
        }
        else{
            if (s[i] == s[j]){
                ans[l] = s[i];
                l++;
                i++;
                ans[r] = s[j];
                r--;
                j--;
            }else{
                if (d[i+1][j] > d[i][j-1])
                    i++;
                else
                    j--;
            }
        }
    }
}

int main() {
    
    freopen("palindrome.in", "r", stdin);
    freopen("palindrome.out", "w", stdout);
    
    cin >> s;
    n = s.length();
    
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            if (i == j)
                d[i][j] = 1;
            if (i > j)
                d[i][j] = 0;
            if (i < j)
                d[i][j] = -1;
        }
    }
    
    cout << maxPalindrome(0, n-1) << endl;
    ans.resize(maxPalindrome(0, n-1) + 1);
    getPalindrome(0, n, 0, d[0][n-1]);
    for (int i = ans.length()/2 + 1; i < ans.length(); i++)
        ans[i-1] = ans[i];
    ans.resize(ans.length() - 1);
    cout << ans;
    
    
}