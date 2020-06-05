# The Database

The database is organized as follows.

Table UHC: int id, text date, text motd (example: UHC (1, "4-4-2020", "It begins!") )
Table PLAYER: text username, text nickname (example: PLAYER ("Dunkersplatt", "Bdan") )
Table REGISTRATION: text player, int uhc, text team (example: REGISTRATION ("Dunkersplatt", 6, "Team BC") )
Table KILL: text killer, text victim, int uhc, text time, text method (example: KILL ("Dunkersplatt", "XmasGoose", 3, "1:05:20", "shot") )
Table STAT: text player, int uhc, int damageDealt, int damageTaken, ... (example: STAT ("Dunkersplatt", 8, 100, 0, ...) )

The database is using sqlite3 engine, which should be built in on macOS.

# Logfile parser

This one is gonna be kinda tough because of all the little details it requires. Reading the logfile line by line, pull out every kill, first blood, first nether, first diamonds.
Store it in whatever kind of data structure you like, as the code to then move the data into the database can adapt accordingly.

# Stat parser

Located in src/parse/StatParser.java, this module must be able to read the stats file in the world/stats folder in the server. For every player (indexed by username), we need:

(Idk how to format a list yet) damage dealt, damage taken, mobs killed, distance sprinted, distance walked, distance crouched. And whatever else you see fit.

It is probably best stored in Map<Player,Map<String,Integer>> where the Map<String,Integer> are the stats themselves (like {"DamageDealt": 100} for example) and the Player is the person whose stats those are.

# uhcdp work

Some changes will need to be made to the datapack, namely: fix the bedrock spawning before the game begins,

# Other things

We want every world seed, 
