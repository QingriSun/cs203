#include <iostream>
#include <random>

using namespace std;

int main(){
    random_device rd;
    mt19937 gen(rd());

    int test_num = 100;
    cout << test_num << endl;
    for (int j = 0; j < test_num; j++){
        int len = uniform_int_distribution<int>(1, 30000)(gen);
        cout << len << endl;
        for (int i = 0; i < len; i++){
            cout << uniform_int_distribution<int>(1, 30000)(gen) << ' ';
        }
        cout << endl;
    }

}