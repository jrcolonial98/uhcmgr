#spectate anyone who dies
gamemode spectator @a[scores={deaths=1..5}]
scoreboard players reset @a deaths

#placed under minecraft tick tag, following code not needed
#schedule function helpers:autospec 1s append
