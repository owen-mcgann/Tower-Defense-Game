# TOWER EDU-FENSE 
## Memebers: Brooktie Frogge, Andrew Peng, Owen McGann, Zachary Smith
### Mission Statement: To bring an enriching and educational twist to the long-established tower-defense genre. 
#### Problem Statement: 
Currently, many fun and engaging tower defense games exist that are played by millions of people across the globe. 
However, there is a major lack of these types of games that offer educational content and exercises for elementary school children, which is a demographic that already loves to play tower defense games. 
Children need more opportunities to enrich their early education while still being able to play a genre of video games that they know and love. 

# How to run our game
In order to run our game you are going to use the IDE intellij
* Go to ColoradoCollege-MathCS/ team_boza  press the green code button, go to ssh and copy that link
* Go to your terminal and navigate to where you want this game to live. Then put in “git clone <copy of the ssh>
* Go to intellij and open up the team_boza file 
* Once you are in team_boza you will want to go to project structure (which is under file)
* Click libraries, then press the plus button and select maven 
* In the pop up put JUnit in the search bar and select “junit:junit:4.13.2”
* Make sure the “Download to” goes to where your team_boza folder is located
then you can press ok
* Then press the plus button again but this time press Java
* Locate your “mysql-connector-j-9.0.0.jar” file, open it, and then press ok once the choose module pop up appears. 
* Then press apply followed by ok
* On the left section of intellij select the “DB Browser” which is the third icon
* Press the plus icon and select mysql 
* Change these sections  
  * Database: OwenDB
  * Port: 1521
  * User: (your colorado college username, for example: b_frogge)
  * Password: Changeme_00
  * Driver source: External library 
  * Driver library:  (your .jar file from above again)
  * Driver: (will auto fill once you have added your .jar file)
* Press apply 
* Test the connection to make sure your connection is successful 
* Then press “OK”
* The next step is to ensure you are connected/logged into the mathcs server to access the database (OwenDB)
* Open a new terminal window and ensure you are in your central directory (cd)
* Next type into your terminal: vim .bashrc to access your saved command or source .bashrc to access the mathcs server
  * For example my command is saved as “mathcs” 
* Next type whatever that command is for you into your terminal and this should allow you to connect to the math/cs server (after entering your credentials/passwords)
* After you have entered your credentials and logged in you will need to type this command into your terminal to enter the cc mysql:
mysql -u your_username -p
  * For example mine looks like this: mysql -u o_mcgann -p
* After entering that command into your terminal it will prompt you to enter a password which is:
Changeme_00
* After entering this password it should welcome you to the server as so…:
  * Welcome to the MySQL monitor.  Commands end with ; or \g.
  * Your MySQL connection id is 4233
  * Server version: 8.0.32 Source distribution
* From here you are connected to the mysql server and just need to access the correct database by entering the following:
  * mysql> use OwenDB;
* You have successfully entered the mysql server and are accessing the correct database (it should look like this after typing use OwenDB;:
  * Reading table information for completion of table and column names
  * You can turn off this feature to get a quicker startup with -A
  * Database changed
* The last step to correctly initialize and connect to the database (OwenDB) is to ensure you are connected/logged-into the CC mproxy server
* Go to your terminal and ensure you are in your central directory (cd)
* Next type into your terminal: vim .bashrc to access your saved command or source .bashrc to access the CC proxy-server
  * For example my command is saved as “mproxy” 
* Next type whatever that command is for you into your terminal and this should allow you to connect to the proxy server (after entering your credentials)
* Ensure this terminal window stays open and shows you something like this:
  * Success. Logging you in…
  * Authenticated to ma-mathcs.coloradocollege.edu (via proxy) using "keyboard-interactive".
  * debug1: Local connections to LOCALHOST:1521 forwarded to remote
  * address ma-mathcs.coloradocollege.edu:3306
  * You have successfully connected to the proxy server!
* Go back to “Project” (first icon) and locate the src folder 
* Go to main and run the file using the green play icon 
  * make sure you are running your current file 
* Have fun and learn lots!!
