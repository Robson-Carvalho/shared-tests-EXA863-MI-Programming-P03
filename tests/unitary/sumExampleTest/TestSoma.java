public class TestSoma {

    @Test
    public void testSomaPositivos() {
        assertEquals(5, Soma.soma(2, 3));
    }

    @Test
    public void testSomaNegativos() {
        assertEquals(-5, Soma.soma(-2, -3));
    }

    @Test
    public void testSomaMisto() {
        assertEquals(1, Soma.soma(-2, 3));
    }

    @Test
    public void testSomaZero() {
        assertEquals(0, Soma.soma(0, 0));
    }
}
