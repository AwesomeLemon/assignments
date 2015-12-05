package hw09

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Alexander on 20.11.2015.
 */
class asciiToBfTest {
    @Test fun helloWorldTest() {
        val hw = "Hello World!"
        val br_hw = AsciiToBrainfuck().translate(hw)
        val res = brainfuckInterpeter().interpete(br_hw, false)
        assertEquals(hw, res)
    }

    @Test fun emptyInput() {
        val bf = AsciiToBrainfuck().translate("")
        val res = brainfuckInterpeter().interpete(bf, false)
        assertEquals("", res)
    }

    @Test fun oneSymbolInput() {
        val bf = AsciiToBrainfuck().translate("!")
        val res = brainfuckInterpeter().interpete(bf, false)
        assertEquals("!", res)
    }

    @Test fun findBestValue() {
        val input = "'Cause the dead don't shuffle - they run! Rush through streets of slaughter clutching your gun!"
        val in2 = "A short story is a piece of prose fiction, which can be read in a single setting.[1] Emerging from earlier oral storytelling traditions in the 17th century, the short story has grown to encompass a body of work so diverse as to defy easy characterization. At its most prototypical the short story features a small cast of named characters, and focuses on a self-contained incident with the intent of evoking a single effect or mood.[2] In doing so, short stories make use of plot, resonance, and other dynamic components to a far greater degree than is typical of an anecdote, yet to a far lesser degree than a novel. While the short story is largely distinct from the novel, authors of both generally draw from a common pool of literary techniques." +
        "Short stories have no set length. In terms of word count there is no official demarcation between an anecdote, a short story, and a novel. Rather, the form's parameters are given by the rhetorical and practical context in which a given story is produced and considered, so that what constitutes a short story may differ between genres, countries, eras, and commentators.[3] Like the novel, the short story's predominant shape reflects the demands of the available markets for publication, and the evolution of the form seems closely tied to the evolution of the publishing industry and the submission guidelines of its constituent houses.[4]" +
        "The short story has been considered both an apprenticeship form preceding more lengthy works, and a crafted form in its own right, collected together in books of similar length, price, and distribution as novels. Short story writers may define their works as part of the artistic and personal expression of the form. They may also attempt to resist categorization by genre and fixed formation."
        var in3 = "(colloquial, often humorous) Used as a response to a question such as How long will it take? or How big is it? when the length or size is unknown, infinite, or variable."
        val in4 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        val in5 = "A short story is a piece of prose fiction, which can be read in a single setting.[1] Emerging from earlier oral storytelling traditions in the 17th century, the short story has grown to encompass a body of work so diverse as to defy easy characterization"
        val in5len = in5.length
        in3 = in2 + in2 + in2 + input//"!@#$%^&*()_+_)(*&^%$#@!ZXCVBNM<>?:LKJHGFDSAQWERFGHJMK<>"//in2 + in3 + in2 + in2 + in2

        val a2b = AsciiToBrainfuck()
        val bf = a2b.translate(in3)
        val bfLen = bf.length
//        var min = Int.MAX_VALUE
//        for (i in 1..255) {
//            val cur = AsciiToBrainfuck().translate(in3, i)
//            if (cur.length < min) {
//                min = cur.length
//                println("$i" + " " + "$min")
//            }
//        }
//        val bestBf = a2b.translateChooseBest(in3)
//        val bestBfLen = bestBf.first.length
//        val mean = a2b.translate(in3)
//        val meanLen = mean.length
//        print(bestBf)
        val res = brainfuckInterpeter().interpete(bf, false)
        assertEquals(in3, res)
    }

}