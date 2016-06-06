// The API: int read4(char *buf) reads 4 characters at a time from a file.
//
// The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
//
// By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
//
// Note:
// The read function may be called multiple times.
//
// Hide Company Tags Bloomberg Google Facebook
// Hide Tags String
// Hide Similar Problems (E) Read N Characters Given Read4

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */

    private char[] temp;
    private int tempPtr;
    private int count;

    public Solution() {
        temp = new char[4];
        tempPtr = 0;
        count = 0;
    }

    public int read(char[] buf, int n) {
        int len = 0;

        while (len < n) {
            // check if there is leftover chars from last call
            // do not update count if there is leftover chars
            if (tempPtr == 0) {
                count = read4(temp);
            }

            if (count == 0) {
                break;
            }

            while (tempPtr < count && len < n) {
                buf[len] = temp[tempPtr];
                len++;
                tempPtr++;
            }

            if (tempPtr >= count) {
                tempPtr = 0;
            }
        }

        return len;
    }
}
