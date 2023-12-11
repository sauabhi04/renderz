Feature: Filter players wrt their Ovr and Position

	Background: 
	Given User is on Renderz Players page with minOvr as 70 and maxOvr as 70

	Scenario: User filters player
	 Given User set the position as "LW" on "Position" tab
	 When User clicks on a player
	 Then Player details opens in a new tab