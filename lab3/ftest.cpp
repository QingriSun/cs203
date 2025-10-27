#include <iostream>
#include <random>

using namespace std;

int main(){
    int test_num = 1000;
    cout << test_num << endl;
    random_device rd;
    mt19937 gen(rd());

    for (int i = 0; i < test_num; i++){
        int len = uniform_int_distribution(0, 100000)(gen);

        for (int j = 0; j < len; j++){
            cout << uniform_int_distribution(1, 100000) << ' ';
        }
        cout << endl;
    }
    

}



