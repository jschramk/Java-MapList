// Author: Jacob Schramkowski

import java.util.Comparator;

public class LeaderboardExample {

  public static void main(String[] args) {

    // initialize the MapList with random scores
    MapList<String, Integer> leaderBoard = new HashMapList<>();

    leaderBoard.put("Adam", 2);
    leaderBoard.put("Bella", 4);
    leaderBoard.put("Carl", 5);
    leaderBoard.put("Donna", 1);
    leaderBoard.put("Eric", 0);

    // see who is in first place (ascending order)
    String firstPlaceName = leaderBoard.keyAt(leaderBoard.size() - 1);
    System.out.println("First place: " + firstPlaceName);

    // get the score of the person in last place
    int lastPlaceScore = leaderBoard.valueAt(0);
    System.out.println("Score of person in last place: " + lastPlaceScore);

    // get the place of a specific person
    int adamsIndex = leaderBoard.indexOfKey("Adam");
    int adamsPlace = leaderBoard.size() - adamsIndex;
    System.out.println("Adam's place: " + adamsPlace);

    // track place of a person after scoring
    leaderBoard.prepReport();
    leaderBoard.put("Eric", 3);
    MoveReport<String, Integer> moveReport = leaderBoard.getReport();
    System.out.println("Move report: " + moveReport.toString());

    // get the MoveReport's type
    MoveReport.Type type = moveReport.type();

    // condition on the move type
    switch (type) {

      case INSERT: // a new entry was inserted

      case UPDATE: // an entry's value was changed but its index stayed the same

      case MOVE: // an entry's value was changed and moved to a different index

      case REMOVE: // an entry was removed

      case NONE: // nothing happened, likely due to a remove() with a key that was not in the map

    }

    // get the previous and current indices of the modified entry
    int prev = moveReport.from();
    int curr = moveReport.to();

    // get the key and value of the modified entry
    String name = moveReport.key();
    int score = moveReport.value();

    // print final scores for each person in descending order
    Comparator<Integer> reverse = new Comparator<Integer>() {
      @Override public int compare(Integer integer, Integer t1) {
        return Integer.compare(t1, integer);
      }
    };

    leaderBoard.setComparator(reverse);

    for(String person : leaderBoard.keyList()){
      System.out.println(person + ": " + leaderBoard.get(person));
    }

  }

}
