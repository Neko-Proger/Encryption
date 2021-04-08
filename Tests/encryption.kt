import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

class Tests {
    private fun assertFileContent(file1: String, file2: String) {
        val file = File(file1).readLines()
        val fileTwo = File(file2).readLines()
        assertEquals(file, fileTwo)
    }
    @Test
    @Tag("Key")
    fun key() {
        val e = encryption("j","j","j")
        assertEquals(2748, e.key("ABC") )
        assertEquals(215596, e.key("34A2C"))
    }
    @Test
    @Tag("Coding")
    fun fileCoding(){
        val e = encryption("215596","input/test.txt","input/test1.txt")
        e.fileCoding()
        val i =  encryption("215596","input/test1.txt","input/test2.txt")
        i.fileDecoding()
        assertFileContent("input/test.txt","input/test2.txt")
        File("input/test1.txt").delete()
        File("input/test2.txt").delete()
        val e1 = encryption("144","input/dictation.txt","input/test1.txt")
        e1.fileCoding()
        val i1 =  encryption("144","input/test1.txt","input/test2.txt")
        i1.fileDecoding()
        assertFileContent("input/dictation.txt","input/test2.txt")
        File("input/test1.txt").delete()
        File("input/test2.txt").delete()
    }
    @Test
    @Tag("Coding")
    fun xor(){
        val e = encryption("215596","input/test.txt","input/test1.txt")
        assertEquals(51,e.xor(153,2))
        assertEquals(797,e.xor(456,725))
        assertEquals(456,e.xor(797,725))
    }
}