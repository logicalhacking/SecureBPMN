/* Copyright 2012-2015 SAP SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.aniketos.securebpmn.export.xacml.export;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains utility methods that are being used in the XACML export.
 *
 *
 */
public class ExportUtil {

    private static int permLevel = -1;

    /**
     * Generates integer partitions in anti-lexicographic order using the
     * algorithm ZS1 as described in Zoghbi, A. and Stojmenovic, I.
     * "Fast Algorithms for Generating Integer Partitions", published in 1998.
     * For each partition, the parts are provided in descending order.
     *
     * @param n
     *            The integer for which the partitions should be generated.
     * @return A List containing the integer partitions, each represented as a
     *         integer Array.
     */
    public static List<int[]> generateIntegerPartitions(int n) {

        List<int[]> res = new ArrayList<int[]>();

        int[] x = new int[n];

        for (int i = 1; i <= n; i++) {
            x[i - 1] = 1;
        }

        x[0] = n;
        int m = 1;
        int h = 1;

        res.add(copySubarray(x, 0, 1));

        while (x[0] != 1) {

            if (x[h - 1] == 2) {
                m = m + 1;
                x[h - 1] = 1;
                h = h - 1;
            } else {
                int r = x[h - 1] - 1;
                int t = m - h + 1;
                x[h - 1] = r;

                while (t >= r) {
                    h = h + 1;
                    x[h - 1] = r;
                    t = t - r;
                }

                if (t == 0) {
                    m = h;
                } else {
                    m = h + 1;

                    if (t > 1) {
                        h = h + 1;
                        x[h - 1] = t;
                    }
                }
            }
            res.add(copySubarray(x, 0, m));
        }

        return res;

    }

    /**
     * Copies a subarray of a given array and returns it.
     *
     * @param src
     *            The source array that should be used.
     * @param start
     *            The start index.
     * @param end
     *            The end index.
     * @return A subarray of the source array, including the value at the start
     *         index and excluding the value at the end index.
     */
    private static int[] copySubarray(int[] src, int start, int end) {

        int[] res = new int[end - start];
        int resPos = 0;

        for (int i = start; i < end; i++) {
            res[resPos] = src[i];
            resPos++;
        }

        return res;
    }

    /**
     * Generates all permutations of a sequence containing the numbers from 1 to
     * n.
     *
     * @param n
     *            The upper bound of the permutations.
     * @return A List with all permutations of the sequence.
     */
    public static List<int[]> generateLocationPermutations(int n) {
        List<int[]> res = new ArrayList<int[]>();
        permLevel = -1;

        int[] value = new int[n];
        for (int i = 0; i < value.length; i++) {
            value[i] = 0;
        }
        permVisit(value, n, 0, res);

        return res;
    }

    /**
     * Helper method for generating the integer sequence permutations. Should
     * not be used on its own!
     *
     */
    private static void permVisit(int[] value, int n, int k, List<int[]> res) {
        permLevel = permLevel + 1;
        value[k] = permLevel;

        if (permLevel == n) {
            res.add(copySubarray(value, 0, value.length));
        } else {
            for (int i = 0; i < n; i++) {
                if (value[i] == 0)
                    permVisit(value, n, i, res);
            }
        }
        permLevel = permLevel - 1;
        value[k] = 0;

    }

}
