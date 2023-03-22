# Black Ether Minecraft Mod
[![CodeQL](https://github.com/javachaos/blackether/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/javachaos/blackether/actions/workflows/codeql-analysis.yml)

To install, go to https://www.curseforge.com/minecraft/mc-mods/black-ether-mod

## Current Items:

- **Ether ore:** Burnable, lasts a little longer than coal ore. Ether is found at level 8 or lower, most commonly between 0 and 8, in vein sizes of about 5.
- **Block of ether:** Non-burnable, but can be placed into a blast furnace to create diamonds.
- **Onyx ore:** An ore slightly more valuable than diamond. Can be used to craft an Onyx Pickaxe. To create Onyx ore, simply place a diamond into either a blast furnace or a furnace and burn it to produce Onyx.
- **Onyx Pickaxe**
- **Onyx Shovel**
- **Onyx Axe**
- **Onyx Hoe**
- **Onyx Dust:** Craftable using the stonesaw. (WIP, will add more to this later. Dust is currently useless.)
- **Onyx Armor:** Craftable using onyx ore.
- **Onyx fort:** Structures which spawn randomly 1 in every 1000 chunks, filled with loot and a few goodies.
- **Onyx Snake Mob:** An onyx snake which has a few animations and is hostile.

This mod changes how diamonds can be acquired. A new recipe was introduced to allow the player to smelt a block of ether or place a block of ether into the blast furnace to create a diamond. Cooking time in the blast furnace is 3x as fast.

The following properties exist in a file called "ethermod.properties" in the same directory as the server.properties file (run directory):

- `spawn.onyxforts` (default value is true)
- `enable.onyx.biome` (default value is true)
- `onyxfort.spawn.chance` (default value is 1000; note that setting this value too low will cause issues. No less than 100, and you're safe.) (Lower means higher chance to spawn.)

**NOTE:** This mod requires the Fabric API.


[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.me/fred801825)
