##Phased Server, Client, and ServerManager.

I've taken the base sources from Aggroth, and have started to upgrade everything I see might be wrong.

Lot's of refactoring has happened, and will happen. I plan on releasing the client later on for everyone, and making it an easy way to start your own Private server

###Client
I've updated the client to more or less work on Linux, Mac, and Windows Equally. There still needs to be a cache upgrade; other than that  it's all good.


###Server

Big addition to the server is the Command functionality, it's possible to control the server simply by adding a lambda method.

Currently the functions that are present are:
  * uptime
  * players
  * player:
    * -p / --player ( Specify the player you're going to be working on )
    * -k / --kick ( kick the specified player )
    * -g / --give ( give a specific item to player + amount of said item you want to give )
    * --delete-invent ( deletes players inventory )
    * --bank-invent ( dumps inventory into the bank )
    * -t / --teleport ( teleports a player to a given location )
  * item:
    * -c / --change ( change a specific item id)
    * --reload ( reload items for everyone on the server )
  * npc:
    * --reload ( reloads the npc config for live updates )
    

On the list of TODO commands I'd like to:
  * Load items from the config file again
  * Shop reloading
  * Npc reloading
  * Npc drops reloading

###ServerManager
This is something I've personally implemented. I needed the ability to control the server, from not the server. Since I wasn't going to bother hosting it on a Windows server, and would never use the GUI I needed a CLI. This makes it possible to control the server from anywhere around the world, as long as you have an internet connection.
