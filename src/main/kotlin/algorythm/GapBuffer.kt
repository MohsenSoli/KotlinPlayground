package algorythm

internal object GFG {
    var buffer = CharArray(50)
    var gap_size = 10
    var gap_left = 0
    var gap_right = gap_size - gap_left - 1
    var size = 10

    // Function that is used to grow the gap
    // at index position and return the array
    fun grow(k: Int, position: Int) {
        val a = CharArray(size)

        // Copy characters of buffer to a[]
        // after position
        for (i in position until size) {
            a[i - position] = buffer[i]
        }

        // Insert a gap of k from index position
        // gap is being represented by '-'
        for (i in 0 until k) {
            buffer[i + position] = '_'
        }

        // Reinsert the remaining array
        for (i in 0 until k) {
            buffer[position + k + i] = a[i]
        }
        size += k
        gap_right += k
    }

    // Function that is used to move the gap
    // left in the array
    fun left(position: Int) {
        // Move the gap left character by character
        // and the buffers
        while (position < gap_left) {
            gap_left--
            gap_right--
            buffer[gap_right + 1] = buffer[gap_left]
            buffer[gap_left] = '_'
        }
    }

    // Function that is used to move the gap
    // right in the array
    fun right(position: Int) {

        // Move the gap right character by character
        // and the buffers
        while (position > gap_left) {
            gap_left++
            gap_right++
            buffer[gap_left - 1] = buffer[gap_right]
            buffer[gap_right] = '_'
        }
    }

    // Function to control the movement of gap
    // by checking its position to the point of
    // insertion
    fun move_cursor(position: Int) {
        if (position < gap_left) {
            left(position)
        } else {
            right(position)
        }
    }

    // Function to insert the string to the buffer
    // at point position
    fun insert(input: String, position: Int) {
        var position = position
        val len = input.length
        var i = 0

        // If the point is not the gap check
        // and move the cursor to that point
        if (position != gap_left) {
            move_cursor(position)
        }

        // Insert characters one by one
        while (i < len) {
            // If the gap is empty grow the size
            if (gap_right == gap_left) {
                val k = 10
                grow(k, position)
            }

            // Insert the character in the gap and
            // move the gap
            buffer[gap_left] = input[i]
            gap_left++
            i++
            position++
        }
    }

    // Driver code
    fun main(args: Array<String>) {
        // Initializing the gap buffer with size 10
        for (i in 0..9) {
            buffer[i] = '_'
        }
        println(
            "Initializing the gap" +
                    " buffer with size 10"
        )
        for (i in 0 until size) {
            print(buffer[i].toString() + " ")
        }
        println()

        // Inserting a string to buffer
        var input = "GEEKSGEEKS"
        var position = 0
        insert(input, position)
        println()
        println(
            "Inserting a string " +
                    "to buffer: GEEKSGEEKS"
        )
        print("Output: ")
        for (i in 0 until size) {
            print(buffer[i].toString() + " ")
        }
        input = "FOR"
        position = 5
        insert(input, position)
        println()
        println()
        println(
            "Inserting a string" +
                    " to buffer: FOR"
        )
        print("Output: ")
        for (i in 0 until size) {
            print(buffer[i].toString() + " ")
        }
    }
}