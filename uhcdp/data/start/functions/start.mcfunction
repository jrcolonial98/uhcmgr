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

# Start timers for border and clear effects
# set to:
# 0 - nothing
# 60 - shrink to 101 diam
# 120 - nothing
# 135 - shrink to 21 diam and eternal day
# 165 - nothing 
schedule function helpers:cleareffects 3s append
schedule function helpers:shrink1 3600s append
schedule function helpers:mansurface 7200s append
schedule function helpers:shrink2 8100s append

# Add kills and deaths scoreboard
scoreboard objectives add kills playerKillCount "Kills"
scoreboard objectives setdisplay sidebar kills
scoreboard objectives add deaths deathCount "Deaths"