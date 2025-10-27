#include <iostream>
#include <string.h>

using namespace std;

int main(){
    int arr_len;
    cin >> arr_len;
    int arr[arr_len];
    for (int i = 0; i < arr_len; i++){
        cin >> arr[i];
    }
    int test_num;
    cin >> test_num;
    bool in_arr[test_num];
    memset(in_arr, 0, sizeof(in_arr));
    int test_case;
    for (int i = 0; i < test_num; i++){
        cin >> test_case;
        for (int j = 0; j < arr_len; j++){
            if (test_case == arr[j]){
                in_arr[i] = 1;
                break;
            } 
        }
    }

    for (int i = 0; i < test_num; i++){
        if (in_arr[i] == 1) cout << "yes" << endl;
        else cout << "no" << endl;
    }
}