package common;

import algorithm.Algorithm;

/**
 *  TODO
 * Factory design patterns is used to encapsulate algorithm selection and instantiation logic
 *
 * @author Harrison Mfula
 */
public class AlgorithmFactory {

    private Algorithm algorithm;

    public AlgorithmFactory() {
    }


    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
