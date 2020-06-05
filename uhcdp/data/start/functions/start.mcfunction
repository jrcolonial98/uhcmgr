# Countdown (go also starts 1st day and enables daynight cycle)
function text:three
schedule function text:two 1s replace
schedule function text:one 2s append
schedule function text:go 3s append 

# Start timers for border and clear effects
# set to:
# 0-nothing-
# 45-shirnk to 100diam-
# 90-nothing-
# 105-shrink to 20diam and eternal day-
# 135- nothing 
schedule function helpers:cleareffects 3s append
schedule function helpers:shrink1 2700s append
schedule function helpers:shrink2 6300s append

# Add kills and deathsscoreboard
scoreboard objectives add kills playerKillCount
scoreboard objectives setdisplay sidebar kills
scoreboard objectives add deaths deathCount