import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class FrequencyQuery {

  static List<Integer> freqQuery(int[][] queries) {
    List<Integer> status = new ArrayList<>();
    HashMap<Integer, Integer> map1 = new HashMap<>();
    int notify = 0;

    for (int i = 0; i < queries.length; i++) {
      int operation = 0;
      int operationValue = 0;

      int[] temp = queries[i];
      operation = temp[0];
      operationValue = temp[1];

      if (operation == 1) {
        if (!map1.containsKey(operationValue)) {
          map1.put(operationValue, 1);
        } else {
          int currentVal = map1.get(operationValue);
          currentVal++;
          map1.put(operationValue, currentVal);
        }
      } else if (operation == 2) {
        if (map1.containsKey(operationValue)) {
          int currentVal = map1.get(operationValue);
          currentVal--;
          map1.put(operationValue, currentVal);
        }
      } else {
        if (map1.containsValue(operationValue)) {
          notify++;
          status.add(1);
        } else {
          notify++;
          status.add(0);
        }
      }
    }

    System.out.println(notify);
    return status;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

      int q = Integer.parseInt(bufferedReader.readLine().trim());
      int[][] queries = new int[q][2];

      for (int i = 0; i < q; i++) {
        String[] query = bufferedReader.readLine().split(" ");
        queries[i][0] = Integer.parseInt(query[0]);
        queries[i][1] = Integer.parseInt(query[1]);
      }

      List<Integer> ans = freqQuery(queries);

      try (BufferedWriter bufferedWriter =
          new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {

        bufferedWriter.write(ans.stream().map(Object::toString).collect(joining("\n")) + "\n");
      }
    }
  }
}
