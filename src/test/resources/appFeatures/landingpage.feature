Feature: Land on the page and check basics

	Background: 
		Given User on the Renderz landing page
		
	Scenario: Verify the logo
		#Given User on the Renderz landing page
		Then Application logo should be visible properly
		
	Scenario: Verify the title of the page
		#Given User on the Renderz landing page
		Then User should see the title "RenderZ (previously FIFARenderZ) - Your #1 FC Mobile Database"
		
	Scenario: User redirects to Filter Players page
		Given User hover on Players
		When User click on "FC 24"
		Then User redirects to "Filter Players | RenderZ" page