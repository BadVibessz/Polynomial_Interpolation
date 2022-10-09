import mine.polynomial.LagrangePolynomial
import mine.polynomial.NewtonPolynomial
import kotlin.system.measureTimeMillis

fun main() {


//    println((Polynomial(1.0, 0.0, 1.0) - Polynomial(-1.0, 0.0, 1.0)).degree)
//    println(Polynomial(1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 100.0)); // 13 stepen
    //println((Polynomial(1.0, 2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0).times(0.0).degree));
//
//    var p = Polynomial(1.0);
//    var p2 = Polynomial(1.0);
//    println(p == p2);
//
//


//    val hashMap1 = HashMap<Double, Double>(); // x^2
//
//    hashMap1[-1.0] = 1.0;
//    hashMap1[0.0] = 0.0;
//    hashMap1[1.0] = 1.0;
//
//    println(LagrangePolynomial(hashMap1).GetPolynomial());

//    val hashMap2 = HashMap<Double, Double>(); // f(x) = tgx
//    hashMap2[-1.5] = -14.1014;
//    hashMap2[0.75] = -0.931596;
//    hashMap2[0.0] = 0.0;
//    hashMap2[0.75] = 0.931596;
//    hashMap2[1.5] = 14.1014;

    //println("Lagrange: " + LagrangePolynomial(hashMap2).GetPolynomial());
    //println("Newton: " + NewtonPolynomial(hashMap2).GetPolynomial());


    //val withNewton = NewtonPolynomial(hashMap2);
    //hashMap2.remove(1.5);

    //val withoutNewton = NewtonPolynomial(hashMap2);


    //val addedNewton = NewtonPolynomial(hashMap2).also { it.AddNode(1.5, 14.1014) };

//    println("Newton inited with all nodes: " + withNewton.GetPolynomial());
//    println("Newton w/out last node: " + withoutNewton.GetPolynomial());
//    println("Newton with last node added after init: " + addedNewton.GetPolynomial());
//    println("Are they equal: " + (withNewton.GetPolynomial() == addedNewton.GetPolynomial()));


    val hashMap = HashMap<Double, Double>();

    val func = { x: Double -> Math.tan(x) };

    for (i in 1..1000)
        hashMap.put(i.toDouble(), func(i.toDouble()));

    val lagrangeTime = measureTimeMillis {
        LagrangePolynomial(hashMap).GetPolynomial();
    };


    val newtonTime = measureTimeMillis {
        NewtonPolynomial(hashMap).GetPolynomial();
    };

    //println(NewtonPolynomial(hashMap).GetPolynomial())


    hashMap.clear();
    hashMap[1.0] = 1.0;
    val newtonAddTime = measureTimeMillis {
        val newton = NewtonPolynomial(hashMap);

        for (i in 2..1000)
            newton.AddNode(i.toDouble(), func(i.toDouble()));

        newton.GetPolynomial();
    };

    println("Lagrange took: $lagrangeTime ms");
    println("Newton took: $newtonTime ms");
    println("Newton adding step by step took: $newtonAddTime ms");

    val div = String.format("%.1f", lagrangeTime.toDouble() / newtonTime.toDouble() * 100);
    println("Newton $div% faster than Lagrange")


}