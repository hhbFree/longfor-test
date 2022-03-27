package 位运算;

class Solution {
    public String addBinary(String a, String b) {
        int ans=0;
       if(a.length()>b.length()){
           ans=a.length()-b.length();
           for(int i=0;i<ans;i++){
            b="0"+b;
           }
       }else{
           ans=b.length()-a.length();
           for(int i=0;i<ans;i++){
            a="0"+a;
           }
       }

        int n=a.length()-1;
        int carry=0;
        
        String result="";
        while(n>=0){
            //00 01 10 11
            int sum =Integer.parseInt(b.charAt(n)+"")+ Integer.parseInt(a.charAt(n)+"")+carry;
            if(sum>=2){
                result+=sum%2;
                carry=1;
            }else{
                result+=sum;
                 carry=0;
            }
            n--;
        }
        if(carry==1){
            result+="1";
        }
        n=result.length()-1;
        int s=0;

        System.out.print(result);
        return result;
    }
}