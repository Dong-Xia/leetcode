package xia.ncut;

/**
 * <b>System：</b>ncc<br/>
 * <b>Title：</b>LongestPalindrome<br/>
 * <b>Description：</b>最长回文字符串<br/>
 * <b>@author： </b>xiadong<br/>
 * <b>@date：</b>2019/8/29 21:45<br/>
 */
public class LongestPalindrome05 {
    public String longestPalindrome(String s) {
        Boolean[][] dp = new Boolean[s.length()][s.length()];
        if(s.length() <= 0){
            return null;
        }
        if(s.length() == 1){
            return s;
        }
        for(int i =0;i<s.length();i++){
            dp[i][i]=true;
        }
        // 记录最长的区间
        int start =0;
        int end = 0;
        for(int r = 1;r<s.length();r++){
            for(int l=0;l<r;l++){
                if((s.charAt(l) == s.charAt(r)) && ((l == r-1) || dp[l+1][r-1])){
                    dp[l][r] = true;
                    if(r-l > end - start){
                        start = l;
                        end = r;
                    }
                }else{
                    dp[l][r] = false;
                }
            }
        }

        return s.substring(start,end+1);
    }

    public static void main(String[] args) {
        new LongestPalindrome05().longestPalindrome("cbbd");
    }
}
