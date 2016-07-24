// http://algorithm.yuanbin.me/zh-hans/basics_algorithm/math/gcd.html
// http://www.cnblogs.com/EdwardLiu/p/4200180.html

public int gcd(int a, int b) {
    if (b == 0) {
        return a;
    }

    return gcd(b, a % b);
}

public int lcm(int a, int b) {
    return a * (b / gcd(a, b));
}
