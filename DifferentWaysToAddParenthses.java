//Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> num=new ArrayList<>();
        List<Character> op=new ArrayList<>();
        int number=0;
        for(int i=0;i<input.length();i++){
            char c=input.charAt(i);
            if(c==' ') continue;
            if(c>='0'&&c<='9'){
                number=number*10+(c-'0');
            }
            else{
                num.add(number);
                number=0;
                op.add(c);
            }
        }
        num.add(number);
        List<Integer>[][] dp=new List[num.size()][num.size()];
        return divide(num,op,0,num.size()-1,dp);
    }
    public List<Integer> divide(List<Integer> num, List<Character> op,int start, int end, List<Integer>[][] dp){
        if(dp[start][end]!=null) return dp[start][end];
        List<Integer> result=new ArrayList<>();
        if(start==end){
            result.add(num.get(start));
        }
        else{
            for(int i=start;i<end;i++){
                List<Integer> left=divide(num,op,start,i,dp);
                List<Integer> right=divide(num,op,i+1,end,dp);
                char operator=op.get(i);
                for(int l:left){
                    for(int r:right){
                        result.add(compute(l,r,operator));
                    }
                }
            }
        }
        dp[start][end]=result;
        return result;
    }
    public int compute(int a, int b, char o){
        switch(o){
            case '+':
                return a+b;
            case '-':
                return a-b;
            default:
                return a*b;
        }
    }
}


/*
import java.util.*;

public class Solution {
   public List<Integer> diffWaysToCompute(String input) {
        input.replaceAll("\\s+","");
        Queue<String> list=new LinkedList<>();
        String temp="";
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='*'||input.charAt(i)=='-'||input.charAt(i)=='+'){
                list.add(temp);
                list.add(input.charAt(i)+"");
                temp="";
            }
            else{
                temp+=input.charAt(i);
            }
        }
        list.add(temp);
        List<Integer> result=sub1(list);
        return result;
    }
  
    
    public List<Integer> sub1(Queue<String> list){
    	List<Integer> result=new ArrayList<>();
        if(list.size()==1) {
            List<Integer> temp=new ArrayList<>();
            temp.add(Integer.parseInt(list.poll()));
            return temp;
        }
        int head=Integer.parseInt(list.poll());
        String op=list.poll();
        Queue<String> cons=new LinkedList<>(list);
        if(op.equals("-")) {for(int k:sub1(list)){result.add(head-k);}}
        else if(op.equals("+")) for(int k:sub1(list)){result.add(head+k);}
        else for(int k:sub1(list)){result.add(head*k);}
        int l=cons.size();
        if(l<=2) return result;
        for(int i=0;i<l-1;i=i+2){
        	Queue<String> cbk=new LinkedList<>(cons);
        	Queue<String> temp=new LinkedList<>();
        	List<Integer> headlist=new ArrayList<>();
        	for(int j=0;j<=i;j++){
        		String next=cbk.poll();
        		temp.add(next);
        	}
        	 if(op.equals("-")) {for(int k:sub1(temp)){headlist.add(head-k);}}
             else if(op.equals("+")) for(int k:sub1(temp)){headlist.add(head+k);}
             else for(int k:sub1(temp)){headlist.add(head*k);}
        	 for(int h:headlist){
        		 Queue<String> cbk1=new LinkedList<>();
        	        cbk1.add(h+"");
        	        cbk1.addAll(cbk);
        	        result.addAll(sub1(cbk1));
        	 }
        }
        return result;
    }
   
}
*/
