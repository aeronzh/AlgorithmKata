// An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

// For example, given the following image:

// [
//   "0010",
//   "0110",
//   "0100"
// ]
// and x = 0, y = 2,
// Return 6.

// Hide Company Tags Google
// Hide Tags Binary Search

// DFS
public class Solution {
    int minX = Integer.MAX_VALUE;
    int maxX = 0;
    int minY = Integer.MAX_VALUE;
    int maxY = 0;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] == '0') {
            return 0;
        }
        
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
        
        image[x][y] = '0';
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            minArea(image, newX, newY);
        }
        
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
}

