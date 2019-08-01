package com.hankcs.algorithm;

import static com.hankcs.algorithm.DoctorExample.Feel.Cold;
import static com.hankcs.algorithm.DoctorExample.Feel.Dizzy;
import static com.hankcs.algorithm.DoctorExample.Feel.Normal;
import static com.hankcs.algorithm.DoctorExample.Status.Fever;
import static com.hankcs.algorithm.DoctorExample.Status.Healthy;

/** @author Administrator */
public class DoctorExample {
  enum Status {
    /** 健康 */
    Healthy,
    /** 发烧 */
    Fever
  }

  enum Feel {
    /** 正常 */
    Normal,
    /** 冷 */
    Cold,
    /** 头晕 */
    Dizzy,
  }

  public static void main(String[] args) {
    int[] states = new int[] {Healthy.ordinal(), Fever.ordinal()};
    int[] observations = new int[] {Normal.ordinal(), Cold.ordinal(), Dizzy.ordinal()};
    double[] startProbability = new double[] {0.6, 0.4};
    double[][] transitionProbability =
        new double[][] {
          {0.7, 0.3},
          {0.4, 0.6},
        };
    double[][] emissionProbability =
        new double[][] {
          {0.5, 0.4, 0.1},
          {0.1, 0.3, 0.6},
        };
    int[] result =
        Viterbi.compute(
            observations, states, startProbability, transitionProbability, emissionProbability);
    // Healthy Healthy Fever
    for (int r : result) {
      System.out.print(Status.values()[r] + " ");
    }
    System.out.println();
  }
}
