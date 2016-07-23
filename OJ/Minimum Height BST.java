import java.util.*;

public class MinimalBST {
    public int buildMinimalBST(int[] vals) {
        // write code here
        if (vals == null || vals.length == 0) {
			return 0;
        }
        
        return (int)(Math.log10(vals.length) / Math.log10(2)) + 1;
    }
}