#spectate anyone who dies
gamemode spectator @a[scores={deaths=1..5}]
scoreboard players reset @a deaths
schedule function helpers:autospec 1s append
