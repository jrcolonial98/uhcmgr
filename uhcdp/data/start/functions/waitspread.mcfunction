# Spreads players 
spreadplayers 0 0 300 500 true @a

#Inventory 
clear @a
gamerule keepInventory false

# Keep them safe
gamemode adventure @a
effect give @a minecraft:regeneration 180 99
effect give @a minecraft:saturation 10 100
effect give @a minecraft:slowness 999 100
effect give @a minecraft:slow_falling 999 100
effect give @a minecraft:mining_fatigue 999 100

# Ensure they are happy before start
title @a title {"text":"Any issues?","color":"white"}
#schedule function text:wait 15s
