输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
public class Solution {
    private ArrayList<ArrayList<Integer>> res=new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        dfs(root,target,new ArrayList<>());
        return res;
    }
    private void dfs(TreeNode root,int target,ArrayList<Integer> path){
        if(root==null) return;
        target-=root.val;
        path.add(root.val);
        if(target==0&&root.left==null&&root.right==null){
            res.add(new ArrayList(path));
            path.remove(path.size()-1);
            target+=root.val;
            return;
        }
        dfs(root.left,target,path);
        dfs(root.right,target,path);
        path.remove(path.size()-1);
        target+=root.val;
    }
}

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。


public class Solution {
    private TreeNode pre;
    public TreeNode Convert(TreeNode pRootOfTree) {
        TreeNode head=pRootOfTree;
        if(head==null) return null;
        while(head.left!=null){
            head=head.left;
        }
        transform(pRootOfTree);
        return head;
    }
    private void transform(TreeNode root){
        if(root==null) return ;
        transform(root.left);
        root.left=pre;
        if(pre!=null)
        pre.right=root;
        pre=root;
        transform(root.right);
    }
}


输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。

import java.util.*;
public class Solution {
    private ArrayList<String> res=new ArrayList<>();
    public ArrayList<String> Permutation(String str) {
       char[] chars=str.toCharArray();
        if(chars.length==0) return res;
        Arrays.sort(chars);
        back(chars,chars.length,0,"",new boolean[chars.length]);
        return res;
    }
    private void back(char[] chars,int n,int num,String s,boolean[] isVisited){
        if(num==n){
            res.add(s);
            return;
        }
        for(int i=0;i<n;i++){
            if(isVisited[i]) continue;
            if(i>0&&chars[i]==chars[i-1]&&!isVisited[i-1]){
                continue;
            }
            s+=chars[i];
            isVisited[i]=true;
            back(chars,n,num+1,s,isVisited);
            isVisited[i]=false;
            s=s.substring(0,num);
        }
    }
}


数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        int res=0;
        int count=0;
        for(int num:array){
            if(count==0){
                res=num;
            }
            if(num==res){
                count++;
            }else{
                count--;
            }

        }
        count=0;
        for(int num:array){
            if(num==res) count++;
        }
        return count>array.length/2?res:0;
    }
}



