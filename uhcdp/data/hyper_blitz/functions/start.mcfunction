# Misc
fill 150 245 150 170 255 170 minecraft:air
gamerule doDaylightCycle true
gamerule naturalRegeneration false
advancement revoke @a everything
time set 0

xp set @a 5 levels
xp set @a 0 points

#set end portal
schedule function hyper_blitz:summon_endportal 3s append

# Countdown (go also starts 1st day and enables daynight cycle)
title @a actionbar {"text":"Speed UHC","color":"white"}
function text:three
schedule function text:two 1s replace
schedule function text:one 2s append
schedule function text:hyper_blitz 3s append 

#starting kit
schedule function hyper_blitz:kitup 3s append

# Start timers for border and clear effects
schedule function helpers:cleareffects 3s append

#shrink border across 45 mins
worldborder set 101 3000
advancement grant @a[gamemode=survival] only uhc:walls_closing

# Add kills and deaths scoreboard
scoreboard objectives add kills playerKillCount "Kills"
scoreboard objectives setdisplay sidebar kills
scoreboard objectives add deaths deathCount "Deaths"
