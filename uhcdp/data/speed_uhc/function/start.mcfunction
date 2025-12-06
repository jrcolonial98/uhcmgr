# Misc
fill 150 245 150 170 255 170 minecraft:air
gamerule doDaylightCycle true
advancement revoke @a everything
time set 0

xp set @a 0 levels
xp set @a 0 points

# Countdown (go also starts 1st day and enables daynight cycle)
function text:three
schedule function text:two 1s replace
schedule function text:one 2s append
schedule function text:go 3s append 
title @a actionbar {"text":"Speed UHC","color":"white"}

# Start timers for border and clear effects
# delays 10 minutes before shrinking
schedule function helpers:cleareffects 3s append
schedule function speed_uhc:full_shrink 600s append

# Add kills and deaths scoreboard
scoreboard objectives add kills playerKillCount "Kills"
scoreboard objectives setdisplay sidebar kills
scoreboard objectives add deaths deathCount "Deaths"