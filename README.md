# Java-MapList

## What's a MapList?
The MapList is an augmented version of the Java Map data structure with the following features: 
* Values accessible by both keys and indices
* Key list sorted by associated values
* Entry movement reports detailing previous and current indices of modified entries

## Who needs it?
The MapList data structure is purpose-built for applications where entries need to be accessed by keys AND indices, while remaining sorted by value. A representative example would be a leaderboard where the keys are names of players and the values are scores. At any given time, one might want to know who is in first place, but also what a specific player's score is in O(1) time. In addition to that, one may want to know how a change in score would affect a player's place in the leaderboard. For this, the MapList interface contains methods to track order changes with simple syntax and minimal impact on performance.

The MapList pairs very well with RecyclerViews for the Android developers among us.

## Can I trust this code?
My implementation of the HashMapList is not perfect and has not been tested extensively, but anything within the intended usage as shown below should work properly.

## How do I learn this power?
Let's look at the leaderboard example I mentioned earlier (full source code is posted):

**Initialize the MapList with random scores**
~~~
MapList<String, Integer> leaderBoard = new HashMapList<>();

leaderBoard.put("Adam", 2);
leaderBoard.put("Bella", 4);
leaderBoard.put("Carl", 5);
leaderBoard.put("Donna", 1);
leaderBoard.put("Eric", 0);
~~~

**Get the name of the person in first place (entries are in ascending order)**
~~~
String firstPlaceName = leaderBoard.keyAt(leaderBoard.size() - 1);
System.out.println("First place: " + firstPlaceName);
~~~
Output:
~~~
First place: Carl
~~~


**Get the score of the person in last place**
~~~
int lastPlaceScore = leaderBoard.valueAt(0);
System.out.println("Score of person in last place: " + lastPlaceScore);
~~~
Output:
~~~
Score of person in last place: 0
~~~


**Get the place of a specific person**
~~~
int adamsIndex = leaderBoard.indexOfKey("Adam");
int adamsPlace = leaderBoard.size() - adamsIndex;
System.out.println("Adam's place: " + adamsPlace);
~~~
Output:
~~~
Adam's place: 3
~~~


**Track a person's place after scoring**
~~~
leaderBoard.prepReport();
leaderBoard.put("Eric", 3);
MoveReport<String, Integer> moveReport = leaderBoard.getReport();
System.out.println("Move report: " + moveReport.toString());
~~~
Output:
~~~
Move report: Moved key "Eric" from index 0 to index 2
~~~


**Condition on MoveReport type**
~~~
MoveReport.Type type = moveReport.type();

switch (type) {

  case INSERT: // a new entry was inserted

  case UPDATE: // an entry's value was changed but its index stayed the same

  case MOVE: // an entry's value was changed and moved to a different index

  case REMOVE: // an entry was removed

  case NONE: // nothing happened, likely due to a remove() with a key that was not in the map

}
~~~


**Get the previous and current indices of the modified entry**
~~~
int prev = moveReport.from();
int curr = moveReport.to();
~~~


**Get the key and value of the modified entry**
~~~
String name = moveReport.key();
int score = moveReport.value();
~~~


**Print final scores for each person in descending order**
~~~
List<String> order = leaderBoard.keyList();
Collections.reverse(order);

for(String person : order){
  System.out.println(person + ": " + leaderBoard.get(person));
}
~~~
Output:
~~~
Carl: 5
Bella: 4
Eric: 3
Adam: 2
Donna: 1
~~~
