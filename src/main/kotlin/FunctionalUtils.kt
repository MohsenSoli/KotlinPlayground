

tailrec fun loop(n: Int) {
    if (n < 0) return
    loop(n - 1)
}