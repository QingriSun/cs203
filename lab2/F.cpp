#include <iostream>
#include <stdlib.h>

using namespace std;

void Msort(int (* h_s), int (* index), int (* sorted), int (* index_s), int L, int R);
void merge(int (* h_s), int (* index), int (* sorted), int (* index_s), int L1, int R1, int L2, int R2);
int pow_2(int p);

int main(){
    // speed up io
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int n, p, q;
    int pos = 0; // the number of positive h - s
    int total_s = 0;
    cin >> n >> p >> q;

    int * s = (int *) malloc(sizeof(int) * n);
    int * h = (int *) malloc(sizeof(int) * n);
    int * h_s = (int *) malloc(sizeof(int) * n);
    int * sorted = (int *) malloc(sizeof(int) * n);
    int * index = (int *) malloc(sizeof(int) * n);
    int * index_s = (int *) malloc(sizeof(int) * n);
    for (int i = 0; i < n; i++){
        cin >> h[i] >> s[i];
        h_s[i] = h[i] - s[i];
        index[i] = i;

        if (h_s[i] > 0) pos++;

        total_s += s[i];
    }

    Msort(h_s, index, sorted, index_s, 0, n -1);

    // find the plant to use q potion

    int two_p = pow_2(p);
    int max; 
    if (h_s[0] > 0 && q > 0){
        max = (two_p - 1) * h[index[0]];
        total_s += h_s[0];
    } 
    else{
        cout << total_s << endl;
        return 0;
    }

    int increase;
    if (pos < q){

        for (int i = 1; i < pos; i++){
            total_s += h_s[i];
            increase = (two_p - 1) * h[index[i]];
            if (increase > max){
                max = increase;
            }
        }
        for (int i = pos; i < n; i++){
            increase = two_p * h[index[i]] - s[index[i]];
            if (increase > max){
                max = increase;
            }
        }

        total_s += max;
    }else{
        for (int i = 1; i < q; i++){
            total_s += h_s[i];
            increase = (two_p - 1) * h[index[i]];
            if (increase > max){
                max = increase;
            }
        }
        for (int i = q; i < n; i++){
            increase = two_p * h[index[i]] - s[index[i]] - h_s[q - 1];
            if (increase > max){
                max = increase;
            }
        }

        total_s += max;
    }

    cout << total_s << endl;
}

void Msort(int (* h_s), int (* index), int (* sorted), int (* index_s), int L, int R){
    if (L == R){
        sorted[L] = h_s[L];
        return;
    }
    int mid = (L + R) / 2;
    Msort(h_s, index, sorted, index_s, L, mid);
    Msort(h_s, index, sorted, index_s, mid + 1, R);
    merge(h_s, index, sorted, index_s, L, mid, mid + 1, R);
}

void merge(int (* h_s), int (* index), int (* sorted), int (* index_s), int L1, int R1, int L2, int R2){
    int begin_i = L1;
    int sorted_i = L1;
    while (L1 <= R1 && L2 <= R2){
        if (h_s[L1] >= h_s[L2]){
            sorted[sorted_i] = h_s[L1];
            index_s[sorted_i++] = index[L1++];
        }
        else{
            sorted[sorted_i] = h_s[L2];
            index_s[sorted_i++] = index[L2++];
        }
    }

    for (int i = L1; i <= R1; i++){
        sorted[sorted_i] = h_s[i];
        index_s[sorted_i++] = index[i];
    }
    for (int i = L2; i <= R2; i++){
        sorted[sorted_i] = h_s[i];
        index_s[sorted_i++] = index[i];
    }

    for (int i = begin_i; i <= R2; i++){
        h_s[i] = sorted[i];
        index[i] = index_s[i];
    }
}

int pow_2(int n){
    return 1 << n;
}