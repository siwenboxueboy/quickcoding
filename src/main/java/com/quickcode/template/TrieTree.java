package com.quickcode.template;

/**
 * 常用的数据结构
 * 前缀树
 */
public class TrieTree {

    /**
     * 前缀树节点
     */
    public static class TrieNode {
        /**
         * 含义:通过该节点的个数<br>
         * 更新规则:每当通过一次改节点,则++pass<br>
         * 作用:查询该数据结构中有多少个字符串是以某个子串作为前缀的<br>
         * 作用举例:从根节点出发,找到字串的pass值,即是该字符子串的前缀数
         */
        public int pass;
        /**
         * 含义:以该节点结尾的个数<br>
         * 更新规则:当以该节点结尾时,则++end<br>
         * 作用:可以查询字符串是否已经存在于该数据结构之中<br>
         * 作用举例:如果从根节点出发,找到以待查询字符串结尾的节点的end=1,则说明已经存在,end=0则说明不存在<br>
         */
        public int end;
        // 数组记录下级节点
        public TrieNode[] nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            // 26个字母所以开成26,相当于是路都建好了
            // 前缀树是在路径上表达字符,那么如何表达路通不通?只要存在下级节点(nexts[i]!=null),则就表示路径存在,即字符存在
            nexts = new TrieNode[26];
        }
    }

    public static class Trie {
        // 头节点
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * 加入字符串
         *
         * @param word 待加入的字符串
         */
        public void insert(String word) {
            char[] chars = word.toCharArray();
            for (char c : chars) {
                TrieNode trieNode = new TrieNode();
                // 新建节点
                // 把节点放入对应位置
                // 设置好对应的pass,end属性
            }
        }
    }

}
