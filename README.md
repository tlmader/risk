risk
====

University of New Orleans Software Design II Project – Risk

Risk is a turn-based game for two to six players. The standard version is played on a board depicting a political map of the Earth, divided into forty-two territories, which are grouped into six continents. The object of the game is to eliminate the other players by occupying every territory on the board. Players use armies to attack and defend territories from other players. A random number generator determines the outcomes of battles.

This program has three main screens that will have to be displayed to the user:
- A main menu
- Game setup screen
- Main play screen

The Main menu will be simple. It will consist of three buttons: New Game, Load Saved Game, and Quit.
- If the user selects “Quit”, the program closes and exits.
- If the user selects “Load Saved Game”, a file chooser will be displayed to allow the user to select the saved game file.
- If the user clicks “New Game”, the Players Creation Screen will be displayed. The Player Creation Screen will first accept input for how many players there will be (between 3 and 6). It will then display input fields for each player’s name and color.

After the user has input the required information for the players, then the Main Play Screen will be displayed. The Main Play Screen consists of several components. The Risk Hand for the current player is displayed along with a button to turn in cards. The game map is also displayed. The last major component is an interchangeable panel that allows the player to perform whatever actions are necessary for the current step of the turn.

If the user clicks the button to turn in risk cards, the panel allows the user to select the risk cards to turn in and to turn them in. When the player needs to place reinforcement troops, the panel allows the user to select how
many troops and to what territory to place them until all the troops have been placed. After placing reinforcements, the player can choose to attack. If the player chooses to attack, the panel allows the user to select one of his/her
occupied territories to attack from, an adjacent territory to attack, and the number of troops with which to attack. The player can do this as many times as he/she wishes. Finally, after the player finishes attacking or chooses not to, the player can fortify troops from one occupied territory to another adjacent occupied territory. If the player is taking advantage of this free move at the end of his/her turn, the panel allows the player to select the territory from which to move the troops, the adjacent territory to move the troops to, and the number of troops to move. There is also a panel to select “Menu” options to save or quit the game.

Players are able to choose to move on to the next steps throughout their turns. There is a set of buttons: “Turn in Cards”, “Place Reinforcements”, “Attack”, “Fortify”, “Menu” that the user can click to move on to the next step of the turn.
