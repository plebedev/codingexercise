Coding exercise
==============

Here is what I came up with after spending roughly 2.5 hours

### Assumptions
1. The player does not have a map, he only knows what to collect. It simplifies the algorithm and data structure. I use simple map instead of building a full graph,
and use DFS to traverse the map. It stops when all items are collected, or returns to starting point if some items to be collected do not exist on the map.
If I could have invested more time, I could implement another flavor when the map is known. In that case, more optimal path could be found.
2. The player needs to collect only one instance of each item, i.e. there are no duplicates in scenario file
3. It is not a coding competition, so I spent a little time to do a simple design vs quickly code the algorithm. It is still not perfect but I feel it is good enough for the purpose
4. There are few unit tests present that helped me testing few core pieces. I did not spend time adding more tests due to self imposed time constraint.
5. The project uses maven, so it can be build and run outside of Eclipse.
6. Maybe, something else that I forgot :) Feel free to ask questions

### Run

Run 
`run.sh map.xml scenario.txt`

It will build and execute the program with provided map.xml and scenario.txt. You can pass your own file names to the script to test different maps and scenarios.
Below is the output of the provided files:
Room Id              Room Name                 Object Collected
-----------------------------------------------------------------
scullery             Scullery                       None           
secret-passage-west  Secret Passage (West)          None           
library              Library                        Book           
hallway              Hallway                        None           
dining-room          Dining Room                    Plate          
kitchen              Kitchen                        None           
dining-room          Dining Room                    None           
hallway              Hallway                        None           
porch                Porch                          None           
front-yard           Front Yard                     None           
dirt-track           Dirt Track                     None           
pond                 Pond                           Fishing-rod    
dirt-track           Dirt Track                     None           
mesa                 Mesa                           None           
foothills            Foothills                      None           
pine-forest          Pine Forest                    Pine-cone      
scree-slope          Scree Slope                    None           
cave-entrance        Cave Entrance                  None           
low-tunnel           Low Tunnel                     Pickaxe        
cave-entrance        Cave Entrance                  None           
secret-passage-east  Secret Passage (East)          None           
huge-cavern          Huge Cavern                    None           
narrow-crawl         Narrow Crawl                   None           
underground-lake     Underground Lake               Lamp           


