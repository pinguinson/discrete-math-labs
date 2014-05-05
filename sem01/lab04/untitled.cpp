#include <fstream>
#include <iostream>
#include <vector>
#include <iomanip>

int main()
{
    std::ifstream ifs ("markchain.in");
    std::ofstream ofs ("markchain.out");
    int n;
    ifs >> n;
    std::vector< std::vector<float> > matrix (n, std::vector<float> (n, 0)), lala, lala2;
    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < n; ++j)
        {
            ifs >> matrix[i][j];
        }
    }
    lala = matrix;
    lala2 = lala;
    for (int it = 0; it < 100; ++it)
    {
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                float temp = 0;
                for (int k = 0; k < n; ++k)
                {
                    temp += lala[i][k] * matrix[k][j];
                }
                lala2[i][j] = temp;
            }
        }
        lala = lala2;
    }
    for (int i = 0; i < n; ++i)
    {
        ofs << std::setprecision(4) << lala2[0][i] << std::endl;
    }
}
