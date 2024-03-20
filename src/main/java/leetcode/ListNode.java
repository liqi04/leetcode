package leetcode;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }
    ListNode(int... arr){
        ListNode temp = null;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 ){
                this.val = arr[i];
                this.next = temp = new ListNode();
            }else {
                temp.val = arr[i];
                if (i < arr.length-1){
                    temp.next = new ListNode();
                    temp = temp.next;
                }

            }
        }
    }


    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int val = this.val;
        s.append(val);
        ListNode next = this.next;
        while (next != null) {
            s.append(" -> ").append(next.val);
            next = next.next;
        }
        return s.toString();
    }
}
