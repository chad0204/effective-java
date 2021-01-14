package com.pc.algorithm.btree;

import com.pc.algorithm.datastructure.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 106. 从中序与后序遍历序列构造二叉树
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 *
 * @author pengchao
 * @date 14:38 2021-01-14
 */
public class BuildTree {

    public static void main(String[] args) {

        int[] preOrder = new int[]{3,9,20,15,7};
        int[] inOrder = new int[]{9,3,15,20,7};
        int[] postOrder = new int[]{9,15,7,20,3};

        TreeNode root1 = buildTree(preOrder,0,preOrder.length-1,
                inOrder,0,inOrder.length-1);


        TreeNode root2 = buildTreePost(inOrder,0,inOrder.length-1,
                postOrder,0,postOrder.length-1);


        TreeNode.preTraversal(root1);
        TreeNode.inTraversal(root1);
        TreeNode.postTraversal(root1);


        System.out.println();



    }


    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder  = [9,3,15,20,7]
     *
     *
     * pre   root ----------------left-----------------  --------------right-------------
     * pre       [l_preStart]                     [l_preEnd]  [r_preStart]              [r_preEnd]
     *
     * in    ----------------left-------------   root     --------------right---------
     * in   [l_inStart]                  [l_inEnd] [index]   [r_inStart]             [r_inEnd]
     *
     * pre的preStart一定是root
     * l_preStart = preStart+1
     *
     * 根据root值找到中序遍历中的index
     * 然后求得
     * leftSize = index-inStart
     * l_inEnd = index-1
     * r_inStart = index+1
     *
     *
     * 然后求得前序遍历中
     * l_preEnd = leftSize+preStart
     * r_preStart = leftSize+preStart+1
     *
     *
     *
     * @author pengchao
     * @date 14:38 2021-01-14
     */
    public static TreeNode buildTree(int[] preorder,int preStart, int preEnd,
                                     int[] inorder,int inStart , int inEnd) {
        //base case
        if (preStart > preEnd) {
            return null;
        }


        int rootValue = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = 0;
        for(int i=inStart;i<=inEnd;i++) {
            if(inorder[i]  == rootValue) {
                index = i;
                break;
            }
        }

        //通过中序遍历，得到左子树的长度
        int leftSize = index - inStart;

        //构造出当前树的根
        TreeNode root = new TreeNode(rootValue);

        //递归构造左右子树
        root.left = buildTree(preorder,preStart+1,preStart+leftSize,
                              inorder,inStart,index-1);

        root.right = buildTree(preorder,preStart+leftSize+1,preEnd,
                              inorder,index+1,inEnd);

        return root;
    }


    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * @param inorder
     * @param inStart
     * @param inEnd
     * @param postorder
     * @param postStart
     * @param postEnd
     * @return
     */
    public static TreeNode buildTreePost(int[] inorder,int inStart,int inEnd,
                              int[] postorder,int postStart,int postEnd) {

        if(inStart>inEnd) {
            return null;
        }

        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        //root在中序遍历中的索引
        int index=0;
        for(int i=inStart;i<=inEnd;i++) {
            if(inorder[i] == rootValue) {
                index = i;
                break;
            }
        }

        int leftSize = index-inStart;

        root.left = buildTreePost(inorder,inStart,index-1,
                postorder,postStart,postStart+leftSize-1);
        root.right = buildTreePost(inorder,index+1,inEnd,
                postorder,postStart+leftSize,postEnd-1);

        return root;
    }


}
