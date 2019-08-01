package com.hankcs.algorithm;

import static com.hankcs.algorithm.WeatherExample.Activity.Clean;
import static com.hankcs.algorithm.WeatherExample.Activity.Shop;
import static com.hankcs.algorithm.WeatherExample.Activity.Walk;
import static com.hankcs.algorithm.WeatherExample.Weather.Rainy;
import static com.hankcs.algorithm.WeatherExample.Weather.Sunny;

/** @author Administrator */
public class WeatherExample {
  enum Weather {
    /** 下雨 */
    Rainy,
    /** 天晴 */
    Sunny,
  }

  enum Activity {
    /** 散步 */
    Walk,
    /** 逛超市 */
    Shop,
    /** 洗衣服 */
    Clean,
  }

  public static void main(String[] args) {
    int[] states = new int[] {Rainy.ordinal(), Sunny.ordinal()};
    int[] observations = new int[] {Walk.ordinal(), Shop.ordinal(), Clean.ordinal()};
    double[] startProbability = new double[] {0.6, 0.4};
    double[][] transitionProbability =
        new double[][] {
          {0.7, 0.3},
          {0.4, 0.6},
        };
    double[][] emissionProbability =
        new double[][] {
          {0.1, 0.4, 0.5},
          {0.6, 0.3, 0.1},
        };
    int[] result =
        Viterbi.compute(
            observations, states, startProbability, transitionProbability, emissionProbability);
    for (int r : result) {
      System.out.print(Weather.values()[r] + " ");
    }
    System.out.println();
  }
}
