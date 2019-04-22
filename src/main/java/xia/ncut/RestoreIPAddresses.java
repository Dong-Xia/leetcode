package xia.ncut;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIPAddresses {
    List<List<String>> res=new ArrayList<List<String>>();
    public List<String> restoreIpAddresses(String s) {
        if(s==null) return null;
        List<String> result=new ArrayList<String>();
        if(s.length()<4) return result;
        List<String> list=new ArrayList<String>();
        restoreIpAddresses(s, 0, list);
        for(List<String> l: res){
            boolean b=false;
            StringBuilder sb=new StringBuilder();
            for(String str: l){
                if(str.length()>1 && str.charAt(0)=='0'){
                    b=true;
                    continue;
                }
                sb.append(str);
                sb.append(".");
            }
            if(b) continue;
            sb.deleteCharAt(sb.length()-1);
            result.add(sb.toString());
        }
        return result;
    }
    int len=0;
    private void restoreIpAddresses(String s, int start, List<String> list){
        if(start>s.length()) return;
        if(start==s.length() && list.size()==4 && len==s.length()){
            res.add(new ArrayList<String>(list));
        }
        else{
            for(int i=start; i<s.length(); i++){
                for(int j=1; j<4; j++){
                    if(i+j>s.length()) return;
                    String st=s.substring(i, i+j);
                    int temp=Integer.parseInt(st);
                    if(temp>=0 && temp<=255){
                        list.add(st);
                        len+=st.length();
                        restoreIpAddresses(s, i+j, list);
                        list.remove(list.size()-1);
                        len-=st.length();
                    }
                    else return;
                }
            }
        }
    }
}

