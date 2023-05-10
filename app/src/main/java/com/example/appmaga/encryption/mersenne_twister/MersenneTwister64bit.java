package com.example.appmaga.encryption.mersenne_twister;

public class MersenneTwister64bit {
    private final long UPPER_MASK = 0xFFFFFFFF80000000L;
    private final long LOWER_MASK = 0x7FFFFFFFL;

    // Маска XOR применяется как линейная функция при каждом повороте.
    private final long MASK_A = 0xB5026F5AA96619E9L;

    /* Параметры настройки битовой маски: значения битовой маски для операции скремблирования,
       используемые алгоритмом генерации. */
    private final long MASK_B = 0x71D67FFFEDA60000L;
    private final long MASK_C = 0xFFF7EEE000000000L;
    private final long MASK_D = 0x5555555555555555L;

    // Параметры сдвига темперирования. Эти параметры должны быть меньше или равны w.
    private final int S = 17;
    private final int U = 29;
    private final int T = 37;
    private final int L = 43;

    // Размер слова. Размер каждого слова в последовательности состояния в битах.
    private final int W = 64;
    // Результат разности чисел N и M
    private final static int diffNM = 156;
    // Размер сдвига. Число элементов, которые попускаются при каждом повороте
    private final int M = 156;
    // Размер состояния. Количество элементов (значений) в последовательности состояний.
    private final int N = 312;
    // Множитель инициализации, используемый для заполнения последовательности состояний
    private final long F = 6364136223846793005L;

    private long[] mt;
    private final long magic[];
    private int mtIndex;

    public MersenneTwister64bit(int seed){
        this.mtIndex = 0;
        this.mt = new long[N];
        this.magic = new long[]{ 0x0L, MASK_A };
    }
}
