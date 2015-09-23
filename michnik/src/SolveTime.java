public class SolveTime {

  public class Result {
    int []details;
    int time;

    private Result(int []details, int time) {
      this.details = details.clone();
      this.time = time;
    }
  }

  /*
      i-ую деталь на j-ом станке map[i][j] времени
   */
  public Result solve(int [][]map) {
    if (map.length == 0) {
      return new Result(new int[0], 0);
    }
    ret = null;
    rec(0, new int[map[0].length], new int[map.length], 0, map);
    return ret;
  }

  Result ret;

  private void rec(int detail, int []machines, int []details, int currentTime, int [][]map) {
    if (ret != null && currentTime >= ret.time) {
      return ;
    }
    if (detail == map.length) {
      ret = new Result(details, currentTime);
      return ;
    }
    for (int i = 0; i < machines.length; ++i) {
      machines[i] += map[detail][i];
      details[detail] = i;
      rec(detail + 1, machines, details, Math.max(currentTime, machines[i]), map);
      machines[i] -= map[detail][i];
    }
  }
}
