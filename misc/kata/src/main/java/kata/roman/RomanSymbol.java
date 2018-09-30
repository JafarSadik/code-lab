package kata.roman;


enum RomanSymbol {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    RomanSymbol(int decimal) {
        this.decimal = decimal;
    }

    int toDecimal() {
        return decimal;
    }

    public static RomanSymbol romanSymbol(char symbol) {
        return valueOf(String.valueOf(symbol));
    }

    int decimal;
}