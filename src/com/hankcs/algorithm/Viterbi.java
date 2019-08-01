package com.hankcs.algorithm;

/**
 * 维特比算法
 *
 * @author hankcs
 */
public class Viterbi {
  /**
   * 求解HMM模型
   *
   * @param obs 观测序列
   * @param states 隐状态
   * @param start_p 初始概率（隐状态）
   * @param trans_p 转移概率（隐状态）
   * @param emit_p 发射概率 （隐状态表现为显状态的概率）
   * @return 最可能的序列
   */
  public static int[] compute(
      int[] obs, int[] states, double[] start_p, double[][] trans_p, double[][] emit_p) {
    // 记录序列每一步骤计算得到的最大概率
    double[][] V = new double[obs.length][states.length];
    // 记录序列每一步骤计算得到最大概率对应的state
    int[][] path = new int[states.length][obs.length];
    for (int y : states) {
      // 设置初始概率
      V[0][y] = start_p[y] * emit_p[y][obs[0]];
      // 设置初始状态
      path[y][0] = y;
    }
    // 从t1步开始计算
    for (int t = 1; t < obs.length; ++t) {
      int[][] newpath = new int[states.length][obs.length];
      // 遍历当前步的所有状态
      for (int y : states) {
        double prob = -1;
        int state;
        // 遍历t-1步的所有状态
        for (int y0 : states) {
          // 使用序列上一步的概率计算当前步的最大概率
          double nprob = V[t - 1][y0] * trans_p[y0][y] * emit_p[y][obs[t]];
          if (nprob > prob) {
            prob = nprob;
            state = y0;
            // 记录最大概率
            V[t][y] = prob;
            // 记录路径
            System.arraycopy(path[state], 0, newpath[y], 0, t);
            newpath[y][t] = y;
          }
        }
      }
      path = newpath;
    }
    // 所有路径、概率、状态均已计算好，从中选取概率最大的路径
    double prob = -1;
    int state = 0;
    for (int y : states) {
      if (V[obs.length - 1][y] > prob) {
        prob = V[obs.length - 1][y];
        state = y;
      }
    }
    return path[state];
  }
}
