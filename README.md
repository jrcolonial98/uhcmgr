# Overview
UHC Manager is a datapack for minecraft java version 1.21.10 to run UHC games on minecraft servers. More information on setting up a minecraft server can be found [here](https://www.minecraft.net/en-us/download/server).

# Project components
### Stats
The code under src is a Java project that allows manipulation of stats. Stats are pulled from the CSV files in src/load which are manually created. Most of the statistical work is done in src/stat/Stats.java, and the main() function is in src/load/LoadAll.java.

### Datapack
Everything under the directory uhcdp are the custom commands for running UHC. The main functions are under "uhc", and the others are helpers which are referenced by those.

# Installation
1. Copy the [uhcdp](./uhcdp/) folder into the `[world file]/datapacks` folder on your server. The server folder structure should look like:  
```
[world name]/datapacks/uhcdp
├── pack.mcmeta
└── data/
    └── <namespace>/
        ├── function/
        └── (etc…)
```
NOTE: double check [world file] matches what is specified in your server.properties file
2. Ensure you have the correct privileges to run the custom commands with `op [username]` from the server console  
3. When ingame use `/datapack list`, `/datapack enable`, and `/reload` to verify the datapack is loaded  
4. When done correctly you should be able to access the custom commands from the in game terminal using `/function [namespace i.e. uhc/speed_uhc/blitz]:[function]`.  

If you run into issues using this datapack with another version of minecraft consider comparing the foldernames and file sctructure to a datapack off of a trusted source like [modrinth](modrinth.com).

# Running UHC 
1. Create the waiting area `/function uhc:setup`. This command creates a bedrock cage that players will spawn into in adventure mode (NOTE: update initial gamemode as adventure in server.properties if not working)  
2. Setup the teams scoreboard using ``.   
3. Add players to teams using ``.  
4. Once everyone is ready and has been added to a team, run `/function uhc:waitspread` to spread players and create the world boarder. Mining fatigue and other effects are applied to ensure plays cannot start. Ensure everyone is ready and spread correctly. 
5. Unfreeze players and start the world boarder with `/function uhc:start`. WARNING: Once a game is started, dying will cause a player to automatically switch to spectating.  
6. When the game is over, convert all players to spectator mode with `/function uhc:stop`.  

# Game Mode Summaries 
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