package com.pc.algorithm.btree;

import com.pc.algorithm.datastructure.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 652. 寻找重复的子树
 *
 *  遍历后，得到子树
 *  子树在判断是否遇到过子树
 *  后序遍历
 *
 * @author pengchao
 * @date 17:49 2021-01-14
 */
public class FindDuplicateSubtrees {

    public static void main(String[] args) {

        List<TreeNode> list = findDuplicateSubtrees(TreeNode.buildNumD());

        System.out.println();
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        //保存结果
        List<TreeNode> res = new ArrayList<>();
        //k=子树字符串,v=次数
        HashMap<String,Integer> duplicateTrees = new HashMap<>();

        postTraversal(root,res,duplicateTrees);

        return res;
    }

    private static String postTraversal(TreeNode root, List<TreeNode> res, HashMap<String, Integer> duplicateTrees) {
        if(root==null) {
            return "#";
        }

        String left = postTraversal(root.left,res,duplicateTrees);
        String right = postTraversal(root.right,res,duplicateTrees);

        //构建以root为根的子树的字符串
        String subtrees = left+","+right+","+root.val;

        //以root为根的子树出现的次数
        int nums = duplicateTrees.getOrDefault(subtrees,0);
        if(nums==1) {
            res.add(root);
        }

        duplicateTrees.put(subtrees,nums+1);

        return subtrees;
    }
}
