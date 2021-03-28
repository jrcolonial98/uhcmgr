# Overview
### Stats
The code under src is a Java project that allows manipulation of stats. Stats are pulled from the CSV files in src/load which are manually created. Most of the statistical work is done in src/stat/Stats.java, and the main() function is in src/load/LoadAll.java.
### Datapack
Everything under the directory uhcdp is the datapack. The main functions are under "uhc", and the others are helpers which are referenced by those.

Once a game is started, dying will cause a player to automatically switch to spectating.
### Installation / Usage
Ensure that uhcdp is in the server datapacks directory. Run uhc:setup after creating the world. Once everyone is in the game and in the proper teams, run uhc:waitspread and make sure there are no problems with unlucky spawns. Then run uhc:start. After the game is over, run uhc:stop.

# Manual setup required
## In server.properties:
Set gamemode to adventure and obviously the MOTD and seed
## In-game:
Add players to teams

# Game Modes
Functions to run alternate game modes are under their corresponding namespaces.
- **UHC** 
    - Classic UHC. No regen and the border slowly closes in.
- **Speed UHC** 
    - Like UHC, but the border moves in quicker. 
    - Games complete in 70 mins or less.
- **Blitz** 
    - Regen on and start kitted up. Quick border close in.
    - Meant to mimic UHC endgames but a bit more forgiving.
    - Games complete in 50 mins or less.