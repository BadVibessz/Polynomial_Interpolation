package mine.polynomial

class NewtonPolynomial(hashMap: HashMap<Double, Double>) : Polynomial() {

    private val _polynomial: Polynomial
    private val _hashMap: MutableMap<Double, Double>;
    fun GetPolynomial(): Polynomial = this._polynomial;

    init {
        val result = Polynomial();
        this._hashMap = hashMap.toMutableMap();

        var j = 0;
        for ((key, value) in hashMap) {
            result += CalculateBasePolynomial(j) * CalculateDividedDifferences(j + 1);
            j++;
        }
        _polynomial = result;
    }

    private fun CalculateBasePolynomial(j: Int): Polynomial {
        val result = Polynomial(1.0);
        val keys = _hashMap.keys.take(j);

        var i = 0;
        while (i < j) {
            result *= Polynomial(-keys[i], 1.0);
            i++;
        }

        return result;
    }

    private fun CalculateDividedDifferences(n: Int): Double {

        val keys = _hashMap.keys.take(n).toList();
        val values = _hashMap.values.take(n).toList();

        if (n == 1)
            return values.first();

        var i = 0;
        var result = 0.0;

        val production = fun(): Double {
            var temp = 1.0;
            var k = 0;
            while (k < n) {
                if (k != i)
                    temp *= keys[i] - keys[k];
                k++;
            }
            return temp;
        };

        while (i < n) {
            result += values[i] / production();
            i++;
        }
        return result;
    }

    fun AddNode(key: Double, value: Double) {
        this._hashMap.put(key, value);
        val n = _hashMap.size;

        this._polynomial += CalculateBasePolynomial(n - 1) *
                CalculateDividedDifferences(n);
    }

}