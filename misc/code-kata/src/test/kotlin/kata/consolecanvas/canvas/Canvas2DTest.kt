package kata.consolecanvas.canvas

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Assert.fail
import org.junit.Test

class Canvas2DTest {
    private val canvas = Canvas2D(width = 20, height = 10)
    private val fillColour = 'o'

    @Test
    fun `should instantiate canvas of a given width and height`() {
        assertThat(canvas.width).isEqualTo(20)
        assertThat(canvas.height).isEqualTo(10)
    }

    @Test
    fun `fail on an attempt to create a canvas with negative size`() {
        assertAllThrow(IllegalArgumentException::class.java,
                { Canvas2D(-1, 10) },
                { Canvas2D(10, -1) },
                { Canvas2D(-1, -1) }
        )
    }

    @Test
    fun `should draw a single pixel`() {
        // Given an empty canvas, set a single pixel
        canvas.setPixel(2, 4, lineColour)

        // Verify that a the pixel was set without affecting other pixels
        assertCanvasValid { x, y ->
            when {
                Point(x, y) == Point(2, 4) -> lineColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should draw a horizontal line`() {
        // Given an empty canvas, draw a horizontal line
        canvas.drawLine(Point(1, 1), Point(10, 1))

        // Verify that the line was drawn without affecting other pixels
        assertCanvasValid { x, y ->
            when {
                Point(x, y).on(HorizontalLine(1..10, 1)) -> lineColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should draw horizontal line even when x1 is greater than x2`() {
        // Given an empty canvas, draw a horizontal line
        canvas.drawLine(Point(7, 2), Point(1, 2))

        // Verify that the line was drawn without affecting other pixels
        assertCanvasValid { x, y ->
            when {
                Point(x, y).on(HorizontalLine(1..7, 2)) -> lineColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should draw a vertical line`() {
        // Given an empty canvas, draw a vertical line
        canvas.drawLine(Point(3, 2), Point(3, 5))

        // Verify that the line was drawn without affecting other pixels
        assertCanvasValid { x, y ->
            when {
                Point(x, y).on(VerticalLine(3, 2..5)) -> lineColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should draw vertical line even when y1 is greater than y2`() {
        // Given an empty canvas, draw a vertical line
        canvas.drawLine(Point(3, 5), Point(3, 2))

        // Verify that the line was drawn without affecting other pixels
        assertCanvasValid { x, y ->
            when {
                Point(x, y).on(VerticalLine(3, 2..5)) -> lineColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should draw single pixel line`() {
        // Given an empty canvas, draw a single pixel line
        canvas.drawLine(Point(2, 2), Point(2, 2))

        // Verify that the line was drawn without affecting other pixels
        assertCanvasValid { x, y ->
            when {
                Point(x, y) == Point(2, 2) -> lineColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should draw a rectangle`() {
        // Given an empty canvas, draw a rectangle
        canvas.drawRect(Point(2, 3), Point(7, 6))

        // Verify that the rectangle was drawn without affecting other pixels
        assertCanvasValid { x, y ->
            when {
                Point(x, y).on(Rectangle(2, 3, 7, 6)) -> lineColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should fill the whole canvas when it's empty`() {
        // Given an empty canvas, fill it with a uniform color
        canvas.fill(Point(1, 1), fillColour)

        // Expect canvas filled with a uniform colour
        assertCanvasValid { _, _ -> fillColour }
    }

    @Test
    fun `should fill rectangle border if the starting point is on the rectangle`() {
        // Given a canvas with a single rectangle
        canvas.drawRect(Point(2, 3), Point(7, 6))

        // Expect the rectangle border to change colour
        canvas.fill(Point(7, 6), fillColour)

        assertCanvasValid { x, y ->
            when {
                Point(x, y).on(Rectangle(2, 3, 7, 6)) -> fillColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should fill an enclosed area without the border`() {
        // Given a canvas with a single rectangle
        canvas.drawRect(Point(2, 3), Point(7, 6))

        // Expect the rectangle to be filled with a uniform colour without affecting other pixels
        canvas.fill(Point(3, 4), fillColour)

        assertCanvasValid { x, y ->
            when {
                Point(x, y).on(FilledRectangle(3, 4, 6, 5)) -> fillColour
                Point(x, y).on(Rectangle(2, 3, 7, 6)) -> lineColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should fill a line`() {
        // Given a canvas with a single vertical line
        canvas.drawLine(Point(3, 2), Point(3, 5))

        // Expect the line to be filled with a uniform colour without affecting other pixels
        canvas.fill(Point(3, 2), fillColour)

        assertCanvasValid { x, y ->
            when {
                Point(x, y).on(VerticalLine(3, 2..5)) -> fillColour
                else -> emptyColour
            }
        }
    }

    @Test
    fun `should display empty canvas with border`() {
        val canvas = Canvas2D(20, 4)

        assertThat(canvas.display()).isEqualToNormalizingNewlines(
                """
                ----------------------
                |                    |
                |                    |
                |                    |
                |                    |
                ----------------------
                """.trimIndent()
        )
    }

    @Test
    fun `should display canvas with a single line`() {
        val canvas = Canvas2D(20, 4)
        canvas.drawLine(Point(1, 2), Point(6, 2))

        assertThat(canvas.display()).isEqualToNormalizingNewlines(
                """
                ----------------------
                |                    |
                |xxxxxx              |
                |                    |
                |                    |
                ----------------------
                """.trimIndent()
        )
    }

    @Test
    fun `should display non-empty canvas`() {
        val canvas = Canvas2D(20, 4)
        canvas.drawLine(Point(1, 2), Point(6, 2))
        canvas.drawLine(Point(6, 3), Point(6, 4))
        canvas.drawRect(Point(16, 1), Point(20, 3))
        canvas.fill(Point(10, 3), 'o')

        assertThat(canvas.display()).isEqualToNormalizingNewlines(
                """
                ----------------------
                |oooooooooooooooxxxxx|
                |xxxxxxooooooooox   x|
                |     xoooooooooxxxxx|
                |     xoooooooooooooo|
                ----------------------
                """.trimIndent()
        )
    }

    @Test
    fun `display empty string for canvas of size 0x0`() {
        assertThat(Canvas2D(0, 0).display()).isEmpty()
    }

    private fun assertAllThrow(requiredException: Class<*>, vararg closures: () -> Any) {
        for (closure in closures) {
            assertThatThrownBy { closure() }.isInstanceOf(requiredException)
        }
    }

    private fun assertCanvasValid(expectedPixelAt: (x: Int, y: Int) -> Colour) {
        canvas.forEach { point, actualPixel ->
            val expectedPixel = expectedPixelAt(point.x, point.y)
            if (expectedPixel != actualPixel) {
                fail("Wrong pixel at ($point.x, $point.y). Expected '$expectedPixel' but was '$actualPixel'")
            }
        }
    }
}