#include <iostream>
#include <stdlib.h>

using namespace std;

void merge(int * arr1, int * arr2, int * sorted, int len1, int len2);

int main(){
    int test_num;
    cin >> test_num;
    int ** result = new int * [test_num];
    int len[test_num * 2];
    int len_index = 0;
    for (int i = 0; i < test_num; i++){
        int len1, len2;
        cin >> len1 >> len2;
        len[len_index++] = len1;
        len[len_index++] = len2;
        int arr1[len1];
        int arr2[len2];
        for (int j = 0; j < len1; j++){
            cin >> arr1[j];
        }
        for (int j = 0; j < len2; j++){
            cin >> arr2[j];
        }

        result[i] = (int *) malloc(sizeof(int) * (len1 + len2));
        merge(arr1, arr2, result[i], len1, len2);
    }

    len_index = 0;
    for (int i = 0; i < test_num; i++){
        int first_i = len_index++;
        int second_i = len_index++;
        int new_arr_len = len[first_i] + len[second_i];
        for (int j = 0; j < new_arr_len; j++){
            cout << result[i][j] << ' ';
        }
        cout << endl;
    }
}

void merge(int * arr1, int * arr2, int * sorted, int len1, int len2){
    int L = 0;
    int R = 0;
    int sorted_i = 0;
    while (L < len1 && R < len2){
        if (arr1[L] > arr2[R]){
            sorted[sorted_i++] = arr2[R++];
        }
        else{
            sorted[sorted_i++] = arr1[L++];
        }
    }

    for (int i = L; i < len1; i++){
        sorted[sorted_i++] = arr1[i];
    }
    for (int i = R; i < len2; i++){
        sorted[sorted_i++] = arr2[i];
    }

}