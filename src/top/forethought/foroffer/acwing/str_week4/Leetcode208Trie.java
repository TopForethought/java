package top.forethought.foroffer.acwing.str_week4;


class Trie {
    static    class Node{
        private boolean isEnd;
        private Node[] kids;
        Node(){

            isEnd=false;
            kids=new Node[26];
        }
    }

    private Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root=new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node n=root;
        for(int i=0;i<word.length();i++){
            int  c=word.charAt(i)-'a';
            if(n.kids[c]==null){
                n.kids[c]=new Node();
            }
            n=n.kids[c];
        }
        n.isEnd=true;// 标记为该节点为 单词末尾
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {

        Node n=root;
        for(int i=0;i<word.length();i++){
            int  c=word.charAt(i)-'a';
            if(n.kids[c]==null){
                return false;
            }
            n=n.kids[c];
        }



        return n.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
       Node n=root;
        for(int i=0;i<prefix.length();i++){
            int  c=prefix.charAt(i)-'a';
            if(n.kids[c]==null){
                return false;
            }
            n=n.kids[c];
        }



        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */