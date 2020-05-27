输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1==null) return list2;
        if(list2==null) return list1;
        if(list1.val<list2.val){
            list1.next=Merge(list1.next,list2);
            return list1;
        }else{
            list2.next=Merge(list1,list2.next);
            return list2;
        }
    }
}

输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

public class Solution {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root2==null||root1==null) return false;
        if(isSame(root1,root2)) return true;
        return HasSubtree(root1.left,root2)||HasSubtree(root1.right,root2);
    }
    private boolean isSame(TreeNode root1,TreeNode root2){
        if(root1==null&&root2==null) return true;
        if(root2==null) return true;
        if(root1==null) return false;
        if(root1.val!=root2.val) return false;
        return isSame(root1.left,root2.left)&&isSame(root1.right,root2.right);
    }
}

操作给定的二叉树，将其变换为源二叉树的镜像。

public class Solution {
    public void Mirror(TreeNode root) {
        if(root==null) return;
        TreeNode pre=root.left;
        root.left=root.right;
        root.right=pre;
        Mirror(root.left);
        Mirror(root.right);
    }
   
}


输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵：
 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list=new ArrayList<>();
        int up=0;
        int down=matrix.length-1;
        if(down==-1) return list;
        int left=0;
        int right=matrix[0].length-1;
        while(true){
            for(int i=left;i<=right;i++){
                list.add(matrix[up][i]);
            }
            if(++up>down) break;
            for(int i=up;i<=down;i++){
                list.add(matrix[i][right]);
            }
            if(--right<left) break;
            for(int i=right;i>=left;i--){
                list.add(matrix[down][i]);
            }
            if(--down<up) break;
            for(int i=down;i>=up;i--){
                list.add(matrix[i][left]);
            }
            if(++left>right) break;
        }
        return list;
    }
}

定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
public class Solution {
    private static class Node{
        private int val;
        private int min;
        public Node(int val,int min){
            this.val=val;
            this.min=min;
        }
    }
    
    private Stack<Node> stack=new Stack<>();
    
    public void push(int node) {
        Node cur=new Node(node,node);
        if(!stack.isEmpty()&&stack.peek().min<node){
            cur.min=stack.peek().min;
        }
        stack.push(cur);
    }
    
    public void pop() {
        if(!stack.isEmpty()){
            stack.pop();
        }
    }
    
    public int top() {
        if(!stack.isEmpty()){
            return stack.peek().val;
        }
        return -1;
    }
    
    public int min() {
        if(!stack.isEmpty()){
            return stack.peek().min;
        }
        return -1;
    }
}

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
（注意：这两个序列的长度是相等的）
import java.util.*;

public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
      Stack<Integer> stack=new Stack<>();
        int j=0;
        for(int i=0;i<pushA.length;i++){
            stack.push(pushA[i]);
            while(!stack.isEmpty()&&j<popA.length&&stack.peek()==popA[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty()&&j==popA.length;
    }
}


从上往下打印出二叉树的每个节点，同层节点从左至右打印。
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        ArrayList<Integer> res=new ArrayList<>();
        if(root==null) return res;
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur=queue.poll();
            res.add(cur.val);
            if(cur.left!=null) queue.offer(cur.left);
            if(cur.right!=null) queue.offer(cur.right);
        }
        return res;
    }
}

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。
假设输入的数组的任意两个数字都互不相同。
public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length==0) return false;
        return isValid(sequence,0,sequence.length-1);
    }
    private boolean isValid(int[] arr,int left,int right){
        if(left>=right) return true;
        int mid=right;
        for(int i=left;i<right;i++){
            if(arr[i]>arr[right]){
                mid=i;
                break;
            }
        }
        for(int i=mid;i<right;i++){
            if(arr[i]<arr[right]){
                return false;
            }
        }
        return isValid(arr,left,mid-1)&&isValid(arr,mid,right-1);
    }
}

