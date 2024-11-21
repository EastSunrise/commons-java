package cn.kingen.commons.text.similarity;

import org.apache.commons.text.similarity.SimilarityScore;

/**
 * A similarity algorithm indicating the length of the longest common subsequence between two strings.
 *
 * @author Kingen
 **/
public class LongestCommonSubstring implements SimilarityScore<Integer> {

    private static final LongestCommonSubstring INSTANCE = new LongestCommonSubstring();

    public static LongestCommonSubstring getInstance() {
        return INSTANCE;
    }

    @Override
    public Integer apply(CharSequence left, CharSequence right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Inputs must not be null");
        }

        int m = left.length();
        int n = right.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (left.charAt(i - 1) == right.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
