# Set up platform
fill 150 253 150 170 255 170 minecraft:bedrock hollow
fill 151 255 151 169 255 169 minecraft:air

# New spawnpoint
setworldspawn 160 254 160
tp @a 160 255 160

# gamerule stuff 
difficulty normal
gamerule naturalRegeneration false
gamerule doInsomnia false
gamerule doWeatherCycle false

# Objectives (moved kill scorebord init to after spread)
scoreboard objectives add health health
scoreboard objectives setdisplay list health


# World boarder
worldborder center 0 0
worldborder set 1000

# Teams (can be modified and not all have to be used but NEEDS ADDITIONAL MANUAL SETUP)
team add husky
team modify husky color aqua

team add evil
team modify evil color black

team add cherry
team modify cherry color red

team add eagle
team modify eagle color gold

team add hen
team modify hen color yellow

team add mint
team modify mint color green

# Welcome
time set day
title @a title {"text":"<^^=+WELCOME+=^^>","color":"light_purple"}