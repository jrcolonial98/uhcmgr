# Overview
### Stats
The code under src is a Java project that allows manipulation of stats. Stats are pulled from the CSV files in src/load which are manually created. Most of the statistical work is done in src/stat/Stats.java, and the main() function is in src/load/LoadAll.java.
### Datapack
Everything under the directory uhcdp is the datapack. The main functions are under start, and the others are helpers which are referenced by those.
### Installation / Usage
Ensure that uhcdp is in the server datapacks directory. Run start:setup after creating the world. Once everyone is in the game and in the proper teams, run start:waitspread and make sure there are no problems with unlucky spawns. Then run start:start. After the game is over, run start:stop.

# Manual setup required
## In server.properties:
Set gamemode to adventure, enable-command-blocks to true, and obviously the MOTD and seed
## Command blocks:
TODO
## In-game:
Add players to teams