import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // 植物数量
        int p = sc.nextInt();  // F_h（翻倍肥料）使用上限
        int q = sc.nextInt();  // F_s（力量肥料）使用上限

        long[] h = new long[n];
        long[] s = new long[n];

        for (int i = 0; i < n; i++) h[i] = sc.nextLong();
        for (int i = 0; i < n; i++) s[i] = sc.nextLong();

        long baseSum = 0;  // 原始力量和
        for (long val : s) baseSum += val;

        long pow2p = 1L << p; // 2^p（注意溢出问题：p<=60比较安全）

        // gainDouble[i] = 对第 i 株用全部 p 次翻倍+1 次力量设定 的收益
        long[] gainDouble = new long[n];
        // gainSingle[i] = 仅用力量设定 的收益
        long[] gainSingle = new long[n];

        for (int i = 0; i < n; i++) {
            gainDouble[i] = h[i] * pow2p - s[i];
            gainSingle[i] = h[i] - s[i];
        }

        // 找出最值得翻倍+覆盖的那株
        int bestIdx = 0;
        for (int i = 1; i < n; i++) {
            if (gainDouble[i] > gainDouble[bestIdx]) bestIdx = i;
        }

        // 防止重复计数（该株已包含在 gainDouble 中）
        gainSingle[bestIdx] = 0;

        // 将剩下的 (h - s) 从大到小排序，取前 (q-1) 个
        Arrays.sort(gainSingle);
        long extra = 0;
        for (int i = n - 1; i >= n - (q - 1) && i >= 0; i--) {
            if (gainSingle[i] > 0)
                extra += gainSingle[i];
        }

        // 最终答案
        long ans = baseSum + gainDouble[bestIdx] + extra;
        System.out.println(ans);
    }
}
