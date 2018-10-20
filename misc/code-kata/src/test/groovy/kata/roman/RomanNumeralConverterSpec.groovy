package kata.roman


import spock.lang.Specification
import spock.lang.Unroll

class RomanNumeralConverterSpec extends Specification {

    def "converts roman symbols"() {
        expect:
        decimalNumber == toDecimal(romanSymbol)

        where:
        romanSymbol | decimalNumber
        'I'         | 1
        'V'         | 5
        'X'         | 10
        'L'         | 50
        'C'         | 100
        'D'         | 500
        'M'         | 1000
    }

    def "converts simple roman numbers with multiple symbols"() {
        expect:
        decimalNumber == toDecimal(romanNumber)

        where:
        romanNumber | decimalNumber
        'VI'        | 6
        'XI'        | 11
        'XX'        | 20
        'XXX'       | 30
        'LX'        | 60
        'LXI'       | 61
        'LXXX'      | 80
        'LXX'       | 70
        'CCC'       | 300
        'DCCXXI'    | 721
        'MXI'       | 1011
        'MMM'       | 3000
        'MMMDLV'    | 3555
    }

    def "converts roman numbers with repeating symbols"() {
        expect:
        decimalNumber == toDecimal(romanSymbol)

        where:
        romanSymbol    | decimalNumber
        'II'           | 2
        'III'          | 3
        'VIII'         | 8
        'XII'          | 12
        'XIII'         | 13
        'XX'           | 20
        'XXX'          | 30
        'DCCC'         | 800
        'MMM'          | 3000
        'MMMCCCXXXIII' | 3333
    }

    def "converts mixed case roman numbers"() {
        expect:
        decimalNumber == toDecimal(romanSymbol)

        where:
        romanSymbol | decimalNumber
        'i'         | 1
        'v'         | 5
        'x'         | 10
        'l'         | 50
        'c'         | 100
        'd'         | 500
        'm'         | 1000
        'vi'        | 6
        'Xxx'       | 30
        'DccXXI'    | 721
        'mmm'       | 3000
    }

    def "converts roman numbers with subtraction"() {
        expect:
        decimalNumber == toDecimal(romanSymbol)

        where:
        romanSymbol | decimalNumber
        'IV'        | 4
        'IX'        | 9
        'XIX'       | 19
        'XL'        | 40
        'LIX'       | 59
        'XC'        | 90
        'CXC'       | 190
        'MMMCMXCIX' | 3999
    }

    def "conversion should fail for unknown roman symbol"() {
        when:
        toDecimal('F')

        then:
        thrown NumberFormatException
    }

    def "conversion should fail when at least one symbol unknown"() {
        when:
        toDecimal('MMMDXF')

        then:
        thrown NumberFormatException
    }

    def "conversion should fail when roman number empty"() {
        when:
        toDecimal('')

        then:
        thrown NumberFormatException
    }

    def "conversion should fail when at least one white space"() {
        when:
        toDecimal(' MMDI ')

        then:
        thrown NumberFormatException
    }

    @Unroll
    def "conversion should fail for #romanNumber due to invalid subtraction"() {
        when:
        toDecimal(romanNumber)

        then:
        thrown NumberFormatException

        where:
        romanNumber | _
        'IL'        | _
        'LC'        | _
        'DM'        | _
        'VM'        | _
        'XM'        | _
        'IM'        | _
    }

    def "conversion should fail when too many symbols in a row"() {
        when:
        toDecimal(romanNumber)

        then:
        thrown NumberFormatException

        where:
        romanNumber | _
        'IIII'      | _
        'IIIII'     | _
        'XXXX'      | _
        'XXXXX'     | _
        'CCCC'      | _
        'CCCCC'     | _
        'MMMMM'     | _
        'MMMMMM'    | _
    }

    def "conversion should fail when wrong symbols in a row"() {
        when:
        toDecimal(romanNumber)

        then:
        thrown NumberFormatException

        where:
        romanNumber | _
        'VV'        | _
        'VVV'       | _
        'LL'        | _
        'LLL'       | _
        'DD'        | _
        'DDD'       | _
    }

    private static int toDecimal(String romanNumeral) {
        new RomanNumeralConverter().convert(romanNumeral)
    }
}